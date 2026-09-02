package com.kukurodev.mykukuroaquarium.model.welcome

object WelcomeGiftDatabase {

    private val gifts = listOf(
        GiftModel(
            day = 1,
            coins = 100,
            rewardText = "100 Coins"
        ),

        GiftModel(
            day = 2,
            food = 25,
            rewardText = "25 Fish Food"
        ),

        GiftModel(
            day = 3,
            coins = 250,
            rewardText = "250 Coins"
        ),

        GiftModel(
            day = 4,
            food = 50,
            rewardText = "50 Fish Food"
        ),

        GiftModel(
            day = 5,
            coins = 500,
            rewardText = "500 Coins"
        ),

        GiftModel(
            day = 6,
            coins = 1000,
            food = 50,
            rewardText = "1000 Coins and 50 Fish Food"
        ),

        GiftModel(
            day = 7,
            rewardText = "Golden Fish"
        ),
    )

    fun getAllGifts(): List<GiftModel> {
        return gifts
    }

    fun getGift(day: Int): GiftModel =
        gifts.first { it.day == day }
}