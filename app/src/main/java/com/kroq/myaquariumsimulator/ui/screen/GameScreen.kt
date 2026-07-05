package com.kroq.myaquariumsimulator.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.kroq.myaquariumsimulator.ui.component.aquarium.AquariumView
import com.kroq.myaquariumsimulator.ui.component.Background
import com.kroq.myaquariumsimulator.ui.component.shop.ConfirmPopup
import com.kroq.myaquariumsimulator.ui.component.aquarium.ResourceBadge
import com.kroq.myaquariumsimulator.ui.component.shop.ShopPopup
import com.kroq.myaquariumsimulator.ui.component.shop.ShopButton
import com.kroq.myaquariumsimulator.utils.Utils
import kotlinx.coroutines.delay
import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.game.DailyTaskManager
import com.kroq.myaquariumsimulator.game.SaveManager
import com.kroq.myaquariumsimulator.ui.component.popup.DailyTaskButton
import com.kroq.myaquariumsimulator.ui.component.popup.DailyTaskPopup
import com.kroq.myaquariumsimulator.ui.component.popup.WelcomeGiftPopup

@Composable
fun GameScreen() {
    val context = LocalContext.current
    val screenWidth = LocalConfiguration.current.screenWidthDp.toFloat()
    val screenHeight = LocalConfiguration.current.screenHeightDp.toFloat()
    val lifecycleOwner = LocalLifecycleOwner.current

    var isShopOpen by remember { mutableStateOf(false) }
    var selectedTankState by remember {mutableStateOf(AquariumType.SMALL)}
    var showConfirm by remember { mutableStateOf(false) }
    var showTasks by remember { mutableStateOf(false) }
    var showDailyGift by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        val loaded = loadGameState(context)
        SaveManager.init(context)
        GameManager.initialize(loaded)
        DailyTaskManager.refreshIfNeeded(
            GameProgress(
                AquariumManager.currentAquarium.type,
                ItemManager.items.map { it.type }
            ).calculateTier()
        )
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

        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ResourceBadge(
                    stringResource(
                        R.string.coin_value, GameManager.state.coins
                    )
                )
                ResourceBadge(
                    stringResource(
                        R.string.fish_food_value, GameManager.state.foodCount
                    )
                )
            }

            DailyTaskButton(
                modifier = Modifier.padding(top = 24.dp),
                hasAnyTask = true,
                hasClaimableReward = true,
                onClick = {
                    showTasks = true
                }
            )

            DailyTaskButton(
                modifier = Modifier.padding(top = 24.dp),
                hasAnyTask = true,
                hasClaimableReward = true,
                onClick = {
                    showDailyGift = true
                }
            )
        }

        //RESET BUTTON
        Button(
            modifier = Modifier.align(Alignment.BottomStart),
            onClick = {
                GameManager.resetGame()
                Utils.showToast(R.string.btn_reset)
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

        if (isShopOpen) {
            ShopPopup(
                onClose = { isShopOpen = false },
                playerTier = GameProgress(
                    AquariumManager.currentAquarium.type,
                    ItemManager.items.map { it.type }
                ).calculateTier(),
                onTankSelected = { selectedTank ->
                    selectedTankState = selectedTank
                    showConfirm = true
                },
                onFishSelected = { fish ->
                    FishManager.buy(fish.id, fish.price)
                    isShopOpen = false
                },
                onItemSelected = { item ->
                    if (isFood(item.id)) {
                        FishFoodManager.buyFood(item.id, item.price)
                    } else {
                        ItemManager.buy(item.id, item.price)
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

        if (showTasks) {
            GameManager.state.dailyTask?.let { dailyTask ->
                if (dailyTask.claimed) {
                    Utils.showToast(stringResource(R.string.already_received_reward))
                    showTasks = false
                } else {
                    DailyTaskPopup(
                        tasks = dailyTask.tasks,
                        allCompleted = dailyTask.isCompleted,
                        totalReward = dailyTask.totalReward,
                        onCollect = {
                            DailyTaskManager.claimReward()
                            showTasks = false
                        },
                        onClose = { showTasks = false }
                    )
                }
            }
        }

        if (showDailyGift) {
            WelcomeGiftPopup(
                currentDay = 4,
                rewardText = "aaa",
                canClaim = true,
                claimedToday = false,
                onClaim = {},
                onClose = {
                    showDailyGift = false
                }
            )
        }
    }
}
