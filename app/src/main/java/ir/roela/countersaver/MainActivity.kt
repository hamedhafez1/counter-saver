package ir.roela.countersaver

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
//        supportActionBar!!.setCustomView(R.layout.actionbar_layout)

        resources.configuration.setLocale(Locale("fa", "IR"))
        window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.about, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_about_us) {
            val builder: AlertDialog.Builder? = AlertDialog.Builder(this)
            val view = layoutInflater.inflate(R.layout.about_us, null, false)
            val txtVersion = view.findViewById<TextView>(R.id.txtVersion)
            txtVersion.text =
                getString(R.string.version, getString(R.string.beshmar_version), " : ", BuildConfig.VERSION_NAME)
            builder?.setView(view)
            val dialog: AlertDialog? = builder?.create()
            dialog?.show()
        }
        return false
    }
}