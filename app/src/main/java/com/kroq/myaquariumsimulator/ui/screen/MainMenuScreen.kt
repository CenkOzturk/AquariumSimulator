package com.kroq.myaquariumsimulator.ui.screen

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.model.component.GameColors
import com.kroq.myaquariumsimulator.ui.component.MenuBubbleBackground
import com.kroq.myaquariumsimulator.ui.component.buttons.GameMenuButton

@Composable
fun MainMenuScreen(
    onPlay: () -> Unit,
    onSettings: () -> Unit,
    onCredits: () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val logoOffset by infiniteTransition.animateFloat(
        initialValue = -4f,
        targetValue = 4f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2500,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF65D5FF),
                        Color(0xFF45C4F4),
                        Color(0xFF2AA7E3),
                        Color(0xFF1286CF)
                    )
                )
            )
    ) {

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(.12f)
                        ),
                        radius = 1200f
                    )
                )
        )

        MenuBubbleBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(
                Modifier.weight(.7f)
            )

            Box(
                contentAlignment = Alignment.Center
            ) {

                Box(
                    modifier = Modifier
                        .size(240.dp)
                        .blur(60.dp)
                        .background(
                            Color.White.copy(.12f),
                            CircleShape
                        )
                )

                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth(.72f)
                        .offset(y = logoOffset.dp)
                )
            }

            Spacer(
                Modifier.height(36.dp)
            )

            GameMenuButton(
                text = "PLAY",
                gradient = GameColors.Ocean,
                isPrimary = true,
                onClick = onPlay
            )

            Spacer(
                Modifier.height(16.dp)
            )

            GameMenuButton(
                text = "SETTINGS",
                gradient = GameColors.WelcomeGift,
                onClick = onSettings
            )

            Spacer(
                Modifier.height(16.dp)
            )

            GameMenuButton(
                text = "CREDITS",
                gradient = GameColors.Shop,
                onClick = onCredits
            )

            Spacer(
                Modifier.weight(1f)
            )

            Text(
                text = "Version 1.0.0",
                fontSize = 14.sp,
                color = Color.White.copy(.75f)
            )

            Spacer(
                Modifier.height(24.dp)
            )
        }
    }
}