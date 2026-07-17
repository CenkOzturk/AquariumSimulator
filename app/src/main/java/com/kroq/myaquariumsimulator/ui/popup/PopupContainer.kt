package com.kroq.myaquariumsimulator.ui.popup

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.managers.AquariumManager
import com.kroq.myaquariumsimulator.managers.DailyTaskManager
import com.kroq.myaquariumsimulator.managers.FishFoodManager
import com.kroq.myaquariumsimulator.managers.FishManager
import com.kroq.myaquariumsimulator.managers.GameManager
import com.kroq.myaquariumsimulator.managers.ItemManager
import com.kroq.myaquariumsimulator.managers.WelcomeGiftManager
import com.kroq.myaquariumsimulator.model.GameProgress
import com.kroq.myaquariumsimulator.model.GameUiState
import com.kroq.myaquariumsimulator.model.calculateTier
import com.kroq.myaquariumsimulator.model.item.FishFoodItemDatabase.isFood
import com.kroq.myaquariumsimulator.ui.popup.shop.ConfirmPopup
import com.kroq.myaquariumsimulator.ui.popup.shop.ShopPopup
import com.kroq.myaquariumsimulator.ui.popup.task.DailyTaskPopup
import com.kroq.myaquariumsimulator.ui.popup.welcome.WelcomeGiftPopup
import com.kroq.myaquariumsimulator.utils.Utils

@Composable
fun PopupContainer(uiState: GameUiState) {
    if (uiState.isShopOpen) {
        ShopPopup(
            onClose = {
                uiState.closeShop()
            },
            playerTier = GameProgress(
                AquariumManager.currentAquarium.type,
                ItemManager.items.map { it.type }
            ).calculateTier(),
            onTankSelected = {
                uiState.openConfirm(it)
            },
            onFishSelected = { fish ->
                FishManager.buy(fish.id, fish.price)
                uiState.closeShop()
            },
            onItemSelected = { item ->

                if (isFood(item.id)) {
                    FishFoodManager.buyFood(item.id, item.price)
                } else {
                    ItemManager.buy(item.id, item.price)
                }

                uiState.closeShop()
            }
        )
    }

    if (uiState.showConfirm) {
        ConfirmPopup(
            onNo = {
                uiState.closeConfirm()
            },
            onYes = {

                AquariumManager.upgrade(
                    uiState.selectedTank
                )

                uiState.closeConfirm()
                uiState.closeShop()
            }
        )
    }

    if (uiState.showDailyTasks) {
        GameManager.state.dailyTask?.let { dailyTask ->
            if (dailyTask.claimed) {
                Utils.showToast(
                    stringResource(R.string.already_received_reward)
                )
                uiState.closeDailyTasks()

            } else {
                DailyTaskPopup(
                    tasks = dailyTask.tasks,
                    allCompleted = dailyTask.isCompleted,
                    totalReward = dailyTask.totalReward,
                    onCollect = {
                        DailyTaskManager.claimReward()
                        uiState.closeDailyTasks()
                    },
                    onClose = {
                        uiState.closeDailyTasks()
                    }
                )
            }
        }
    }

    if (uiState.showWelcomeGift) {
        WelcomeGiftPopup(
            currentDay = WelcomeGiftManager.currentDay(),
            gift = WelcomeGiftManager.currentGift(),
            canClaim = WelcomeGiftManager.canClaim(),
            claimedToday = GameManager.state.welcomeGiftClaimed,
            onClaim = {
                WelcomeGiftManager.claimReward()
            },
            onClose = {
                uiState.closeWelcomeGift()
            }
        )
    }
}