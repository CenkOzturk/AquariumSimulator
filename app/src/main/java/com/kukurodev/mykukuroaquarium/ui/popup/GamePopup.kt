package com.kukurodev.mykukuroaquarium.ui.popup

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kukurodev.mykukuroaquarium.model.component.GameGradient
import com.kukurodev.mykukuroaquarium.ui.component.buttons.RoundedGameButton

@Composable
fun GamePopup(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null,
    gradient: GameGradient,
    onClose: () -> Unit,
    widthFraction: Float = 0.82f,
    buttonText: String? = null,
    buttonEnabled: Boolean = true,
    onButtonClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onClose()
            },
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(widthFraction)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {},
            shape = RoundedCornerShape(30.dp),
            color = gradient.top,
            shadowElevation = 18.dp,
            border = BorderStroke(
                width = 3.5.dp,
                brush = Brush.verticalGradient(
                    listOf(
                        gradient.border,
                        gradient.dark,
                        gradient.border
                    )
                )
            )
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
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 20.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 28.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = title,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = gradient.border,
                            textAlign = TextAlign.Center
                        )

                        subtitle?.let { sub ->
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = sub,
                                fontSize = 13.sp,
                                color = gradient.border.copy(alpha = 0.85f),
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                lineHeight = 16.sp
                            )
                        }
                    }

                    Spacer(Modifier.height(18.dp))

                    content()

                    buttonText?.let { btnText ->
                        onButtonClick?.let { btnAction ->
                            Spacer(Modifier.height(20.dp))

                            RoundedGameButton(
                                modifier = Modifier.fillMaxWidth(),
                                enabled = buttonEnabled,
                                text = btnText,
                                onClick = btnAction
                            )
                        }
                    }
                }

                CornerCloseTab(
                    modifier = Modifier.align(Alignment.TopEnd),
                    gradient = gradient,
                    onClose = onClose
                )
            }
        }
    }
}

@Composable
fun CornerCloseTab(
    gradient: GameGradient,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    var pressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.88f else 1.0f,
        animationSpec = spring(dampingRatio = 0.6f, stiffness = 500f),
        label = "CloseTabScale"
    )

    val quarterCircleShape = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 30.dp,
        bottomStart = 48.dp,
        bottomEnd = 0.dp
    )

    Box(
        modifier = modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .size(48.dp)
            .clip(quarterCircleShape)
            .background(
                Brush.verticalGradient(
                    listOf(
                        gradient.dark,
                        gradient.border
                    )
                )
            )
            .border(
                width = 2.5.dp,
                color = gradient.border,
                shape = quarterCircleShape
            )
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        pressed = true
                        tryAwaitRelease()
                        pressed = false
                        onClose()
                    }
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "✕",
            fontSize = 17.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White,
            modifier = Modifier.offset(x = 2.dp, y = (-2).dp)
        )
    }
}
