package jp.aqua.randomuser.ui.screens.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.aqua.randomuser.data.User
import jp.aqua.randomuser.data.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class HomeViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    fun getUsers() = userRepository.getAllUsers()

    suspend fun updateUsers(context: Context): User? =
        withContext(Dispatchers.IO) {
            try {
                userRepository.updateUserList()
            } catch (e: IOException) {
                e.printStackTrace()
                null
            }
        }
}
