package com.kukurodev.mykukuroaquarium.managers

import com.kukurodev.mykukuroaquarium.data.Constants.WELCOME_GIFT_DAYS
import com.kukurodev.mykukuroaquarium.model.welcome.GiftModel
import com.kukurodev.mykukuroaquarium.model.welcome.WelcomeGiftDatabase
import com.kukurodev.mykukuroaquarium.utils.Utils

object WelcomeGiftManager {
    fun refreshIfNeeded() {
        val today = Utils.currentDay()
        if (GameManager.state.lastLoginTime.toInt() == today) return
        GameManager.update {
            it.copy(
                lastLoginTime = Utils.currentTime(),
                welcomeGiftClaimed = false
            )
        }
    }

    fun canClaim(): Boolean {
        return !GameManager.state.welcomeGiftClaimed &&
                GameManager.state.welcomeGiftDay < WELCOME_GIFT_DAYS
    }

    fun currentDay(): Int {
        return if (GameManager.state.welcomeGiftClaimed) {
            GameManager.state.welcomeGiftDay
        } else {
            GameManager.state.welcomeGiftDay + 1
        }
    }

    fun currentGift(): GiftModel {
        return WelcomeGiftDatabase.getGift(currentDay())
    }

    fun claimReward() {
        if (!canClaim()) return
        currentGift().let { gift ->
            if (gift.coins > 0) {
                CoinManager.addCoins(gift.coins)
            }
            if (gift.food > 0) {
                FishFoodManager.updateFood(gift.food)
            }
            // TODO Golden Fish
            GameManager.update {
                it.copy(
                    welcomeGiftDay = it.welcomeGiftDay + 1,
                    welcomeGiftClaimed = true
                )
            }

            if (gift.day == 7) {
                GameManager.update {
                    it.copy(
                        goldFishUnlocked = true
                    )
                }
            }
        }
    }
}