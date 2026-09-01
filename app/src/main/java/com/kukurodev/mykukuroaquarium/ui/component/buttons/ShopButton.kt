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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kukurodev.mykukuroaquarium.R
import com.kukurodev.mykukuroaquarium.managers.TutorialManager
import com.kukurodev.mykukuroaquarium.model.tutorial.TutorialBoundsType

@Composable
fun ShopButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(64.dp)
            .clip(CircleShape)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF42A5F5),
                        Color(0xFF1E88E5),
                        Color(0xFF1565C0)
                    )
                )
            )
            .border(
                width = 2.dp,
                color = Color(0xFF0D47A1),
                shape = CircleShape
            )
            .onGloballyPositioned {
                TutorialManager.updateBounds(
                    TutorialBoundsType.SHOP,
                    it
                )
            }
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
            painter = painterResource(R.drawable.ic_shop),
            contentDescription = null,
            modifier = Modifier.size(52.dp)
        )
    }
}