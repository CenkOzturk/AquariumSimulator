package com.kroq.myaquariumsimulator.model.aquarium

import com.kroq.myaquariumsimulator.game.ScreenManager.screenHeight
import com.kroq.myaquariumsimulator.game.ScreenManager.screenWidth
import com.kroq.myaquariumsimulator.model.shop.ShopItem

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
            offsetX = screenWidth * 0.2f,
            offsetY = screenHeight * 0.2f,
            width = screenWidth * 0.6f,
            height = screenHeight * 0.35f,
            color = 0xFF3A86FF,
            fishCount = 2
        )

        AquariumType.MEDIUM -> AquariumModel(
            type = type,
            offsetX = screenWidth * 0.15f,
            offsetY = screenHeight * 0.15f,
            width = screenWidth * 0.75f,
            height = screenHeight * 0.5f,
            color = 0xFF2F8FCE,
            fishCount = 3
        )

        AquariumType.LARGE -> AquariumModel(
            type = type,
            offsetX = screenWidth * 0.05f,
            offsetY = screenHeight * 0.1f,
            width = screenWidth * 0.9f,
            height = screenHeight * 0.7f,
            color = 0xFF1D6FA5,
            fishCount = 5
        )
    }
}

fun AquariumType.toShopItem(): ShopItem {
    return when (this) {
        AquariumType.SMALL -> ShopItem(0, "Small", 5, "🧪")
        AquariumType.MEDIUM -> ShopItem(1, "Medium", 10, "🧪")
        AquariumType.LARGE -> ShopItem(2, "Large", 20, "🧪")
    }
}