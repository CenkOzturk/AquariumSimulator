package com.kukurodev.mykukuroaquarium.model.welcome

import com.kukurodev.mykukuroaquarium.R

object WelcomeGiftDatabase {

    private val gifts = listOf(
        GiftModel(
            day = 1,
            coins = 100,
            rewardResId = R.string.welcome_gift_reward_day_1
        ),
        GiftModel(
            day = 2,
            food = 25,
            rewardResId = R.string.welcome_gift_reward_day_2
        ),
        GiftModel(
            day = 3,
            coins = 250,
            rewardResId = R.string.welcome_gift_reward_day_3
        ),
        GiftModel(
            day = 4,
            food = 50,
            rewardResId = R.string.welcome_gift_reward_day_4
        ),
        GiftModel(
            day = 5,
            coins = 500,
            rewardResId = R.string.welcome_gift_reward_day_5
        ),
        GiftModel(
            day = 6,
            coins = 1000,
            food = 50,
            rewardResId = R.string.welcome_gift_reward_day_6
        ),
        GiftModel(
            day = 7,
            rewardResId = R.string.welcome_gift_reward_day_7
        )
    )

    fun getAllGifts(): List<GiftModel> {
        return gifts
    }

    fun getGift(day: Int): GiftModel =
        gifts.first { it.day == day }
}
