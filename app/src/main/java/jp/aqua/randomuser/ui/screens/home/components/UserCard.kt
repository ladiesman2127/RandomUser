package jp.aqua.randomuser.ui.screens.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.SubcomposeAsyncImage
import jp.aqua.randomuser.R
import jp.aqua.randomuser.data.User
import jp.aqua.randomuser.domain.randomuser.model.components.UserData
import jp.aqua.randomuser.domain.randomuser.model.components.addressToString
import jp.aqua.randomuser.domain.randomuser.model.components.fullName

@Composable
fun UserCard(
    modifier: Modifier = Modifier, user: User, onClick: () -> Unit
) {
    Card(
        modifier = modifier.run {
            fillMaxWidth()
                .height(dimensionResource(R.dimen.card_height))
                .clickable(onClick = onClick)
        },
        shape = CardDefaults.outlinedShape,
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
    ) {
        if (user.data != null) {
            Row(Modifier.fillMaxSize()) {
                UserPicture(user.data.picture.large)
                UserShortDescription(user.data)
            }
        }
    }
}

@Composable
fun UserPicture(url: String?) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .aspectRatio(1f)
            .padding(dimensionResource(R.dimen.small_padding))
            .clip(RoundedCornerShape(dimensionResource(R.dimen.small_clip)))
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape),
            model = url,
            loading = {
                CircularProgressIndicator()
            },
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.description)
        )
    }
}

@Composable
fun UserShortDescription(data: UserData) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.small_padding)),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = data.fullName(),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = data.addressToString(),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = data.phone,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis
        )
    }
}