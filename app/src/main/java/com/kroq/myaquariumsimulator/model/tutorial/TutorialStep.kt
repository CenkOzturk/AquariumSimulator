package com.kroq.myaquariumsimulator.model.tutorial

enum class TutorialStep {
    NONE,
    WELCOME,
    OPEN_SHOP,
    TIER_INFO,
    BUY_FIRST_FISH,
    FEED_FISH,
    CLEAN,
    DAILY_TASK,
    FINISH
}

fun TutorialStep.toUiState() = when (this) {
    TutorialStep.WELCOME -> TutorialUiState(
        title = "Welcome to AquaVille!",
        message = "Let's build your first aquarium.",
        showContinue = true
    )

    TutorialStep.OPEN_SHOP -> TutorialUiState(
        title = "Open Shop",
        message = "Tap the Shop button to continue.",
        popupPosition = TutorialPopupPosition.TOP
    )

    TutorialStep.TIER_INFO -> TutorialUiState(
        title = "Unlock New Content",
        message = "Bronze, Silver and Gold tiers unlock new fish and decorations.",
        showContinue = true,
        popupPosition = TutorialPopupPosition.TOP
    )

    TutorialStep.BUY_FIRST_FISH -> TutorialUiState(
        title = "Buy Your First Fish",
        message = "Every aquarium needs a fish.",
        popupPosition = TutorialPopupPosition.TOP
    )

    TutorialStep.FEED_FISH -> TutorialUiState(
        title = "Feed Your First Fish",
        message = "Tap your fish to feed it.",
        popupPosition = TutorialPopupPosition.TOP
    )

    TutorialStep.CLEAN -> TutorialUiState(
        title = "Clean Your Aquarium",
        message = "Aquarium need clean.",
        popupPosition = TutorialPopupPosition.TOP
    )

    TutorialStep.DAILY_TASK -> TutorialUiState(
        title = "Daily Tasks",
        message = "Complete tasks to earn extra coins."
    )

    TutorialStep.FINISH -> TutorialUiState(
        title = "You're Ready!",
        message = "Enjoy AquaVille!",
        showContinue = true
    )

    TutorialStep.NONE -> TutorialUiState(
        title = "",
        message = ""
    )
}

fun TutorialStep.toBoundsType(): TutorialBoundsType? =
    when (this) {
        TutorialStep.OPEN_SHOP -> TutorialBoundsType.SHOP
        TutorialStep.BUY_FIRST_FISH -> TutorialBoundsType.FIRST_FISH
        TutorialStep.FEED_FISH -> TutorialBoundsType.FEED_FISH
        TutorialStep.CLEAN -> TutorialBoundsType.CLEAN
        TutorialStep.DAILY_TASK -> TutorialBoundsType.DAILY_TASK
        else -> null
    }