package com.shaun.newsbreeze.presentation.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.shaun.newsbreeze.ui.theme.ShimmerColorShades


@Composable
fun shimmerAnimation(
): Brush {

    val transition = rememberInfiniteTransition()
    val translateAnim by transition.animateFloat(

        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(


            tween(durationMillis = 1200, easing = FastOutSlowInEasing),
            RepeatMode.Reverse
        )
    )


    return Brush.linearGradient(
        colors = ShimmerColorShades,
        start = Offset(10f, 10f),
        end = Offset(translateAnim, translateAnim)
    )

}


@Composable
fun ShimmerItem(
    brush: Brush = shimmerAnimation(),
    size: Int = 250,
    showBottomLine: Boolean = true,
    paddingStart:Int=30,
    paddingEnd:Int=30,
    paddingTop:Int=20

) {


    Column(modifier = Modifier.padding(start = paddingStart.dp, end = paddingEnd.dp, top = paddingTop.dp)) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .size(size = size.dp),
            shape = RoundedCornerShape(10)
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .background(brush = brush)
            ) {

            }
        }

        if (showBottomLine)
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .padding(vertical = 8.dp)
                    .background(brush = brush)
            )
    }
}