package jp.aqua.randomuser.ui.screens.user

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import coil.compose.SubcomposeAsyncImage
import jp.aqua.randomuser.R
import jp.aqua.randomuser.data.User
import jp.aqua.randomuser.domain.randomuser.model.components.UserData
import jp.aqua.randomuser.domain.randomuser.model.components.addressToString
import jp.aqua.randomuser.domain.randomuser.model.components.coordinatesToString
import jp.aqua.randomuser.domain.randomuser.model.components.dobToString
import jp.aqua.randomuser.domain.randomuser.model.components.fullName
import jp.aqua.randomuser.domain.randomuser.model.components.idToString
import jp.aqua.randomuser.domain.randomuser.model.components.loginToString
import jp.aqua.randomuser.domain.randomuser.model.components.registeredToString
import jp.aqua.randomuser.extensions.capitalize


@Composable
fun UserScreen(
    modifier: Modifier = Modifier,
    viewModel: UserViewModel,
    userId: Int,
) {
    val context = LocalContext.current
    val user = viewModel.getUser(userId).collectAsState(User())
    val userData = user.value.data
    LazyColumn(
        modifier = modifier.padding(dimensionResource(R.dimen.small_padding)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (userData != null) {
            item {
                val address = userData.addressToString()
                val coordinates = userData.coordinatesToString()
                val email = userData.email
                val phone = userData.phone
                val cell = userData.cell

                Description(UserData.NAME.capitalize() + ": " + userData.fullName())
                Description(UserData.GENDER.capitalize() + ": " + userData.gender)
                Description(UserData.NAT.capitalize() + ": " + userData.nat)
                OpenableDescription("Address", address) {
                    openInMaps(context, address)
                }
                OpenableDescription("Coordinates", coordinates) {
                    openInMaps(context, coordinates)
                }
                OpenableDescription(UserData.EMAIL.capitalize(), email) {
                    openInMail(context, email)
                }
                Description(UserData.LOGIN.capitalize() + ": " + userData.loginToString())
                Description(UserData.DOB.capitalize() + ": " + userData.dobToString())
                Description(UserData.REGISTERED.capitalize() + ": " + userData.registeredToString())
                OpenableDescription(UserData.PHONE.capitalize(), phone) {
                    openInPhone(context, phone)
                }
                OpenableDescription(UserData.CELL.capitalize(), cell) {
                    openInPhone(context, cell)
                }
                Description(UserData.USER_ID.capitalize() + ": " + userData.idToString())
                Description(UserData.PICTURE.capitalize() + ": \n")
                UserPicture(userData.picture.large, R.string.large_picture)
                UserPicture(userData.picture.medium, R.string.medium_picture)
                UserPicture(userData.picture.thumbnail, R.string.thumbnail)
            }
        }
    }
}

@Composable
fun OpenableDescription(
    prefix: String,
    description: String,
    onClick: () -> Unit
) {
    val prefixStyle = MaterialTheme.typography.labelLarge
    val annotatedAddress = buildAnnotatedString {
        withStyle(style = ParagraphStyle(textAlign = TextAlign.Center)) {
            withStyle(prefixStyle.copy(color = Color.White).toSpanStyle()) {
                append("$prefix: ")
            }
        }
        val start = length
        val end = length + description.length
        addStringAnnotation("description_tag", "", start, end)
        withStyle(style = ParagraphStyle(textAlign = TextAlign.Center)) {
            withStyle(style = SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold)) {
                append(description)
            }
        }
    }
    ClickableText(
        text = annotatedAddress,
        onClick = { offset ->
            annotatedAddress.getStringAnnotations(
                tag = "description_tag",
                start = offset,
                end = offset
            ).firstOrNull().let {
                if (it != null)
                    onClick()
            }
        }
    )
}

@Composable
fun Description(
    description: String
) {
    Text(
        text = description.capitalize(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.labelLarge
    )
}

@Composable
fun UserPicture(
    url: String,
    @StringRes description: Int
) {
    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        SubcomposeAsyncImage(
            model = url,
            loading = {
                CircularProgressIndicator()
            },
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(description)
        )
    }
}


private fun openInMaps(context: Context, address: String) {
    val uriGoogle = Uri.parse("geo:0,0?q=$address")
    val intentGoogle = Intent(Intent.ACTION_VIEW, uriGoogle).apply {
        setPackage("com.google.android.apps.maps")
    }

    context.startActivity(intentGoogle)
}

private fun openInPhone(context: Context, phone: String) {
    val uri = Uri.parse("tel:$phone")
    val intent = Intent(Intent.ACTION_DIAL, uri)

    context.startActivity(intent)
}

private fun openInMail(context: Context, email: String) {
    val uri = Uri.parse(email)
    val intentGmail = Intent(Intent.ACTION_SEND, uri).apply {
        type = "plain/text"
        setPackage("com.google.android.gm")
        putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
    }

    context.startActivity(intentGmail)
}