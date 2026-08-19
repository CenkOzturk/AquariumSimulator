package com.kroq.myaquariumsimulator.ui.popup.shop

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kroq.myaquariumsimulator.model.PlayerTier

@Composable
fun TierInfoRow(
    tier: PlayerTier,
    title: String,
    description: String,
    icon: String
) {

    val tierColor = when (tier) {
        PlayerTier.FREE -> Color(0xFF66BB6A)
        PlayerTier.BRONZE -> Color(0xFFCD7F32)
        PlayerTier.SILVER -> Color(0xFF9E9E9E)
        PlayerTier.GOLD -> Color(0xFFFFB300)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(
                Brush.horizontalGradient(
                    listOf(
                        tierColor.copy(alpha = .10f),
                        Color.White
                    )
                )
            )
            .border(
                width = 1.5.dp,
                color = tierColor.copy(alpha = .45f),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(
                horizontal = 14.dp,
                vertical = 12.dp
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(tierColor.copy(alpha = .18f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = icon,
                    fontSize = 21.sp
                )
            }

            Spacer(Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = tierColor
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = description,
                    fontSize = 11.sp,
                    lineHeight = 15.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}