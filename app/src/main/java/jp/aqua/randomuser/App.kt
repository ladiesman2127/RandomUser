package jp.aqua.randomuser

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import jp.aqua.randomuser.ui.navigation.AppNavHost
import jp.aqua.randomuser.ui.navigation.AppScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun App() {
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = enumValues<AppScreen>().find {
        it.name == backStackEntry?.destination?.route
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppTopBar(
                currentScreen = currentScreen,
                navigateUp = {
                    coroutineScope.launch {
                        navController.navigateUp()
                        delay(500)
                    }
                }
            )
        }
    ) { innerPadding ->
        AppNavHost(Modifier.padding(innerPadding), navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    currentScreen: AppScreen?,
    navigateUp: () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = {
            if (currentScreen != null)
                Text(stringResource(currentScreen.title))
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        navigationIcon = {
            if (currentScreen != AppScreen.HomeScreen) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}
