package com.kroq.myaquariumsimulator.ui.screen

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
import com.kroq.myaquariumsimulator.managers.AquariumManager
import com.kroq.myaquariumsimulator.managers.BubbleManager
import com.kroq.myaquariumsimulator.managers.CoinLoop
import com.kroq.myaquariumsimulator.managers.DailyTaskManager
import com.kroq.myaquariumsimulator.managers.DirtManager
import com.kroq.myaquariumsimulator.managers.FishManager
import com.kroq.myaquariumsimulator.managers.GameManager
import com.kroq.myaquariumsimulator.managers.GoldFishManager
import com.kroq.myaquariumsimulator.managers.ScreenManager
import com.kroq.myaquariumsimulator.managers.TutorialManager
import com.kroq.myaquariumsimulator.managers.WelcomeGiftManager
import com.kroq.myaquariumsimulator.model.GameProgress
import com.kroq.myaquariumsimulator.model.calculateTier
import com.kroq.myaquariumsimulator.model.loadGameState
import com.kroq.myaquariumsimulator.model.rememberGameUiState
import com.kroq.myaquariumsimulator.model.tutorial.TutorialStep
import com.kroq.myaquariumsimulator.ui.aquarium.AquariumView
import com.kroq.myaquariumsimulator.ui.component.GameHud
import com.kroq.myaquariumsimulator.ui.component.RightMenu
import com.kroq.myaquariumsimulator.ui.popup.PopupContainer
import com.kroq.myaquariumsimulator.ui.tutorial.TutorialOverlay
import com.kroq.myaquariumsimulator.utils.Utils
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
