package com.kukurodev.mykukuroaquarium.ui.tutorial

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.kukurodev.mykukuroaquarium.managers.ScreenManager
import com.kukurodev.mykukuroaquarium.managers.TutorialManager
import com.kukurodev.mykukuroaquarium.model.tutorial.TutorialStep
import com.kukurodev.mykukuroaquarium.model.tutorial.toUiState
import com.kukurodev.mykukuroaquarium.utils.Utils.toDp

@Composable
fun TutorialOverlay() {
    val step = TutorialManager.currentStep
    if (step == TutorialStep.NONE) return

    val state = step.toUiState()
    val bounds = TutorialManager.currentBounds
    val density = LocalDensity.current

    var popupHeightPx by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        TutorialSpotlight()

        if (state.showContinue || bounds == null) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 16.dp)
            ) {
                TutorialPopup()
            }
        } else {
            val targetTopDp = bounds.top.toDp()
            val targetBottomDp = bounds.bottom.toDp()
            val popupHeightDp = with(density) { popupHeightPx.toDp() }

            val idealYDp = targetTopDp - popupHeightDp - 16.dp

            val finalYDp = if (idealYDp < 24.dp) {
                (targetBottomDp + 16.dp).coerceAtMost(ScreenManager.screenHeight.dp - popupHeightDp - 24.dp)
            } else {
                idealYDp
            }

            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset { IntOffset(0, with(density) { finalYDp.toPx().toInt() }) }
                    .padding(horizontal = 12.dp)
                    .onGloballyPositioned {
                        popupHeightPx = it.size.height
                    }
            ) {
                TutorialPopup()
            }
        }
    }
}
