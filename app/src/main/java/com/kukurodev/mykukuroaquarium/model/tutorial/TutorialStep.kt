package com.kukurodev.mykukuroaquarium.model.tutorial

import com.kukurodev.mykukuroaquarium.R

enum class TutorialStep {
    NONE,
    WELCOME,
    OPEN_SHOP,
    TIER_INFO,
    BUY_FIRST_FISH,
    FEED_FISH,
    COLLECT_COIN,
    CLEAN,
    DAILY_TASK,
    FINISH
}

fun TutorialStep.toUiState() = when (this) {
    TutorialStep.WELCOME -> TutorialUiState(
        titleResId = R.string.tutorial_welcome_title,
        messageResId = R.string.tutorial_welcome_message,
        showContinue = true
    )

    TutorialStep.OPEN_SHOP -> TutorialUiState(
        titleResId = R.string.tutorial_open_shop_title,
        messageResId = R.string.tutorial_open_shop_message,
        popupPosition = TutorialPopupPosition.TOP
    )

    TutorialStep.TIER_INFO -> TutorialUiState(
        titleResId = R.string.tutorial_tier_info_title,
        messageResId = R.string.tutorial_tier_info_message,
        showContinue = true,
        popupPosition = TutorialPopupPosition.TOP
    )

    TutorialStep.BUY_FIRST_FISH -> TutorialUiState(
        titleResId = R.string.tutorial_buy_first_fish_title,
        messageResId = R.string.tutorial_buy_first_fish_message,
        popupPosition = TutorialPopupPosition.TOP
    )

    TutorialStep.FEED_FISH -> TutorialUiState(
        titleResId = R.string.tutorial_feed_fish_title,
        messageResId = R.string.tutorial_feed_fish_message,
        popupPosition = TutorialPopupPosition.TOP
    )

    TutorialStep.COLLECT_COIN -> TutorialUiState(
        titleResId = R.string.tutorial_collect_coin_title,
        messageResId = R.string.tutorial_collect_coin_message,
        popupPosition = TutorialPopupPosition.TOP
    )

    TutorialStep.CLEAN -> TutorialUiState(
        titleResId = R.string.tutorial_clean_title,
        messageResId = R.string.tutorial_clean_message,
        popupPosition = TutorialPopupPosition.TOP
    )

    TutorialStep.DAILY_TASK -> TutorialUiState(
        titleResId = R.string.tutorial_daily_task_title,
        messageResId = R.string.tutorial_daily_task_message
    )

    TutorialStep.FINISH -> TutorialUiState(
        titleResId = R.string.tutorial_finish_title,
        messageResId = R.string.tutorial_finish_message,
        showContinue = true
    )

    TutorialStep.NONE -> TutorialUiState(
        titleResId = R.string.empty_string,
        messageResId = R.string.empty_string
    )
}

fun TutorialStep.toBoundsType(): TutorialBoundsType? =
    when (this) {
        TutorialStep.OPEN_SHOP -> TutorialBoundsType.SHOP
        TutorialStep.BUY_FIRST_FISH -> TutorialBoundsType.FIRST_FISH
        TutorialStep.FEED_FISH -> TutorialBoundsType.FEED_FISH
        TutorialStep.COLLECT_COIN -> TutorialBoundsType.COLLECT_COIN
        TutorialStep.CLEAN -> TutorialBoundsType.CLEAN
        TutorialStep.DAILY_TASK -> TutorialBoundsType.DAILY_TASK
        else -> null
    }