package ir.roela.countersaver

import android.app.Application

class G : Application() {

    companion object {
        var counter = 0
        var db: AppDataBase? = null
    }

    override fun onCreate() {
        super.onCreate()
        db = AppDataBase.getInstance(this)

    }
}