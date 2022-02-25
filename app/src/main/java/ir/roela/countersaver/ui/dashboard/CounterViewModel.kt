package ir.roela.countersaver.ui.dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import ir.roela.countersaver.model.CounterModel
import ir.roela.countersaver.model.CounterRepository

class CounterViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: CounterRepository = CounterRepository(application)

    fun getCounters() = repository.getCounters()

    fun insertCounter(counterModel: CounterModel) = repository.insertCounter(counterModel)

    fun deleteCounter(counterModel: CounterModel) = repository.deleteCounter(counterModel)

    fun sdf(){
        viewModelScope
    }

}


