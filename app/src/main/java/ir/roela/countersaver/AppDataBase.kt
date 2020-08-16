package ir.roela.countersaver

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ir.roela.countersaver.model.Counter
import ir.roela.countersaver.model.CounterDao

@Database(entities = [Counter::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun counterDao(): CounterDao?

    companion object {
        private var DATABASE_NAME = "dbCounter.db"
        private var instance: AppDataBase? = null

        @Synchronized
        fun getInstance(context: Context): AppDataBase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java, DATABASE_NAME
                ).build()
            }
            return instance
        }
    }

}
