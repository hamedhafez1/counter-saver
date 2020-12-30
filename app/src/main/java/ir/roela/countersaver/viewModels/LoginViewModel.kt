package ir.roela.countersaver.viewModels

import android.text.TextUtils
import android.util.Patterns
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import ir.roela.countersaver.BR
import ir.roela.countersaver.model.User


class LoginViewModel : BaseObservable() {
    private var user: User = User("", "")

    private var successMessage = "Login was successful"
    private var errorMessage = "Email or Password not valid"

    @Bindable
    private var toastMessage: String? = null

    fun getToastMessage(): String? {
        return toastMessage
    }

    private fun setToastMessage(toastMessage: String) {
        this.toastMessage = toastMessage
        notifyPropertyChanged(BR.toastMessage)
    }

    fun setUserEmail(email: String) {
        user.email = email
        notifyPropertyChanged(BR.userEmail)
    }

    @Bindable
    fun getUserEmail(): String {
        return user.email
    }

    fun setUserPassword(password: String) {
        user.password = password
        notifyPropertyChanged(BR.userPassword)
    }

    @Bindable
    fun getUserPassword(): String {
        return user.password
    }

    fun onLoginClicked() {
        if (isInputDataValid()) setToastMessage(successMessage) else setToastMessage(errorMessage)
    }

    private fun isInputDataValid(): Boolean {
        return !TextUtils.isEmpty(getUserEmail()) &&
                Patterns.EMAIL_ADDRESS.matcher(getUserEmail())
                    .matches() && getUserPassword().length > 5
    }


}