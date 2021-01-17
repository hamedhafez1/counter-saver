package ir.roela.countersaver.model

import android.app.Application
import ir.roela.countersaver.AppDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class CounterRepository(application: Application) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var counterDao: CounterDao?

    init {
        val db = AppDataBase.getInstance(application)
        counterDao = db?.counterDao()
    }

    fun getCounters() = counterDao?.getAll()

    fun insertCounter(counterModel: CounterModel) {
        launch {
            withContext(Dispatchers.IO) {
                counterDao?.insert(counterModel)
            }
        }
    }

    fun deleteCounter(counterModel: CounterModel) {
        launch {
            withContext(Dispatchers.IO) {
                counterDao?.delete(counterModel)
            }
        }
    }

}