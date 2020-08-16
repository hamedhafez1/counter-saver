package ir.roela.countersaver.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CounterDao {
    @Query("SELECT * FROM counter ORDER BY CounterId DESC")
    fun getAll(): LiveData<List<Counter>>

    @Insert
    fun insert(counter: Counter)

    @Delete
    fun delete(counter: Counter)
}