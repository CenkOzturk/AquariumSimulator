package com.kroq.myaquariumsimulator.model.fish

import com.kroq.myaquariumsimulator.R

object FishDatabase {
    fun getInitialFishes(): List<FishModel> {
        return listOf(
            FishModel(
                id = 1,
                resId = R.drawable.fish_01,
                x = 50f,
                y = 200f,
                targetX = 100f,
                targetY = 200f,
                speed = 1.4f,
                direction = 1
            ),

            FishModel(
                id = 2,
                resId = R.drawable.fish_02,
                x = 200f,
                y = 500f,
                targetX = 100f,
                targetY = 200f,
                speed = 1.5f,
                direction = -1
            ),

            FishModel(
                id = 3,
                resId = R.drawable.fish_03,
                x = 300f,
                y = 150f,
                targetX = 100f,
                targetY = 200f,
                speed = 0.8f,
                direction = 1
            ),

            FishModel(
                id = 4,
                resId = R.drawable.fish_04,
                x = 100f,
                y = 650f,
                targetX = 100f,
                targetY = 200f,
                speed = 1.2f,
                direction = -1
            ),

            FishModel(
                id = 5,
                resId = R.drawable.fish_05,
                x = 450f,
                y = 350f,
                targetX = 100f,
                targetY = 200f,
                speed = 1f,
                direction = 1
            )
        )
    }
}