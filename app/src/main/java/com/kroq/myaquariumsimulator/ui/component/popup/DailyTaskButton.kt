package com.kroq.myaquariumsimulator.ui.component.popup

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
            .background(Color(0xFFE8D8B5))
            .border(2.dp, Color(0xFF8B6B3D), CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = "📜",
            fontSize = 26.sp
        )

        // 🔴 OUTSIDE BADGE
        if (hasClaimableReward) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .offset(x = 6.dp, y = (-6).dp) // dışarı taşıyoruz
                    .align(Alignment.TopEnd)
                    .background(Color.Red, CircleShape)
                    .border(2.dp, Color.White, CircleShape)
            )
        }
    }
}