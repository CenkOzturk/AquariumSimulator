package com.kukurodev.mykukuroaquarium.managers

import com.kukurodev.mykukuroaquarium.data.Constants.WELCOME_GIFT_DAYS
import com.kukurodev.mykukuroaquarium.model.welcome.GiftModel
import com.kukurodev.mykukuroaquarium.model.welcome.WelcomeGiftDatabase

object WelcomeGiftManager {
    private const val COOLDOWN_MS = 24 * 60 * 60 * 1000L

    fun refreshIfNeeded() {
        val now = System.currentTimeMillis()
        val isCooldownActive = remainingTime(now) > 0L

        GameManager.update {
            it.copy(
                welcomeGiftClaimed = isCooldownActive
            )
        }
    }

    fun remainingTime(currentTime: Long = System.currentTimeMillis()): Long {
        val lastClaimTime = GameManager.state.welcomeGiftLastClaimTime
        if (lastClaimTime == 0L) return 0L

        val nextClaimTime = lastClaimTime + COOLDOWN_MS
        return (nextClaimTime - currentTime).coerceAtLeast(0L)
    }

    fun canClaim(): Boolean {
        if (GameManager.state.welcomeGiftDay >= WELCOME_GIFT_DAYS) return false
        return remainingTime() <= 0L
    }

    fun currentDay(): Int {
        val day = GameManager.state.welcomeGiftDay
        return if (canClaim()) {
            (day + 1).coerceAtMost(WELCOME_GIFT_DAYS)
        } else {
            day.coerceIn(1, WELCOME_GIFT_DAYS)
        }
    }

    fun currentGift(): GiftModel {
        val day = if (currentDay() == 0) 1 else currentDay()
        return WelcomeGiftDatabase.getGift(day)
    }

    fun claimReward() {
        if (!canClaim()) return
        val giftToClaim = currentGift()

        if (giftToClaim.coins > 0) {
            CoinManager.addCoins(giftToClaim.coins)
        }
        if (giftToClaim.food > 0) {
            FishFoodManager.updateFood(giftToClaim.food)
        }

        val now = System.currentTimeMillis()
        GameManager.update {
            it.copy(
                welcomeGiftDay = it.welcomeGiftDay + 1,
                welcomeGiftClaimed = true,
                welcomeGiftLastClaimTime = now
            )
        }

        if (giftToClaim.day == WELCOME_GIFT_DAYS) {
            GameManager.update {
                it.copy(
                    goldFishUnlocked = true
                )
            }
        }
    }
}
