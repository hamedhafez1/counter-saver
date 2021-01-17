package ir.roela.countersaver.ui.home

import android.app.Application

interface UserCounter {

    interface View {
        fun txtCountSetText()
        fun enableSaveCountButton(isEnable: Boolean)
    }

    interface Presenter {
        fun initCounterText()
        fun increaseCount()
        fun decreaseCount()
        fun saveCount(
            application: Application,
            name: String,
            date: String,
            dialogListener: SaveDialogListener
        )
    }

    interface SaveDialogListener {
        fun saveSuccess()
        fun showError(message: String)
        fun dismissDialog()
    }
}