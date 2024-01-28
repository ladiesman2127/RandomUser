package jp.aqua.randomuser.ui.screens.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.aqua.randomuser.data.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    fun getUser(id: Int) = userRepository.getUser(id)

}
