package jp.aqua.randomuser.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import jp.aqua.randomuser.Application
import jp.aqua.randomuser.ui.screens.home.HomeViewModel
import jp.aqua.randomuser.ui.screens.user.UserViewModel


object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(
                application().userRepository
            )
        }
        initializer {
            UserViewModel(
                application().userRepository
            )
        }
    }
}

fun CreationExtras.application(): Application =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Application)