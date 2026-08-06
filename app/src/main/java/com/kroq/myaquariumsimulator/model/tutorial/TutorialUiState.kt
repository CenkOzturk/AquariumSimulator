package com.kroq.myaquariumsimulator.model.tutorial

data class TutorialUiState(
    val title: String,
    val message: String,
    val showContinue: Boolean = false,
    val popupPosition: TutorialPopupPosition = TutorialPopupPosition.BOTTOM
)