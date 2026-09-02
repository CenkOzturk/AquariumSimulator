package com.kukurodev.mykukuroaquarium.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.kukurodev.mykukuroaquarium.managers.AquariumManager
import com.kukurodev.mykukuroaquarium.managers.BubbleManager
import com.kukurodev.mykukuroaquarium.managers.CoinLoop
import com.kukurodev.mykukuroaquarium.managers.DailyTaskManager
import com.kukurodev.mykukuroaquarium.managers.DirtManager
import com.kukurodev.mykukuroaquarium.managers.FishManager
import com.kukurodev.mykukuroaquarium.managers.GameManager
import com.kukurodev.mykukuroaquarium.managers.GoldFishManager
import com.kukurodev.mykukuroaquarium.managers.ScreenManager
import com.kukurodev.mykukuroaquarium.managers.TutorialManager
import com.kukurodev.mykukuroaquarium.managers.WelcomeGiftManager
import com.kukurodev.mykukuroaquarium.model.GameProgress
import com.kukurodev.mykukuroaquarium.model.calculateTier
import com.kukurodev.mykukuroaquarium.model.loadGameState
import com.kukurodev.mykukuroaquarium.model.rememberGameUiState
import com.kukurodev.mykukuroaquarium.model.tutorial.TutorialStep
import com.kukurodev.mykukuroaquarium.ui.aquarium.AquariumView
import com.kukurodev.mykukuroaquarium.ui.component.GameHud
import com.kukurodev.mykukuroaquarium.ui.component.RightMenu
import com.kukurodev.mykukuroaquarium.ui.popup.PopupContainer
import com.kukurodev.mykukuroaquarium.ui.tutorial.TutorialOverlay
import com.kukurodev.mykukuroaquarium.utils.Utils
import kotlinx.coroutines.delay

@Composable
fun GameScreen() {
    val context = LocalContext.current
    val screenWidth = LocalConfiguration.current.screenWidthDp.toFloat()
    val screenHeight = LocalConfiguration.current.screenHeightDp.toFloat()
    val lifecycleOwner = LocalLifecycleOwner.current
    val uiState = rememberGameUiState()

    LaunchedEffect(Unit) {
        val loaded = loadGameState(context)
        GameManager.initialize(loaded)
        Utils.init(context)
        TutorialManager.initialize()
        WelcomeGiftManager.refreshIfNeeded()
        DailyTaskManager.refreshIfNeeded(GameProgress().calculateTier())
        DirtManager.initialize(AquariumManager.currentAquarium)
        CoinLoop.start(lifecycleOwner)
    }

    LaunchedEffect(screenWidth, screenHeight) {
        while (true) {
            val aquarium = AquariumManager.currentAquarium
            FishManager.fishMove(aquarium)
            GoldFishManager.update(screenWidth, screenHeight)
            GoldFishManager.move(screenWidth)
            BubbleManager.update(aquarium)
            DirtManager.update(aquarium)
            delay(16)
        }
    }

    ScreenManager.init(screenWidth, screenHeight)

    Box(modifier = Modifier.fillMaxSize()) {
        Background()

        AquariumView(
            remember(
                GameManager.state.aquariumType,
                screenWidth,
                screenHeight
            ) {
                AquariumManager.currentAquarium
            }
        )

        GameHud(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp),
            uiState = uiState
        )

        RightMenu(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp),
            uiState = uiState
        )

        PopupContainer(
            uiState = uiState
        )

        if (!GameManager.state.tutorialCompleted &&
            GameManager.state.tutorialStep != TutorialStep.NONE.name)
            TutorialOverlay()
    }
}
