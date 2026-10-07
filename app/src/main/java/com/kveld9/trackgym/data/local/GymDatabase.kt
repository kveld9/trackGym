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
    version = 16,
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

        fun getInstance(context: Context): GymDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GymDatabase::class.java,
                    "trackgym_database.db"
                )
                    .addMigrations(MIGRATION_12_13, MIGRATION_13_14, MIGRATION_14_15, MIGRATION_15_16)
                    .fallbackToDestructiveMigration(dropAllTables = true)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
