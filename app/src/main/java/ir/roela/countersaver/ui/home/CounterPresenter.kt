package ir.roela.countersaver.ui.home

import android.app.Application
import android.util.Log
import ir.roela.countersaver.R
import ir.roela.countersaver.model.Count
import ir.roela.countersaver.model.CounterModel
import ir.roela.countersaver.ui.dashboard.CounterViewModel

class CounterPresenter(
    private val view: UserCounter.View,
    private val count: Count
) : UserCounter.Presenter {

    override fun initCounterText() {
        if (count.countNumber == 0L) {
            view.enableSaveCountButton(false)
        }
        view.txtCountSetText()
    }

    override fun increaseCount() {
        if (count.countNumber == 0L)
            view.enableSaveCountButton(true)
        count.addCountNumber()
        view.txtCountSetText()
    }

    override fun decreaseCount() {
        if (count.countNumber != 0L) {
            count.minusCountNumber()
            view.txtCountSetText()
        }
    }

    override fun saveCount(
        application: Application,
        name: String,
        date: String,
        dialogListener: UserCounter.SaveDialogListener
    ) {
        try {
            if (name.length > 2) {
                val counter = CounterModel(name, date, count.countNumberStr)
                CounterViewModel(application).insertCounter(counter)
                count.resetCount()
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