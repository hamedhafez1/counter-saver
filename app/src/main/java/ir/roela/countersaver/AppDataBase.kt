package ir.roela.countersaver

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ir.roela.countersaver.model.CounterModel
import ir.roela.countersaver.model.CounterDao

@Database(entities = [CounterModel::class], version = 2)
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
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()
            }
            return instance
        }

        // Migration from 1 to 2, Room 2.1.0
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "ALTER TABLE counter ADD COLUMN counter_date TEXT NOT NULL DEFAULT ''"
                )
            }
        }

    }


}
