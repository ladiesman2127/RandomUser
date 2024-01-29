package jp.aqua.randomuser.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import jp.aqua.randomuser.R
import jp.aqua.randomuser.ui.AppViewModelProvider
import jp.aqua.randomuser.ui.screens.home.HomeScreen
import jp.aqua.randomuser.ui.screens.home.HomeViewModel
import jp.aqua.randomuser.ui.screens.user.UserScreen
import jp.aqua.randomuser.ui.screens.user.UserViewModel

enum class AppScreen(@StringRes val title: Int) {
    HomeScreen(R.string.random_user),
    UserScreen(R.string.user_info)
}

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    homeViewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
    userViewModel: UserViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = AppScreen.HomeScreen.name,
    ) {
        composable(route = AppScreen.HomeScreen.name) {
            HomeScreen(
                modifier = Modifier.fillMaxSize(),
                viewModel = homeViewModel,
                onNavigateToFullDescription = { bookId ->
                    navController.navigate(
                        "${AppScreen.UserScreen.name}/$bookId"
                    )
                }
            )
        }
        composable(
            "${AppScreen.UserScreen.name}/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.IntType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId")
            UserScreen(
                modifier = Modifier.fillMaxSize(),
                viewModel = userViewModel,
                userId = userId!!
            )
        }
    }
}