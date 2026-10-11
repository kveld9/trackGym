package com.kveld9.trackgym.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kveld9.trackgym.data.local.dao.CustomExerciseCategoryDao
import com.kveld9.trackgym.data.local.dao.ExerciseDao
import com.kveld9.trackgym.data.local.dao.PersonalRecordDao
import com.kveld9.trackgym.data.local.dao.RoutineDao
import com.kveld9.trackgym.data.local.dao.WorkoutDao
import com.kveld9.trackgym.data.local.entity.CustomExerciseCategoryEntity
import com.kveld9.trackgym.data.local.entity.ExerciseEntity
import com.kveld9.trackgym.data.local.entity.PersonalRecordEntity
import com.kveld9.trackgym.data.local.entity.RoutineEntity
import com.kveld9.trackgym.data.local.entity.RoutineExerciseEntity
import com.kveld9.trackgym.data.local.entity.RoutineFolderEntity
import com.kveld9.trackgym.data.local.entity.WorkoutEntity
import com.kveld9.trackgym.data.local.entity.WorkoutExerciseEntity
import com.kveld9.trackgym.data.local.entity.WorkoutSetEntity

@Database(
    entities = [
        ExerciseEntity::class,
        WorkoutEntity::class,
        WorkoutExerciseEntity::class,
        WorkoutSetEntity::class,
        PersonalRecordEntity::class,
        RoutineFolderEntity::class,
        RoutineEntity::class,
        RoutineExerciseEntity::class,
        CustomExerciseCategoryEntity::class
    ],
    version = 21,
    exportSchema = false
)
abstract class GymDatabase : RoomDatabase() {

    abstract fun exerciseDao(): ExerciseDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun personalRecordDao(): PersonalRecordDao
    abstract fun routineDao(): RoutineDao
    abstract fun customCategoryDao(): CustomExerciseCategoryDao

