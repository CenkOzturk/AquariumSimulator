package com.kukurodev.mykukuroaquarium.ui.tutorial

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kukurodev.mykukuroaquarium.R
import com.kukurodev.mykukuroaquarium.managers.ScreenManager
import com.kukurodev.mykukuroaquarium.managers.TutorialManager
import com.kukurodev.mykukuroaquarium.model.component.GameColors
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

        // Prominent Screen Top-Right Skip Button (Same Y plane as Game HUD: top = 40.dp, end = 16.dp)
        val redGradient = GameColors.Red
        Surface(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 40.dp, end = 16.dp)
                .clickable {
                    TutorialManager.skip()
                },
            shape = RoundedCornerShape(20.dp),
            color = Color.Transparent,
            shadowElevation = 8.dp
        ) {
            Box(
                modifier = Modifier
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                redGradient.top,
                                redGradient.light,
                                redGradient.base
                            )
                        )
                    )
                    .border(
                        width = 2.5.dp,
                        color = redGradient.border,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.btn_skip),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = redGradient.border
                )
            }
        }

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
