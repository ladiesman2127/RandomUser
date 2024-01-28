package jp.aqua.randomuser.ui.screens.home

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import jp.aqua.randomuser.R
import jp.aqua.randomuser.ui.screens.home.components.UserCard
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    onNavigateToFullDescription: (Int) -> Unit,
) {
    val context = LocalContext.current
    val users = viewModel.getUsers().collectAsState(listOf())
    var usersEmpty by remember { mutableStateOf(false) }
    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }

    fun updateUsersList() = refreshScope.launch {
        refreshing = true
        val newUser = viewModel.updateUsers(context)
        if (newUser != null) {
            Toast.makeText(
                context,
                context.getString(R.string.user_added),
                Toast.LENGTH_SHORT
            ).show()
            usersEmpty = false
        } else {
            Toast.makeText(
                context,
                context.getString(R.string.check_internet_connection),
                Toast.LENGTH_LONG
            ).show()
        }
        refreshing = false
    }

    LaunchedEffect(Unit) {
        delay(500)
        if (users.value.isEmpty()) {
            usersEmpty = true
        }
    }

    if (usersEmpty) {
        Text(
            text = stringResource(R.string.on_empty_user_list),
            modifier = Modifier.fillMaxSize(),
            textAlign = TextAlign.Center
        )
    }

    val refreshState = rememberPullRefreshState(refreshing, ::updateUsersList)

    Box(modifier = modifier.pullRefresh(refreshState)) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.small_padding))
        ) {
            items(users.value) { user ->
                UserCard(
                    modifier = Modifier
                        .padding(vertical = dimensionResource(R.dimen.card_vertical_padding)),
                    user = user,
                    onClick = { onNavigateToFullDescription(user.id) }
                )
            }
        }
        PullRefreshIndicator(refreshing, refreshState, Modifier.align(Alignment.TopCenter))
    }
}