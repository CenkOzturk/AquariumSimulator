package com.kroq.myaquariumsimulator.game

object ScreenManager {

    var screenWidth: Float = 0f
        private set

    var screenHeight: Float = 0f
        private set

    fun init(width: Float, height: Float) {
        screenWidth = width
        screenHeight = height
    }
}