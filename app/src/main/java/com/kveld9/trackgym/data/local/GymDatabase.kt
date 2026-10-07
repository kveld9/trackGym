package com.kveld9.trackgym.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kveld9.trackgym.data.local.dao.ExerciseDao
import com.kveld9.trackgym.data.local.dao.PersonalRecordDao
import com.kveld9.trackgym.data.local.dao.RoutineDao
import com.kveld9.trackgym.data.local.dao.WorkoutDao
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
        RoutineExerciseEntity::class
    ],
    version = 14,
    exportSchema = false
)
abstract class GymDatabase : RoomDatabase() {

    abstract fun exerciseDao(): ExerciseDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun personalRecordDao(): PersonalRecordDao
    abstract fun routineDao(): RoutineDao

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

        fun getInstance(context: Context): GymDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GymDatabase::class.java,
                    "trackgym_database.db"
                )
                    .addMigrations(MIGRATION_12_13, MIGRATION_13_14)
                    .fallbackToDestructiveMigration(dropAllTables = true)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
