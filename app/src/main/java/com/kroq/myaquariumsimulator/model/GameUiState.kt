package com.kroq.myaquariumsimulator.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.kroq.myaquariumsimulator.model.aquarium.AquariumType
import com.kroq.myaquariumsimulator.model.upgrade.UpgradeCategoryTab

@Stable
class GameUiState {
    var isShopOpen by mutableStateOf(false)
        private set

    var showUpgrade by mutableStateOf(false)
        private set

    var showFishUpgradeTree by mutableStateOf(false)
        private set

    var showConfirm by mutableStateOf(false)
        private set

    var showDailyTasks by mutableStateOf(false)
        private set

    var showWelcomeGift by mutableStateOf(false)
        private set

    var selectedUpgradeTab by mutableStateOf(UpgradeCategoryTab.FISH)
        set

    var selectedTank by mutableStateOf(AquariumType.SMALL)
        private set

    fun openShop() {
        isShopOpen = true
    }

    fun closeShop() {
        isShopOpen = false
    }

    fun openUpgrade(tab: UpgradeCategoryTab) {
        showUpgrade = true
        selectedUpgradeTab = tab
    }

    fun closeUpgrade() {
        showUpgrade = false
    }

    fun openFishUpgradeTree() {
        showFishUpgradeTree = true
    }

    fun closeFishUpgradeTree() {
        showFishUpgradeTree = false
    }

    fun openConfirm(tank: AquariumType) {
        selectedTank = tank
        showConfirm = true
    }

    fun closeConfirm() {
        showConfirm = false
    }

    fun openDailyTasks() {
        showDailyTasks = true
    }

    fun closeDailyTasks() {
        showDailyTasks = false
    }

    fun openWelcomeGift() {
        showWelcomeGift = true
    }

    fun closeWelcomeGift() {
        showWelcomeGift = false
    }
}

@Composable
fun rememberGameUiState(): GameUiState {
    return remember {
        GameUiState()
    }
}