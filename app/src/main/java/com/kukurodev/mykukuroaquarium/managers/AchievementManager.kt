package com.kukurodev.mykukuroaquarium.managers

import com.kukurodev.mykukuroaquarium.model.GameState
import com.kukurodev.mykukuroaquarium.model.achievement.AchievementDatabase
import com.kukurodev.mykukuroaquarium.model.achievement.AchievementModel
import com.kukurodev.mykukuroaquarium.model.aquarium.AquariumType
import com.kukurodev.mykukuroaquarium.model.upgrade.UpgradeDatabase

object AchievementManager {
    fun getAchievements(): List<AchievementModel> {
        return AchievementDatabase.getAllAchievements()
    }

    fun isUnlocked(id: Int, state: GameState): Boolean {
        return when (id) {
            1 -> state.ownedFishIds.isNotEmpty()
            2 -> state.activeFishes.size >= 5 || state.ownedFishIds.size >= 5
            3 -> state.ownedUpgrades.list.any { it.currentLevel > 0 }
            4 -> state.totalBubblesCollected >= 20
            5 -> state.totalBubblesCollected >= 200
            6 -> state.ownedFishIds.size >= 10
            7 -> state.ownedItemIds.isNotEmpty()
            8 -> state.aquariumType != AquariumType.SMALL.name
            9 -> state.totalTimesFed >= 100
            10 -> state.totalTimesFed >= 1000
            11 -> state.cleanedFullyDirtyAquarium
            12 -> state.totalCoinsEarned >= 10000 || state.coins >= 10000
            13 -> state.totalCoinsEarned >= 100000 || state.coins >= 100000
            14 -> state.goldFishUnlocked
            15 -> state.aquariumType == AquariumType.LARGE.name
            16 -> isAllUpgradesMaxed(state)
            else -> false
        }
    }

    private fun isAllUpgradesMaxed(state: GameState): Boolean {
        val allUpgrades = UpgradeDatabase.getAllUpgrades()
        if (allUpgrades.isEmpty() || state.ownedUpgrades.list.isEmpty()) return false
        return allUpgrades.all { upgrade ->
            val userLevel = state.ownedUpgrades.list.find { it.id == upgrade.id }?.currentLevel ?: 0
            val maxLevel = upgrade.levelList.maxOfOrNull { it.level } ?: 5
            userLevel >= maxLevel
        }
    }

    fun isClaimed(id: Int, state: GameState): Boolean {
        return state.claimedAchievementIds.contains(id)
    }

    fun hasUnclaimedAchievements(state: GameState = GameManager.state): Boolean {
        val all = getAchievements()
        return all.any { achievement ->
            isUnlocked(achievement.id, state) && !isClaimed(achievement.id, state)
        }
    }

    fun claimReward(id: Int) {
        val achievement = AchievementDatabase.getAllAchievements().find { it.id == id } ?: return
        val currentState = GameManager.state
        if (isClaimed(id, currentState) || !isUnlocked(id, currentState)) return

        CoinManager.addCoins(achievement.rewardCoins)
        GameManager.update {
            it.copy(
                claimedAchievementIds = it.claimedAchievementIds + id
            )
        }
    }
}
