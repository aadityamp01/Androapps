package com.shaun.newsbreeze.presentation.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.shaun.newsbreeze.R

@ExperimentalMaterialApi
@Composable
fun NewsViewToolbar(onSaveClick: () -> Unit,isSaved:Boolean ,onBackClick: () -> Unit,onDelete:()->Unit) {


    Row(
        Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 30.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(modifier = Modifier
            .clickable {
                Log.d("TAG", "NewsViewToolbar: Emiited")
                onBackClick()
            }
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "",
                alignment = Alignment.TopCenter,
                modifier = Modifier
                    .size(34.dp)


            )
        }
        Image(
            painter = if (isSaved) painterResource(id = R.drawable.ic_saved) else painterResource(id = R.drawable.ic_not_saved),
            contentDescription = "",
            alignment = Alignment.TopCenter,
            modifier = Modifier
                .size(32.dp)
                .clickable {

                   if(isSaved)
                   {onDelete()

                   }else
                       onSaveClick()
                }
        )

    }
}