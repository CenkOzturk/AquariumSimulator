package com.kroq.myaquariumsimulator.ui.component.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kroq.myaquariumsimulator.model.component.GameColors.GrayGradient
import com.kroq.myaquariumsimulator.model.component.GameColors.OceanGradient

@Composable
fun RoundedGameButton(
    text: String,
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val background =
        if (enabled) {
            OceanGradient
        } else {
            GrayGradient
        }

    Box(
        modifier = modifier
            .height(46.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(background)
            .border(
                2.dp,
                Color.White.copy(alpha = .65f),
                RoundedCornerShape(18.dp)
            )
            .clickable(
                enabled = enabled,
                indication = null,
                interactionSource = remember {
                    MutableInteractionSource()
                }
            ) {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = if (enabled)
                Color.White
            else
                Color(0xFF5F6D75)
        )
    }
}