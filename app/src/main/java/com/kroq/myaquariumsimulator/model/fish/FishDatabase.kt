package com.kroq.myaquariumsimulator.model.fish

import com.kroq.myaquariumsimulator.R

object FishDatabase {
    private val fishes = listOf(
        FishModel(100, R.drawable.fish_01, 50f, 200f, 100f, 200f, 1.4f, 1,RequirementType.BRONZE),
        FishModel(101, R.drawable.fish_02, 200f, 500f, 100f, 200f, 1.5f, -1, RequirementType.BRONZE),
        FishModel(102, R.drawable.fish_03, 300f, 150f, 100f, 200f, 0.8f, 1, RequirementType.BRONZE),
        FishModel(103, R.drawable.fish_04, 100f, 650f, 100f, 200f, 1.2f, -1, RequirementType.BRONZE),
        FishModel(104, R.drawable.fish_05, 450f, 350f, 100f, 200f, 1f, 1, RequirementType.SILVER),
        FishModel(105, R.drawable.fish_06, 80f, 420f, 100f, 200f, 1.3f, -1, RequirementType.SILVER),
        FishModel(106, R.drawable.fish_07, 320f, 220f, 100f, 200f, 1.1f, 1, RequirementType.SILVER),
        FishModel(107, R.drawable.fish_08, 260f, 580f, 100f, 200f, 0.9f, -1, RequirementType.SILVER),
        FishModel(108, R.drawable.fish_09, 410f, 300f, 100f, 200f, 1.4f, 1, RequirementType.SILVER),
        FishModel(109, R.drawable.fish_10, 140f, 120f, 100f, 200f, 1.0f, -1, RequirementType.GOLD),
        FishModel(110, R.drawable.fish_11, 470f, 540f, 100f, 200f, 1.2f, 1, RequirementType.GOLD),
        FishModel(111, R.drawable.fish_12, 230f, 360f, 100f, 200f, 0.85f, -1, RequirementType.GOLD),
        FishModel(112, R.drawable.fish_13, 360f, 180f, 100f, 200f, 1.35f, 1, RequirementType.GOLD),
    )

    fun getAllFishes(): List<FishModel> {
        return fishes
    }

    fun getFishByIds(ids: Set<Int>): List<FishModel> {
        return fishes.filter { it.id in ids }
    }
}