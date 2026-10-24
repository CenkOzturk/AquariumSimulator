package com.kukurodev.mykukuroaquarium.ui.component.buttons

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import com.kukurodev.mykukuroaquarium.model.component.GameColors.GrayGradient
import com.kukurodev.mykukuroaquarium.model.component.GameColors.OceanGradient

@Composable
fun RoundedGameButton(
    text: String,
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed && enabled) 0.93f else 1.0f,
        animationSpec = spring(dampingRatio = 0.6f, stiffness = 500f),
        label = "ButtonScale"
    )

    val background = if (enabled) {
        OceanGradient
    } else {
        GrayGradient
    }

    val borderColor = if (enabled) {
        Color(0xFF0077C2)
    } else {
        Color(0xFF8A99A2)
    }

    Surface(
        modifier = modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .height(48.dp),
        shape = RoundedCornerShape(20.dp),
        color = Color.Transparent,
        shadowElevation = if (enabled) 6.dp else 2.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(background)
                .border(
                    width = 2.5.dp,
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.White.copy(alpha = 0.85f),
                            borderColor
                        )
                    ),
                    shape = RoundedCornerShape(20.dp)
                )
                .clickable(
                    enabled = enabled,
                    indication = null,
                    interactionSource = interactionSource
                ) {
                    onClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp, vertical = 2.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color.White.copy(alpha = 0.40f),
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.20f)
                            )
                        )
                    )
            )

            Text(
                modifier = Modifier.padding(horizontal = 12.dp),
                text = text,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                color = if (enabled)
                    Color.White
                else
                    Color(0xFF5F6D75)
            )
        }
    }
}
