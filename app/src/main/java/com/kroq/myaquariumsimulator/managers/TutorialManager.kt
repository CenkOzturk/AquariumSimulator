package com.kroq.myaquariumsimulator.managers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInRoot
import com.kroq.myaquariumsimulator.model.tutorial.TutorialBoundsType
import com.kroq.myaquariumsimulator.model.tutorial.TutorialStep
import com.kroq.myaquariumsimulator.model.tutorial.toBoundsType

object TutorialManager {
    private val bounds = mutableStateMapOf<TutorialBoundsType, Rect>()
    val currentBounds: Rect? get() = bounds[currentStep.toBoundsType()]
    var currentStep by mutableStateOf(TutorialStep.NONE)
        private set

    fun next() {
        currentStep = when (currentStep) {

            TutorialStep.WELCOME -> TutorialStep.OPEN_SHOP

            TutorialStep.OPEN_SHOP -> TutorialStep.TIER_INFO

            TutorialStep.TIER_INFO -> TutorialStep.BUY_FIRST_FISH

            TutorialStep.BUY_FIRST_FISH -> TutorialStep.FEED_FISH

            TutorialStep.FEED_FISH -> TutorialStep.COLLECT_COIN

            TutorialStep.COLLECT_COIN -> TutorialStep.CLEAN

            TutorialStep.CLEAN -> TutorialStep.DAILY_TASK

            TutorialStep.DAILY_TASK -> TutorialStep.FINISH

            TutorialStep.FINISH -> TutorialStep.NONE

            TutorialStep.NONE -> TutorialStep.NONE
        }

        GameManager.update {
            it.copy(
                tutorialStep = currentStep.name,
                tutorialCompleted = currentStep == TutorialStep.NONE
            )
        }
    }

    fun skip() {
        GameManager.update {
            it.copy(
                tutorialStep = TutorialStep.NONE.name,
                tutorialCompleted = true
            )
        }
    }

    fun initialize() {
        currentStep =
            if (GameManager.state.tutorialCompleted) {
                TutorialStep.NONE
            } else {
                TutorialStep.WELCOME
            }
    }

    fun updateBounds(
        type: TutorialBoundsType,
        coordinates: LayoutCoordinates
    ) {
        bounds[type] = coordinates.boundsInRoot()
    }

    fun onShopOpened() {
        if (currentStep == TutorialStep.OPEN_SHOP) {
            next()
        }
    }

    fun onFirstFishBought() {
        if (currentStep == TutorialStep.BUY_FIRST_FISH) {
            next()
        }
    }

    fun onFeedFish() {
        if (currentStep == TutorialStep.FEED_FISH) {
            next()
        }
    }

    fun onCoinCollected() {
        if (currentStep == TutorialStep.COLLECT_COIN) {
            next()
        }
    }

    fun onCleanClicked() {
        if (currentStep == TutorialStep.CLEAN) {
            next()
        }
    }
}