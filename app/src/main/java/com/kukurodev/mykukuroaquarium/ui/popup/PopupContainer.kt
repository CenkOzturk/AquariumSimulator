package com.kukurodev.mykukuroaquarium.ui.popup

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.kukurodev.mykukuroaquarium.R
import com.kukurodev.mykukuroaquarium.managers.AquariumManager
import com.kukurodev.mykukuroaquarium.managers.CleanerManager
import com.kukurodev.mykukuroaquarium.managers.DailyTaskManager
import com.kukurodev.mykukuroaquarium.managers.FishFoodManager
import com.kukurodev.mykukuroaquarium.managers.FishManager
import com.kukurodev.mykukuroaquarium.managers.GameManager
import com.kukurodev.mykukuroaquarium.managers.ItemManager
import com.kukurodev.mykukuroaquarium.managers.UpgradeManager
import com.kukurodev.mykukuroaquarium.managers.UpgradeManager.buyUpgrade
import com.kukurodev.mykukuroaquarium.managers.WelcomeGiftManager
import com.kukurodev.mykukuroaquarium.model.GameProgress
import com.kukurodev.mykukuroaquarium.model.GameUiState
import com.kukurodev.mykukuroaquarium.model.calculateTier
import com.kukurodev.mykukuroaquarium.model.item.CleanerDatabase.isCleaner
import com.kukurodev.mykukuroaquarium.model.item.FishFoodItemDatabase.isFood
import com.kukurodev.mykukuroaquarium.ui.popup.shop.ConfirmPopup
import com.kukurodev.mykukuroaquarium.ui.popup.shop.ShopPopup
import com.kukurodev.mykukuroaquarium.ui.popup.task.DailyTaskPopup
import com.kukurodev.mykukuroaquarium.ui.popup.upgrade.UpgradePopup
import com.kukurodev.mykukuroaquarium.ui.popup.welcome.WelcomeGiftPopup
import com.kukurodev.mykukuroaquarium.utils.Utils

@Composable
fun PopupContainer(uiState: GameUiState) {
    if (uiState.isShopOpen) {
        ShopPopup(
            onClose = {
                uiState.closeShop()
            },
            playerTier = GameProgress().calculateTier(),
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
                } else if (isCleaner(item.id))
                    CleanerManager.buyCleaner(item.id, item.price)
                else {
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

    if (uiState.showUpgrade) {
        UpgradePopup(
            selectedTab = uiState.selectedUpgradeTab,
            onTabSelected = { selected ->
                uiState.selectedUpgradeTab = selected
            },
            upgrades = UpgradeManager.getUpgrades(uiState.selectedUpgradeTab),
            onUpgradeClick = { upgrade ->
                buyUpgrade(
                    upgrade,
                )
                Utils.showToast("Level arttı.")
            },

            onClose = {
                uiState.closeUpgrade()
            }
        )
    }
}