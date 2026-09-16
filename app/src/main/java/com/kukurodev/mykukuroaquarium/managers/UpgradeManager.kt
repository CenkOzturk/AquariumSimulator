package com.kukurodev.mykukuroaquarium.managers

import androidx.compose.runtime.mutableStateListOf
import com.kukurodev.mykukuroaquarium.model.upgrade.UpgradeCategoryTab
import com.kukurodev.mykukuroaquarium.model.upgrade.UpgradeDatabase
import com.kukurodev.mykukuroaquarium.model.upgrade.UpgradeModel
import com.kukurodev.mykukuroaquarium.model.upgrade.UpgradeState
import com.kukurodev.mykukuroaquarium.model.upgrade.UpgradeType
import com.kukurodev.mykukuroaquarium.model.upgrade.getLevel
import com.kukurodev.mykukuroaquarium.model.upgrade.toUpgradeStateModel

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

        if (!CoinManager.hasEnoughCoin(upgradeModel.getLevel(newCurrentLevel).cost))
            return

        val updatedUpgrades = upgrades.map { upgrade ->
            if (upgrade.id == upgradeModel.id) {
                upgrade.copy(currentLevel = newCurrentLevel)
            } else {
                upgrade
            }
        }
        updateUpgrades(updatedUpgrades)
    }

    fun getUpgradeValue(type: UpgradeType): Int {
        val upgrade = upgrades.firstOrNull { it.type == type } ?: return 1
        if (upgrade.currentLevel <= 0) {
            return 0
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

    private fun updateUpgrades(updatedUpgrades: List<UpgradeModel>) {
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
}