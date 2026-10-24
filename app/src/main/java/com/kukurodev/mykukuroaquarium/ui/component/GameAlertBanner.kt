package com.kukurodev.mykukuroaquarium.ui.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kukurodev.mykukuroaquarium.managers.GameAlertManager

@Composable
fun GameAlertBanner(
    modifier: Modifier = Modifier
) {
    val currentAlert by GameAlertManager.currentAlert.collectAsState()

    AnimatedContent(
        targetState = currentAlert,
        modifier = modifier,
        transitionSpec = {
            if (targetState != null) {
                (slideInVertically(
                    initialOffsetY = { -it },
                    animationSpec = spring(dampingRatio = 0.65f, stiffness = 400f)
                ) + scaleIn(
                    initialScale = 0.85f,
                    animationSpec = spring(dampingRatio = 0.65f, stiffness = 400f)
                ) + fadeIn(tween(180))).togetherWith(
                    slideOutVertically(
                        targetOffsetY = { -it },
                        animationSpec = tween(150)
                    ) + scaleOut(
                        targetScale = 0.85f,
                        animationSpec = tween(150)
                    ) + fadeOut(tween(120))
                )
            } else {
                fadeIn(tween(100)).togetherWith(
                    slideOutVertically(
                        targetOffsetY = { -it },
                        animationSpec = tween(180)
                    ) + fadeOut(tween(150))
                )
            }
        },
        label = "GameAlertBannerAnimation"
    ) { alert ->
        if (alert == null) return@AnimatedContent

        val gradient = alert.type.gradient

        Surface(
            modifier = Modifier
                .fillMaxWidth(0.90f)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    GameAlertManager.dismiss()
                },
            shape = RoundedCornerShape(24.dp),
            color = Color.Transparent,
            shadowElevation = 8.dp
        ) {
            Box(
                modifier = Modifier
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                gradient.top,
                                gradient.light,
                                gradient.base
                            )
                        )
                    )
                    .border(
                        width = 3.dp,
                        brush = Brush.verticalGradient(
                            listOf(
                                gradient.border,
                                gradient.dark
                            )
                        ),
                        shape = RoundedCornerShape(24.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.verticalGradient(
                                    listOf(
                                        Color.White,
                                        gradient.light
                                    )
                                )
                            )
                            .border(2.dp, gradient.border, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = alert.icon,
                            fontSize = 20.sp
                        )
                    }

                    Spacer(Modifier.width(12.dp))

                    Text(
                        text = alert.message,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = gradient.border,
                        modifier = Modifier.weight(1f),
                        lineHeight = 18.sp
                    )

                    Spacer(Modifier.width(8.dp))

                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(gradient.border.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "✕",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = gradient.border
                        )
                    }
                }
            }
        }
    }
}
