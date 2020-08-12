package ir.roela.countersaver

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ir.roela.countersaver.model.Counter

@Database(entities = [Counter::class], version = 1)
public abstract class AppDataBase : RoomDatabase() {
    abstract fun counterDao(): Counter.CountDao?

    companion object {
        private var DATABASE_NAME = "dbCounter.db"
        private var instance: AppDataBase? = null
        private const val NUMBER_OF_THREADS = 4
//        val databaseWriteExecutor: ExecutorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        @Synchronized
        fun getInstance(context: Context): AppDataBase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java, DATABASE_NAME
                ) //delete all data an build again
                    //                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }

}
