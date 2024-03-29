package ir.roela.countersaver.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CounterDao {
    @Query("SELECT * FROM counter ORDER BY CounterId DESC")
    fun getAll(): LiveData<List<CounterModel>>

    @Insert
    fun insert(counterModel: CounterModel)

    @Delete
    fun delete(counterModel: CounterModel)
}