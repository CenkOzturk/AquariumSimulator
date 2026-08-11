package com.kroq.myaquariumsimulator.model.tutorial

enum class TutorialPopupPosition {
    TOP,
    BOTTOM
}

fun TutorialStep.popupPosition(): TutorialPopupPosition =
    when (this) {
        TutorialStep.OPEN_SHOP,
        TutorialStep.BUY_FIRST_FISH -> TutorialPopupPosition.TOP
        else ->
            TutorialPopupPosition.BOTTOM
    }