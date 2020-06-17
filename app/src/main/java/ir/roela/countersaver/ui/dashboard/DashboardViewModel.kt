package ir.roela.countersaver.ui.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ir.roela.countersaver.G
import ir.roela.countersaver.model.Counter

class DashboardViewModel : ViewModel() {

    private val _counters = MutableLiveData<List<Counter>>().apply {
        value = G.db!!.counterDao()?.getAll()
    }


    val counters: MutableLiveData<List<Counter>> = _counters


}


