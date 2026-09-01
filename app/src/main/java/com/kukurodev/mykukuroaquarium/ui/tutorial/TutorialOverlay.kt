package com.kukurodev.mykukuroaquarium.ui.tutorial

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kukurodev.mykukuroaquarium.managers.TutorialManager
import com.kukurodev.mykukuroaquarium.model.tutorial.TutorialPopupPosition
import com.kukurodev.mykukuroaquarium.model.tutorial.TutorialStep
import com.kukurodev.mykukuroaquarium.model.tutorial.toUiState

@Composable
fun TutorialOverlay() {

    if (TutorialManager.currentStep == TutorialStep.NONE) return

    val alignment =
        when (TutorialManager.currentStep.toUiState().popupPosition) {
            TutorialPopupPosition.TOP -> Alignment.TopCenter
            else -> Alignment.BottomCenter
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        TutorialSpotlight()

        Box(
            modifier = Modifier
                .align(alignment)
                .padding(
                    top = 32.dp,
                    bottom = 32.dp,
                    start = 20.dp,
                    end = 20.dp
                )
        ) {
            TutorialPopup()
        }
    }
}