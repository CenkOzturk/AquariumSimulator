package com.kroq.myaquariumsimulator.model.aquarium

import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.managers.ScreenManager.screenHeight
import com.kroq.myaquariumsimulator.managers.ScreenManager.screenWidth
import com.kroq.myaquariumsimulator.model.PlayerTier
import com.kroq.myaquariumsimulator.model.shop.ShopItem
import com.kroq.myaquariumsimulator.model.shop.ShopTab

enum class AquariumType {
    SMALL,
    MEDIUM,
    LARGE
}

fun createAquarium(
    type: AquariumType
): AquariumModel {

    return when (type) {
        AquariumType.SMALL -> AquariumModel(
            type = type,
            nameResId = R.string.aqua_small,
            offsetX = screenWidth * 0.2f,
            offsetY = screenHeight * 0.2f,
            width = screenWidth * 0.6f,
            height = screenHeight * 0.35f,
            color = 0xFF3A86FF,
            fishCount = 6,
            price = 0
        )

        AquariumType.MEDIUM -> AquariumModel(
            type = type,
            nameResId = R.string.aqua_medium,
            offsetX = screenWidth * 0.15f,
            offsetY = screenHeight * 0.15f,
            width = screenWidth * 0.75f,
            height = screenHeight * 0.5f,
            color = 0xFF2F8FCE,
            fishCount = 10,
            price = 7500
        )

        AquariumType.LARGE -> AquariumModel(
            type = type,
            nameResId = R.string.aqua_large,
            offsetX = screenWidth * 0.05f,
            offsetY = screenHeight * 0.1f,
            width = screenWidth * 0.9f,
            height = screenHeight * 0.7f,
            color = 0xFF1D6FA5,
            fishCount = 15,
            price = 50000
        )
    }
}

fun AquariumType.toShopItem(): ShopItem {
    return when (this) {
        AquariumType.SMALL -> ShopItem(0, ShopTab.AQUARIUM, R.string.aqua_small, 0, "🧪", extraInfo = 6.toString())
        AquariumType.MEDIUM -> ShopItem(1, ShopTab.AQUARIUM,R.string.aqua_medium, 7500, "🧪", extraInfo = 10.toString(),  requiredTier = PlayerTier.BRONZE)
        AquariumType.LARGE -> ShopItem(2, ShopTab.AQUARIUM,R.string.aqua_large, 50000, "🧪", extraInfo = 15.toString(), requiredTier = PlayerTier.SILVER)
    }
}