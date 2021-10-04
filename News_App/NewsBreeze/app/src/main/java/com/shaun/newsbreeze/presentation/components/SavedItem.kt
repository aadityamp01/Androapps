package com.shaun.newsbreeze.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter

import com.shaun.newsbreeze.localdatabase.ArticleLocal
import com.shaun.newsbreeze.localdatabase.toArticle
import com.shaun.newsbreeze.models.Article
import com.shaun.newsbreeze.ui.theme.LightGreen


@ExperimentalMaterialApi
@Composable
fun SavedItem(articleLocal: ArticleLocal, onClick: (Article) -> Unit) {

    Row(
        Modifier
            .padding(20.dp)
            .clickable {
                onClick(articleLocal.toArticle())
            }) {
        Card(shape = RoundedCornerShape(15.dp)) {
            Image(
                painter = rememberImagePainter(articleLocal.urlToImage),
                contentDescription = null,
                Modifier.size(150.dp),
                contentScale = ContentScale.FillBounds
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(
                text = "#${articleLocal.author}",
                color = LightGreen,
                fontStyle = FontStyle.Italic,
                fontSize = 15.sp
            )
            Text(
                text = articleLocal.title.toString(),
                style = MaterialTheme.typography.h6,
                color = Color.Black
            )

            Text(
                text = "${
                    articleLocal.publishedAt?.substring(
                        0,
                        10
                    )
                } \u00b7 ${articleLocal.author} ",
                color = Color.Gray
            )
        }
    }


}