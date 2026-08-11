package com.kroq.myaquariumsimulator.model.tutorial

import androidx.annotation.StringRes

data class TutorialUiState(
    @StringRes val titleResId: Int,
    @StringRes val messageResId: Int,
    val showContinue: Boolean = false,
    val popupPosition: TutorialPopupPosition = TutorialPopupPosition.BOTTOM
)