package com.kukurodev.mykukuroaquarium.ui.component.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kukurodev.mykukuroaquarium.R

@Composable
fun DailyTaskButton(
    modifier: Modifier = Modifier,
    hasAnyTask: Boolean,
    hasClaimableReward: Boolean,
    onClick: () -> Unit
) {
    if (!hasAnyTask) return

    Box(
        modifier = modifier
            .size(64.dp)
            .clip(CircleShape)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFB8F5C8),
                        Color(0xFF6DDC8A)
                    )
                )
            )
            .border(
                width = 2.dp,
                color = Color(0xFF35A85A),
                shape = CircleShape
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {

        // Hafif parlaklık
        Box(
            modifier = Modifier
                .size(46.dp)
                .clip(CircleShape)
                .background(
                    Color.White.copy(alpha = 0.16f)
                )
        )

        Image(
            painter = painterResource(R.drawable.ic_daily_tasks),
            contentDescription = null,
            modifier = Modifier.size(52.dp)
        )

        if (hasClaimableReward) {
            Box(
                modifier = Modifier
                    .size(17.dp)
                    .offset(x = 5.dp, y = (-5).dp)
                    .align(Alignment.TopEnd)
                    .background(
                        Color(0xFFFF4D4D),
                        CircleShape
                    )
                    .border(
                        2.dp,
                        Color.White,
                        CircleShape
                    )
            )
        }
    }
}