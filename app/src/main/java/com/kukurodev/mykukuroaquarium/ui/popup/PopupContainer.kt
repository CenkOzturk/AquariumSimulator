package com.kukurodev.mykukuroaquarium.ui.popup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kukurodev.mykukuroaquarium.R
import com.kukurodev.mykukuroaquarium.data.Constants.WELCOME_GIFT_DAYS
import com.kukurodev.mykukuroaquarium.managers.AquariumManager
import com.kukurodev.mykukuroaquarium.managers.CleanerManager
import com.kukurodev.mykukuroaquarium.managers.DailyTaskManager
import com.kukurodev.mykukuroaquarium.managers.FishFoodManager
import com.kukurodev.mykukuroaquarium.managers.FishLifeManager
import com.kukurodev.mykukuroaquarium.managers.FishManager
import com.kukurodev.mykukuroaquarium.managers.GameManager
import com.kukurodev.mykukuroaquarium.managers.ItemManager
import com.kukurodev.mykukuroaquarium.managers.UpgradeManager
import com.kukurodev.mykukuroaquarium.managers.UpgradeManager.buyUpgrade
import com.kukurodev.mykukuroaquarium.managers.WelcomeGiftManager
import com.kukurodev.mykukuroaquarium.model.GameProgress
import com.kukurodev.mykukuroaquarium.model.GameUiState
import com.kukurodev.mykukuroaquarium.model.calculateTier
import com.kukurodev.mykukuroaquarium.model.component.GameColors
import com.kukurodev.mykukuroaquarium.model.item.CleanerDatabase.isCleaner
import com.kukurodev.mykukuroaquarium.model.item.FishFoodItemDatabase.isFood
import com.kukurodev.mykukuroaquarium.ui.popup.inventory.InventoryPopUp
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
                    stringResource(R.string.daily_task_already_received_reward)
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

    if (uiState.showWelcomeGift && GameManager.state.welcomeGiftDay != WELCOME_GIFT_DAYS) {
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
                buyUpgrade(upgrade)
            },

            onClose = {
                uiState.closeUpgrade()
            }
        )
    }

    if (uiState.showInventory) {
        InventoryPopUp(
            onClose = {
                uiState.closeInventory()
            }
        )
    }

    if (uiState.showFishDeathPopup) {
        val deadFishes = FishLifeManager.deadFishes

        GamePopup(
            title = stringResource(R.string.fish_death_title),
            subtitle = stringResource(R.string.fish_death_subtitle),
            gradient = GameColors.Red,
            buttonText = stringResource(R.string.ok),
            buttonEnabled = true,
            onButtonClick = {
                FishLifeManager.acknowledgeDeathNotice()
                uiState.closeFishDeathPopup()
            },
            onClose = {
                FishLifeManager.acknowledgeDeathNotice()
                uiState.closeFishDeathPopup()
            }
        ) {
            if (deadFishes.isNotEmpty()) {
                Spacer(Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    deadFishes.take(4).forEach { fish ->
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 6.dp)
                                .size(56.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.85f))
                                .border(2.dp, GameColors.Red.border, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(fish.resId),
                                contentDescription = null,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .size(42.dp)
                                    .alpha(0.85f)
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(8.dp))
        }
    }
}
