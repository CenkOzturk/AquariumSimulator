package com.kukurodev.mykukuroaquarium.ui.component.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kukurodev.mykukuroaquarium.model.component.GameGradient

@Composable
fun MenuButton(
    modifier: Modifier = Modifier,
    gradient: GameGradient,
    imgResId: Int,
    imgSize: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(64.dp)
            .clip(CircleShape)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        gradient.light,
                        gradient.base,
                        gradient.dark
                    )
                )
            )
            .border(
                width = 3.dp,
                color = gradient.border,
                shape = CircleShape
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(
                    bounded = true
                ),
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        // Hafif parlak iç alan
        Box(
            modifier = Modifier
                .size(46.dp)
                .clip(CircleShape)
                .background(
                    Color.White.copy(alpha = 0.12f)
                )
        )

        Image(
            painter = painterResource(imgResId),
            contentDescription = null,
            modifier = Modifier.size(imgSize.dp)
        )

        /*if (hasClaimableReward) {
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
        }*/
    }
}