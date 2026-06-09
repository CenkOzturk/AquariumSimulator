package com.kroq.myaquariumsimulator.model.fish

import com.kroq.myaquariumsimulator.R

object FishDatabase {
    private val fishes = listOf(
        FishModel(100, R.drawable.fish_01, 25, 1,FishMoveModel(50f, 200f, 100f, 200f, 1.4f, 1,), RequirementType.FREE),
        FishModel(101, R.drawable.fish_02, 50, 2,FishMoveModel(200f, 500f, 100f, 200f, 1.5f, -1, ), RequirementType.FREE),
        FishModel(102, R.drawable.fish_03, 100, 3,FishMoveModel(300f, 150f, 100f, 200f, 0.8f, 1, ), RequirementType.FREE),
        FishModel(103, R.drawable.fish_04, 175, 5,FishMoveModel(100f, 650f, 100f, 200f, 1.2f, -1, ), RequirementType.FREE),
        FishModel(104, R.drawable.fish_05, 300, 8,FishMoveModel( 450f, 350f, 100f, 200f, 1f, 1, ), RequirementType.BRONZE),
        FishModel(105, R.drawable.fish_06, 500, 12,FishMoveModel(80f, 420f, 100f, 200f, 1.3f, -1), RequirementType.BRONZE),
        FishModel(106, R.drawable.fish_07, 800, 18,FishMoveModel(320f, 220f, 100f, 200f, 1.1f, 1, ), RequirementType.BRONZE),
        FishModel(107, R.drawable.fish_08, 1300, 26,FishMoveModel(260f, 580f, 100f, 200f, 0.9f, -1), RequirementType.SILVER),
        FishModel(108, R.drawable.fish_09, 2000, 38,FishMoveModel(410f, 300f, 100f, 200f, 1.4f, 1, ), RequirementType.SILVER),
        FishModel(109, R.drawable.fish_10, 3200, 55, FishMoveModel(140f, 120f, 100f, 200f, 1.0f, -1), RequirementType.SILVER),
        FishModel(110, R.drawable.fish_11, 5000, 80,FishMoveModel(470f, 540f, 100f, 200f, 1.2f, 1, ), RequirementType.GOLD),
        FishModel(111, R.drawable.fish_12, 8000, 115,FishMoveModel(230f, 360f, 100f, 200f, 0.85f, -1), RequirementType.GOLD),
        FishModel(112, R.drawable.fish_13, 12500, 165,FishMoveModel(360f, 180f, 100f, 200f, 1.35f, 1, ), RequirementType.GOLD),
    )

    fun getAllFishes(): List<FishModel> {
        return fishes
    }

    fun getFishByIds(ids: Set<Int>): List<FishModel> {
        return fishes.filter { it.id in ids }
    }
}