    companion object {
        @Volatile
        private var INSTANCE: GymDatabase? = null

        private val MIGRATION_12_13 = object : androidx.room.migration.Migration(12, 13) {
            override fun migrate(db: androidx.sqlite.db.SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE exercises ADD COLUMN primaryMuscle TEXT DEFAULT NULL")
                db.execSQL("ALTER TABLE exercises ADD COLUMN secondaryMuscles TEXT DEFAULT NULL")
            }
        }

        private val MIGRATION_13_14 = object : androidx.room.migration.Migration(13, 14) {
            override fun migrate(db: androidx.sqlite.db.SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE exercises ADD COLUMN mechanics TEXT NOT NULL DEFAULT 'COMPOUND'")
                db.execSQL(
                    """
                    UPDATE exercises SET mechanics = 'ISOLATION' 
                    WHERE LOWER(name) LIKE '%curl%' 
                       OR LOWER(name) LIKE '%extension%' 
                       OR LOWER(name) LIKE '%raise%' 
                       OR LOWER(name) LIKE '%fly%' 
                       OR LOWER(name) LIKE '%crossover%' 
                       OR LOWER(name) LIKE '%calf%' 
                       OR LOWER(name) LIKE '%calves%' 
                       OR LOWER(name) LIKE '%crunch%' 
                       OR LOWER(name) LIKE '%shrug%' 
                       OR LOWER(name) LIKE '%face pull%' 
                       OR LOWER(name) LIKE '%kickback%' 
                       OR LOWER(name) LIKE '%pullover%' 
                       OR LOWER(name) LIKE '%wrist%' 
                       OR LOWER(name) LIKE '%abductor%' 
                       OR LOWER(name) LIKE '%adductor%'
                       OR (muscleGroup = 'ARMS' AND LOWER(name) NOT LIKE '%dip%' AND LOWER(name) NOT LIKE '%close-grip%')
                    """.trimIndent()
                )
            }
        }

        private val MIGRATION_14_15 = object : androidx.room.migration.Migration(14, 15) {
            override fun migrate(db: androidx.sqlite.db.SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE exercises ADD COLUMN force TEXT NOT NULL DEFAULT 'PUSH'")
                db.execSQL("ALTER TABLE exercises ADD COLUMN level TEXT NOT NULL DEFAULT 'BEGINNER'")

                db.execSQL(
                    """
                    UPDATE exercises SET force = 'PULL'
                    WHERE muscleGroup = 'BACK'
                       OR LOWER(name) LIKE '%curl%'
                       OR LOWER(name) LIKE '%pull%'
                       OR LOWER(name) LIKE '%row%'
                       OR LOWER(name) LIKE '%chin%'
                       OR LOWER(name) LIKE '%deadlift%'
                       OR LOWER(name) LIKE '%shrug%'
                       OR LOWER(name) LIKE '%rear delt%'
                       OR LOWER(name) LIKE '%crunch%'
                       OR LOWER(name) LIKE '%leg raise%'
                       OR LOWER(name) LIKE '%knee raise%'
                    """.trimIndent()
                )
                db.execSQL(
                    """
                    UPDATE exercises SET force = 'STATIC'
                    WHERE (LOWER(name) LIKE '%plank%'
                       OR LOWER(name) LIKE '%hold%'
                       OR LOWER(name) LIKE '%dead hang%'
                       OR LOWER(name) LIKE '%wall sit%'
                       OR LOWER(name) LIKE '%l-sit%'
                       OR LOWER(name) LIKE '%isometric%')
                      AND LOWER(name) NOT LIKE '%raise%'
                      AND LOWER(name) NOT LIKE '%clean%'
                      AND LOWER(name) NOT LIKE '%snatch%'
                      AND LOWER(name) NOT LIKE '%sit-up%'
                      AND LOWER(name) NOT LIKE '%sit up%'
                    """.trimIndent()
                )
                db.execSQL(
                    """
                    UPDATE exercises SET level = 'INTERMEDIATE'
                    WHERE LOWER(name) LIKE '%barbell%'
                       OR LOWER(name) LIKE '%squat%'
                       OR LOWER(name) LIKE '%deadlift%'
                       OR LOWER(name) LIKE '%dip%'
                       OR LOWER(name) LIKE '%pull-up%'
                       OR LOWER(name) LIKE '%bench press%'
                       OR LOWER(name) LIKE '%overhead press%'
                    """.trimIndent()
                )
                db.execSQL(
                    """
                    UPDATE exercises SET level = 'EXPERT'
                    WHERE LOWER(name) LIKE '%snatch%'
                       OR LOWER(name) LIKE '%clean and jerk%'
                       OR LOWER(name) LIKE '%muscle-up%'
                       OR LOWER(name) LIKE '%pistol squat%'
                       OR LOWER(name) LIKE '%dragon flag%'
                    """.trimIndent()
                )
            }
        }

        private val MIGRATION_15_16 = object : androidx.room.migration.Migration(15, 16) {
            override fun migrate(db: androidx.sqlite.db.SupportSQLiteDatabase) {
                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS custom_exercise_categories (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        name TEXT NOT NULL,
                        createdAt INTEGER NOT NULL
                    )
                    """.trimIndent()
                )
                db.execSQL(
                    "CREATE UNIQUE INDEX IF NOT EXISTS index_custom_exercise_categories_name ON custom_exercise_categories(name)"
                )
                db.execSQL("ALTER TABLE exercises ADD COLUMN customCategories TEXT NOT NULL DEFAULT ''")
            }
        }

        private val MIGRATION_16_17 = object : androidx.room.migration.Migration(16, 17) {
            override fun migrate(db: androidx.sqlite.db.SupportSQLiteDatabase) {
                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS body_measurements (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        type TEXT NOT NULL,
                        value REAL NOT NULL,
                        measuredAt INTEGER NOT NULL,
                        notes TEXT NOT NULL DEFAULT ''
                    )
                    """.trimIndent()
                )
                db.execSQL("CREATE INDEX IF NOT EXISTS index_body_measurements_type ON body_measurements(type)")
                db.execSQL("CREATE INDEX IF NOT EXISTS index_body_measurements_measuredAt ON body_measurements(measuredAt)")
            }
        }

