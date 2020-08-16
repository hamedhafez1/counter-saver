package ir.roela.countersaver.ui.home

import android.app.Application
import android.util.Log
import ir.roela.countersaver.G
import ir.roela.countersaver.R
import ir.roela.countersaver.model.Counter
import ir.roela.countersaver.ui.dashboard.CounterViewModel

class CounterPresenter(private val view: UserCounter.View) : UserCounter.Presenter {

    override fun initCounterText() {
        if (G.counter == 0L) {
            view.enableSaveCountButton(false)
        }
        view.txtCountSetText()
    }

    override fun increaseCount() {
        if (G.counter == 0L)
            view.enableSaveCountButton(true)
        G.counter++
        view.txtCountSetText()
    }

    override fun decreaseCount() {
        if (G.counter != 0L) {
            G.counter--
            view.txtCountSetText()
        }
    }

    override fun saveCount(
        application: Application,
        name: String,
        dialogListener: UserCounter.SaveDialogListener
    ) {
        try {
            if (name.length > 2) {
                val counter = Counter(name, G.counter.toString())
                CounterViewModel(application).insertCounter(counter)
                G.counter = 0L
                view.txtCountSetText()
                view.enableSaveCountButton(false)
                dialogListener.dismissDialog()
                dialogListener.saveSuccess()
            } else {
                dialogListener.showError(application.getString(R.string.entered_name_short))
            }
        } catch (e: Exception) {
            Log.e("counter", e.message.toString())
        }
    }
}