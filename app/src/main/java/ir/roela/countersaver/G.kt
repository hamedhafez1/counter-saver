package ir.roela.countersaver

import android.app.Application

class G : Application() {

    companion object {
        var counter: Long = 0
        var db: AppDataBase? = null
    }

    override fun onCreate() {
        super.onCreate()
        db = AppDataBase.getInstance(this)

    }
}