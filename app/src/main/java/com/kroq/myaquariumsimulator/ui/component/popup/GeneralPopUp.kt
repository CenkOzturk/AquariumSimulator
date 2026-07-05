package com.kroq.myaquariumsimulator.ui.component.popup

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun GeneralPopup(
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
    dismissOnOutsideClick: Boolean = true,
    content: @Composable BoxScope.(
        popupModifier: Modifier,
        dismiss: () -> Unit
    ) -> Unit
) {

    val scope = rememberCoroutineScope()

    val offsetY = remember { Animatable(1000f) }
    val alpha = remember { Animatable(0f) }
    val scale = remember { Animatable(.95f) }

    LaunchedEffect(Unit) {

        launch {
            offsetY.animateTo(
                targetValue = 0f,
                animationSpec = tween(450)
            )
        }

        launch {
            alpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(250)
            )
        }

        launch {
            scale.animateTo(
                targetValue = 1f,
                animationSpec = tween(300)
            )
        }
    }

    fun dismiss() {

        scope.launch {

            coroutineScope {

                launch {
                    offsetY.animateTo(
                        targetValue = 1000f,
                        animationSpec = tween(300)
                    )
                }

                launch {
                    alpha.animateTo(
                        targetValue = 0f,
                        animationSpec = tween(200)
                    )
                }

                launch {
                    scale.animateTo(
                        targetValue = .95f,
                        animationSpec = tween(200)
                    )
                }
            }

            onClose()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color.Black.copy(.45f * alpha.value)
            )
            .then(
                if (dismissOnOutsideClick) {

                    Modifier.clickable(
                        indication = null,
                        interactionSource = remember {
                            MutableInteractionSource()
                        }
                    ) {
                        dismiss()
                    }

                } else {

                    Modifier
                }
            ),
        contentAlignment = Alignment.Center
    ) {

        val popupModifier = modifier
            .offset {
                IntOffset(
                    0,
                    offsetY.value.toInt()
                )
            }
            .graphicsLayer {
                this.alpha = alpha.value
                scaleX = scale.value
                scaleY = scale.value
            }

        content(
            popupModifier,
            ::dismiss
        )
    }
}