        private val MIGRATION_17_18 = object : androidx.room.migration.Migration(17, 18) {
            override fun migrate(db: androidx.sqlite.db.SupportSQLiteDatabase) {
                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS progress_photos (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        filePath TEXT NOT NULL,
                        pose TEXT NOT NULL,
                        capturedAt INTEGER NOT NULL,
                        notes TEXT NOT NULL DEFAULT ''
                    )
                    """.trimIndent()
                )
                db.execSQL("CREATE INDEX IF NOT EXISTS index_progress_photos_pose ON progress_photos(pose)")
                db.execSQL("CREATE INDEX IF NOT EXISTS index_progress_photos_capturedAt ON progress_photos(capturedAt)")
            }
        }

        private val MIGRATION_18_19 = object : androidx.room.migration.Migration(18, 19) {
            override fun migrate(db: androidx.sqlite.db.SupportSQLiteDatabase) {
                db.execSQL("DROP TABLE IF EXISTS progress_photos")
                db.execSQL("DROP TABLE IF EXISTS body_measurements")
            }
        }

        /**
         * Migration 19 -> 20: Maps obsolete 'CARDIO' exercise category rows to concrete equipment categories
         * (MACHINE, BODYWEIGHT, DUMBBELL, or OTHER) based on exercise names.
         */
        private val MIGRATION_19_20 = object : androidx.room.migration.Migration(19, 20) {
            override fun migrate(db: androidx.sqlite.db.SupportSQLiteDatabase) {
                db.execSQL(
                    """
                    UPDATE exercises
                    SET category = 'MACHINE'
                    WHERE category = 'CARDIO' AND (
                        LOWER(name) LIKE '%treadmill%' OR
                        LOWER(name) LIKE '%bike%' OR
                        LOWER(name) LIKE '%cycling%' OR
                        LOWER(name) LIKE '%bicycling%' OR
                        LOWER(name) LIKE '%elliptical%' OR
                        LOWER(name) LIKE '%rower%' OR
                        LOWER(name) LIKE '%rowing%' OR
                        LOWER(name) LIKE '%climbmill%' OR
                        LOWER(name) LIKE '%stepmill%' OR
                        LOWER(name) LIKE '%step mill%' OR
                        LOWER(name) LIKE '%stairmaster%' OR
                        LOWER(name) LIKE '%skierg%' OR
                        LOWER(name) LIKE '%sled%' OR
                        LOWER(name) LIKE '%prowler%' OR
                        LOWER(name) LIKE '%recumbent%'
                    )
                    """.trimIndent()
                )

                db.execSQL(
                    """
                    UPDATE exercises
                    SET category = 'DUMBBELL'
                    WHERE category = 'CARDIO' AND (
                        LOWER(name) LIKE '%dumbbell%'
                    )
                    """.trimIndent()
                )

                db.execSQL(
                    """
                    UPDATE exercises
                    SET category = 'BODYWEIGHT'
                    WHERE category = 'CARDIO' AND (
                        LOWER(name) LIKE '%run%' OR
                        LOWER(name) LIKE '%sprint%' OR
                        LOWER(name) LIKE '%jog%' OR
                        LOWER(name) LIKE '%walk%' OR
                        LOWER(name) LIKE '%swim%' OR
                        LOWER(name) LIKE '%schwimmen%' OR
                        LOWER(name) LIKE '%jump rope%' OR
                        LOWER(name) LIKE '%jumping jack%' OR
                        LOWER(name) LIKE '%bear crawl%' OR
                        LOWER(name) LIKE '%boxing%' OR
                        LOWER(name) LIKE '%shadow%' OR
                        LOWER(name) LIKE '%skating%' OR
                        LOWER(name) LIKE '%burpee%' OR
                        LOWER(name) LIKE '%high knee%' OR
                        LOWER(name) LIKE '%rope jump%' OR
                        LOWER(name) LIKE '%skipping%'
                    )
                    """.trimIndent()
                )

                db.execSQL("UPDATE exercises SET category = 'OTHER' WHERE category = 'CARDIO'")
            }
        }

        /**
         * Migration 20 -> 21: Adds 'modality' column to exercises table defaulting to 'STRENGTH',
         * then sets 'CARDIO' and 'MOBILITY' modalities using exercise name keyword heuristics.
         * Note on execution order and tie-breaking:
         * The CARDIO update executes first and the MOBILITY update executes second. Because the
         * second write overwrites earlier values in SQLite, executing MOBILITY second guarantees
         * that MOBILITY wins ties whenever an exercise name matches both categories
         * (e.g. "Dynamic Stretch Run" or recovery/warmup drills with cardio terminology).
         */
        private val MIGRATION_20_21 = object : androidx.room.migration.Migration(20, 21) {
            override fun migrate(db: androidx.sqlite.db.SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE exercises ADD COLUMN modality TEXT NOT NULL DEFAULT 'STRENGTH'")
                updateCardioModality(db)
                updateMobilityModality(db)
            }

            private fun updateCardioModality(db: androidx.sqlite.db.SupportSQLiteDatabase) {
                // 1. CARDIO keyword updates (specific multi-word keywords before short ones)
                db.execSQL(
                    """
                    UPDATE exercises
                    SET modality = 'CARDIO'
                    WHERE (
                        LOWER(name) LIKE '%step mill%' OR
                        LOWER(name) LIKE '%stepmill%' OR
                        LOWER(name) LIKE '%climb mill%' OR
                        LOWER(name) LIKE '%climbmill%' OR
                        LOWER(name) LIKE '%jump rope%' OR
                        LOWER(name) LIKE '%rope jump%' OR
                        LOWER(name) LIKE '%skipping rope%' OR
                        LOWER(name) LIKE '%rowing machine%' OR
                        LOWER(name) LIKE '%row machine%' OR
                        LOWER(name) LIKE '%row erg%' OR
                        LOWER(name) LIKE '%stair master%' OR
                        LOWER(name) LIKE '%stairmaster%' OR
                        LOWER(name) LIKE '%ski erg%' OR
                        LOWER(name) LIKE '%skierg%' OR
                        LOWER(name) LIKE '%treadmill%' OR
                        LOWER(name) LIKE '%bicycling%' OR
                        LOWER(name) LIKE '%cycling%' OR
                        LOWER(name) LIKE '%bike%' OR
                        LOWER(name) LIKE '%elliptical%' OR
                        LOWER(name) LIKE '%rowing%' OR
                        LOWER(name) LIKE '%rower%' OR
                        LOWER(name) LIKE '%skating%' OR
                        LOWER(name) LIKE '%swimming%' OR
                        LOWER(name) LIKE '%jogging%' OR
                        LOWER(name) LIKE '%walking%' OR
                        LOWER(name) LIKE '%sprinting%' OR
                        LOWER(name) LIKE '%running%' OR
                        LOWER(name) LIKE '%boxing%' OR
                        LOWER(name) LIKE '%shadow%' OR
                        LOWER(name) LIKE '%cardio%' OR
                        (' ' || LOWER(name) || ' ') LIKE '% run %' OR
                        (' ' || LOWER(name) || ' ') LIKE '% jog %' OR
                        (' ' || LOWER(name) || ' ') LIKE '% walk %' OR
                        (' ' || LOWER(name) || ' ') LIKE '% swim %' OR
                        (' ' || LOWER(name) || ' ') LIKE '% sprint %'
                    ) AND LOWER(name) NOT LIKE '%farmer%'
                    """.trimIndent()
                )
            }

            private fun updateMobilityModality(db: androidx.sqlite.db.SupportSQLiteDatabase) {
                // 2. MOBILITY keyword updates (runs second so MOBILITY wins ties over CARDIO)
                db.execSQL(
                    """
                    UPDATE exercises
                    SET modality = 'MOBILITY'
                    WHERE (
                        LOWER(name) LIKE '%warm-up%' OR
                        LOWER(name) LIKE '%warmup%' OR
                        LOWER(name) LIKE '%stretch%' OR
                        LOWER(name) LIKE '%mobility%' OR
                        LOWER(name) LIKE '%yoga%' OR
                        LOWER(name) LIKE '%breathing%' OR
                        LOWER(name) LIKE '%meditation%' OR
                        LOWER(name) LIKE '%foam%' OR
                        LOWER(name) LIKE '%smr%' OR
                        LOWER(name) LIKE '%activation%' OR
                        LOWER(name) LIKE '%drill%'
                    )
                    """.trimIndent()
                )
            }
        }

        fun getInstance(context: Context): GymDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GymDatabase::class.java,
                    "trackgym_database.db"
                )
                    .addMigrations(
                        MIGRATION_12_13, MIGRATION_13_14, MIGRATION_14_15, MIGRATION_15_16,
                        MIGRATION_16_17, MIGRATION_17_18, MIGRATION_18_19, MIGRATION_19_20,
                        MIGRATION_20_21
                    )
                    .fallbackToDestructiveMigration(dropAllTables = true)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
