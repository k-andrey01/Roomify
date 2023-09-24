import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myroom.database.repositories.UserRepository
import com.example.myroom.viewmodels.LoginViewModel

class LoginViewModelFactory(private val userRepository: UserRepository, private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(userRepository, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
