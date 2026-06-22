package com.kroq.myaquariumsimulator.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.kroq.myaquariumsimulator.game.AquariumManager
import com.kroq.myaquariumsimulator.game.BubbleManager
import com.kroq.myaquariumsimulator.game.CoinLoop
import com.kroq.myaquariumsimulator.game.FishFoodManager
import com.kroq.myaquariumsimulator.game.FishManager
import com.kroq.myaquariumsimulator.game.GameManager
import com.kroq.myaquariumsimulator.game.ItemManager
import com.kroq.myaquariumsimulator.game.ScreenManager
import com.kroq.myaquariumsimulator.model.GameProgress
import com.kroq.myaquariumsimulator.model.aquarium.AquariumType
import com.kroq.myaquariumsimulator.model.calculateTier
import com.kroq.myaquariumsimulator.model.item.FishFoodItemDatabase.isFood
import com.kroq.myaquariumsimulator.model.loadGameState
import com.kroq.myaquariumsimulator.ui.component.AquariumView
import com.kroq.myaquariumsimulator.ui.component.Background
import com.kroq.myaquariumsimulator.ui.component.ConfirmPopup
import com.kroq.myaquariumsimulator.ui.component.ResourceBadge
import com.kroq.myaquariumsimulator.ui.component.ShopPopup
import com.kroq.myaquariumsimulator.ui.component.shop.ShopButton
import com.kroq.myaquariumsimulator.utils.Utils
import kotlinx.coroutines.delay
import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.game.SaveManager

@Composable
fun GameScreen() {
    val context = LocalContext.current
    val screenWidth = LocalConfiguration.current.screenWidthDp.toFloat()
    val screenHeight = LocalConfiguration.current.screenHeightDp.toFloat()
    val lifecycleOwner = LocalLifecycleOwner.current

    var isShopOpen by remember { mutableStateOf(false) }
    var selectedTankState by remember {mutableStateOf(AquariumType.SMALL)}
    var showConfirm by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        val loaded = loadGameState(context)
        SaveManager.init(context)
        GameManager.initialize(loaded)
        CoinLoop.start(lifecycleOwner)
    }

    LaunchedEffect(screenWidth, screenHeight) {
        while (true) {
            val aquarium = AquariumManager.currentAquarium
            FishManager.fishMove(aquarium)
            BubbleManager.update(aquarium)
            delay(16)
        }
    }

    ScreenManager.init(screenWidth, screenHeight)

    Box(modifier = Modifier.fillMaxSize()) {

        Background()

        val aquarium = remember(
            GameManager.state.aquariumType,
            screenWidth,
            screenHeight
        ) {
            AquariumManager.currentAquarium
        }

        AquariumView(aquarium)

        //RESET BUTTON
        Button(
            modifier = Modifier.align(Alignment.BottomStart),
            onClick = {
                GameManager.resetGame()
                Utils.showToast("RESET")
            }
        ) {
            Text(text = stringResource(R.string.btn_reset))
        }

        ShopButton(
            onClick = { isShopOpen = true },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
        )

        Row(
            modifier = Modifier.padding(20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            //ResourceBadge("💰 ${GameManager.state.coins}")
            ResourceBadge(
                stringResource(
                    R.string.coin_value, GameManager.state.coins)
            )
            ResourceBadge(
                stringResource(
                    R.string.fish_food_value, GameManager.state.foodCount)
            )
        }

        if (isShopOpen) {
            ShopPopup(
                context = context,
                onClose = { isShopOpen = false },
                playerTier = GameProgress(
                    AquariumManager.currentAquarium.type,
                    ItemManager.items.map { it.type }
                ).calculateTier(),
                onTankSelected = { selectedTank ->
                    selectedTankState = selectedTank
                    showConfirm = true
                },
                onFishSelected = { fishId ->
                    FishManager.buy(fishId)
                    isShopOpen = false
                },
                onItemSelected = { itemId ->
                    if (isFood(itemId)) {
                        FishFoodManager.buyFood(itemId)
                    } else {
                        ItemManager.buy(itemId)
                    }
                    isShopOpen = false
                }
            )
        }

        if (showConfirm) {
            ConfirmPopup(
                onNo = { showConfirm = false },
                onYes = {
                    AquariumManager.upgrade(selectedTankState)
                    showConfirm = false
                    isShopOpen = false
                }
            )
        }
    }
}
