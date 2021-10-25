package io.henrikhorbovyi.hacktoberfestissues.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.henrikhorbovyi.hacktoberfestissues.R
import java.net.UnknownHostException

@Composable
fun EmptyStateView(throwable: Throwable?, onRetry: () -> Unit) {
    throwable?.let {
        val message = when (it) {
            is UnknownHostException -> stringResource(R.string.connection_error_message)
            else -> stringResource(R.string.generic_error_message)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.ic_no_connection),
                colorFilter = ColorFilter.tint(Color.Gray),
                modifier = Modifier.size(80.dp),
                contentDescription = stringResource(R.string.content_description_no_connection_icon)
            )
            Text(
                modifier = Modifier.padding(top = 22.dp),
                text = message,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
            )
            Button(
                modifier = Modifier.padding(top = 16.dp),
                onClick = onRetry
            ) { Text(stringResource(R.string.try_again)) }
        }
    }
}