package com.kroq.myaquariumsimulator.managers

import androidx.compose.runtime.mutableStateListOf
import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.model.upgrade.UpgradeCategoryTab
import com.kroq.myaquariumsimulator.model.upgrade.UpgradeDatabase
import com.kroq.myaquariumsimulator.model.upgrade.UpgradeModel
import com.kroq.myaquariumsimulator.model.upgrade.UpgradeState
import com.kroq.myaquariumsimulator.model.upgrade.UpgradeType
import com.kroq.myaquariumsimulator.model.upgrade.getLevel
import com.kroq.myaquariumsimulator.model.upgrade.toUpgradeStateModel
import com.kroq.myaquariumsimulator.utils.Utils

object UpgradeManager {
    val upgrades = mutableStateListOf<UpgradeModel>()

    fun initializeUpgrades() {
        upgrades.clear()
        upgrades.addAll(UpgradeDatabase.getAllUpgrades())

        if (GameManager.state.ownedUpgrades.list.isNotEmpty()) {
            for (item in GameManager.state.ownedUpgrades.list) {
                upgrades.find { it.id == item.id }?.currentLevel = item.currentLevel
            }
        }
    }

    fun buyUpgrade(upgradeModel: UpgradeModel) {
        val newCurrentLevel = upgradeModel.currentLevel + 1

        if (!CoinManager.spendCoins(upgradeModel.getLevel(upgradeModel.currentLevel).value)) {
            Utils.showToast(R.string.shop_no_coin_error)
            return
        }

        val updatedUpgrades = upgrades.map { upgrade ->
            if (upgrade.id == upgradeModel.id) {
                upgrade.copy(currentLevel = newCurrentLevel)
            } else {
                upgrade
            }
        }

        upgrades.clear()
        upgrades.addAll(updatedUpgrades)

        GameManager.update { state ->
            state.copy(
                ownedUpgrades = UpgradeState(
                    list = upgrades.map {
                        it.toUpgradeStateModel()
                    }
                )
            )
        }
    }

    fun getUpgradeValue(type: UpgradeType): Int {
        val upgrade = upgrades.firstOrNull { it.type == type } ?: return 1
        if (upgrade.currentLevel <= 0) {
            return 1
        }
        return upgrade.getLevel(upgrade.currentLevel).value
    }

    fun getUpgrades(
        type: UpgradeType
    ): List<UpgradeModel> {
        return upgrades.filter {
            it.type == type
        }
    }

    fun getUpgrades(
        category: UpgradeCategoryTab
    ): List<UpgradeModel> {
        return upgrades.filter {
            it.category == category
        }
    }
}