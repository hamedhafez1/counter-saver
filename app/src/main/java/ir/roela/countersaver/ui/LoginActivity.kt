package ir.roela.countersaver.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import ir.roela.countersaver.R
import ir.roela.countersaver.databinding.ActivityLoginBinding
import ir.roela.countersaver.viewModels.LoginViewModel

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityLoginBinding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        activityLoginBinding.viewModel = LoginViewModel()
        activityLoginBinding.executePendingBindings()
    }

    companion object {
        @JvmStatic
        @BindingAdapter("toastMessage")
        fun runMe(view: View, message: String?) {
            if (message != null) {
                Toast.makeText(view.context, message, Toast.LENGTH_SHORT).show()
            }
        }
    }


}