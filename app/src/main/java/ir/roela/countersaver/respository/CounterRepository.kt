package ir.roela.countersaver.respository

import androidx.lifecycle.LiveData
import ir.roela.countersaver.model.Counter

class CounterRepository(private val counterDao: Counter.CountDao) {
    val allCounters: LiveData<List<Counter>> = counterDao.getAll()

    suspend fun insert(counter: Counter) {
        counterDao.insert(counter)
    }
}