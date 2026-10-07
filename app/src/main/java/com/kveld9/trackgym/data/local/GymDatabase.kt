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
    version = 11,
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

        fun getInstance(context: Context): GymDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GymDatabase::class.java,
                    "trackgym_database.db"
                )
                    .fallbackToDestructiveMigration(dropAllTables = true)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
