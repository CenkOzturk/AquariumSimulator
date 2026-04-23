package com.kroq.myaquariumsimulator.model.fish

import com.kroq.myaquariumsimulator.R

object FishDatabase {
    private val fishes = listOf(
        FishModel(0, R.drawable.fish_01, 50f, 200f, 100f, 200f, 1.4f, 1),
        FishModel(1, R.drawable.fish_02, 200f, 500f, 100f, 200f, 1.5f, -1),
        FishModel(2, R.drawable.fish_03, 300f, 150f, 100f, 200f, 0.8f, 1),
        FishModel(3, R.drawable.fish_04, 100f, 650f, 100f, 200f, 1.2f, -1),
        FishModel(4, R.drawable.fish_05, 450f, 350f, 100f, 200f, 1f, 1)
    )

    fun getFishByIds(ids: Set<Int>): List<FishModel> {
        return fishes.filter { it.id in ids }
    }
}