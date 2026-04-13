package com.kroq.myaquariumsimulator.model.aquarium

enum class AquariumType {
    SMALL,
    MEDIUM,
    LARGE
}

fun createAquarium(
    type: AquariumType,
    screenWidth: Float,
    screenHeight: Float
): AquariumModel {

    return when (type) {
        AquariumType.SMALL -> AquariumModel(
            type = type,
            offsetX = screenWidth * 0.2f,
            offsetY = screenHeight * 0.2f,
            width = screenWidth * 0.6f,
            height = screenHeight * 0.35f,
            color = 0xFF3A86FF
        )

        AquariumType.MEDIUM -> AquariumModel(
            type = type,
            offsetX = screenWidth * 0.15f,
            offsetY = screenHeight * 0.15f,
            width = screenWidth * 0.75f,
            height = screenHeight * 0.5f,
            color = 0xFF2F8FCE
        )

        AquariumType.LARGE -> AquariumModel(
            type = type,
            offsetX = screenWidth * 0.05f,
            offsetY = screenHeight * 0.1f,
            width = screenWidth * 0.9f,
            height = screenHeight * 0.7f,
            color = 0xFF1D6FA5
        )
    }
}