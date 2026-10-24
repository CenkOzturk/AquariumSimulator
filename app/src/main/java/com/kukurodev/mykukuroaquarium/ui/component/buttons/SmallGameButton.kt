package com.kukurodev.mykukuroaquarium.ui.component.buttons

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kukurodev.mykukuroaquarium.model.component.GameColors.OceanGradient

@Composable
fun SmallGameButton(
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed && enabled) 0.92f else 1.0f,
        animationSpec = spring(dampingRatio = 0.6f, stiffness = 500f),
        label = "SmallButtonScale"
    )

    Surface(
        modifier = modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .height(36.dp),
        shape = RoundedCornerShape(16.dp),
        color = Color.Transparent,
        shadowElevation = 4.dp
    ) {
        Box(
            modifier = Modifier
                .background(OceanGradient)
                .border(
                    width = 2.dp,
                    color = Color(0xFF0077C2),
                    shape = RoundedCornerShape(16.dp)
                )
                .clickable(
                    enabled = enabled,
                    indication = null,
                    interactionSource = interactionSource
                ) {
                    onClick()
                }
                .padding(horizontal = 14.dp, vertical = 6.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .padding(horizontal = 4.dp, vertical = 1.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color.White.copy(alpha = 0.35f),
                                Color.Transparent
                            )
                        )
                    )
            )

            Text(
                text = text,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 13.sp,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
    }
}
