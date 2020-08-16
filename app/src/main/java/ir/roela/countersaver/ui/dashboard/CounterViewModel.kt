package ir.roela.countersaver.ui.dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ir.roela.countersaver.model.Counter
import ir.roela.countersaver.model.CounterRepository

class CounterViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: CounterRepository = CounterRepository(application)

    fun getCounters() = repository.getCounters()

    fun insertCounter(counter: Counter) = repository.insertCounter(counter)

    fun deleteCounter(counter: Counter) = repository.deleteCounter(counter)

}


