package com.kroq.myaquariumsimulator.model.fish

import com.kroq.myaquariumsimulator.R

object FishDatabase {
    private val fishes = listOf(
        FishModel(100, R.string.fish_100_name, R.drawable.fish_01, 25, 1,FishMoveModel(50f, 200f, 100f, 200f, 1.4f, 1,), requirementType = RequirementType.FREE),
        FishModel(101, R.string.fish_101_name, R.drawable.fish_02, 50, 2,FishMoveModel(200f, 500f, 100f, 200f, 1.5f, -1, ), requirementType = RequirementType.FREE),
        FishModel(102, R.string.fish_102_name, R.drawable.fish_03, 100, 3,FishMoveModel(300f, 150f, 100f, 200f, 0.8f, 1, ), requirementType = RequirementType.FREE),
        FishModel(103, R.string.fish_103_name, R.drawable.fish_04, 175, 5,FishMoveModel(100f, 650f, 100f, 200f, 1.2f, -1, ), requirementType = RequirementType.FREE),

        FishModel(104, R.string.fish_104_name, R.drawable.fish_05, 300, 10,FishMoveModel(450f, 350f, 100f, 200f, 1f, 1, ), requirementType = RequirementType.BRONZE),
        FishModel(105, R.string.fish_105_name, R.drawable.fish_06, 500, 15,FishMoveModel(80f, 420f, 100f, 200f, 1.3f, -1), requirementType = RequirementType.BRONZE),
        FishModel(106, R.string.fish_106_name, R.drawable.fish_07, 800, 20,FishMoveModel(320f, 220f, 100f, 200f, 1.1f, 1, ), requirementType = RequirementType.BRONZE),

        FishModel(107, R.string.fish_107_name, R.drawable.fish_08, 1300, 25,FishMoveModel(260f, 580f, 100f, 200f, 0.9f, -1), requirementType = RequirementType.SILVER),
        FishModel(108, R.string.fish_108_name, R.drawable.fish_09, 2000, 35,FishMoveModel(410f, 300f, 100f, 200f, 1.4f, 1, ), requirementType = RequirementType.SILVER),
        FishModel(109, R.string.fish_109_name, R.drawable.fish_10, 3200, 50,FishMoveModel(140f, 120f, 100f, 200f, 1.0f, -1), requirementType = RequirementType.SILVER),

        FishModel(110, R.string.fish_110_name, R.drawable.fish_11, 5000, 80,FishMoveModel(470f, 540f, 100f, 200f, 1.2f, 1, ), requirementType = RequirementType.GOLD),
        FishModel(111, R.string.fish_111_name, R.drawable.fish_12, 8000, 120,FishMoveModel(230f, 360f, 100f, 200f, 0.85f, -1), requirementType = RequirementType.GOLD),
        FishModel(112, R.string.fish_112_name, R.drawable.fish_13, 12500, 160,FishMoveModel(360f, 180f, 100f, 200f, 1.35f, 1, ), requirementType = RequirementType.GOLD),

        // FishModel(113, R.string.fish_113_name, R.drawable.fish_14, 90000, 230,FishMoveModel(120f, 250f, 100f, 200f, 1.1f, -1), requirementType = RequirementType.GOLD),
        // FishModel(114, R.string.fish_114_name, R.drawable.fish_15, 140000, 315,FishMoveModel(280f, 420f, 100f, 200f, 1.25f, 1), requirementType = RequirementType.GOLD),
        // FishModel(115, R.string.fish_115_name, R.drawable.fish_16, 220000, 430,FishMoveModel(400f, 180f, 100f, 200f, 0.95f, -1), requirementType = RequirementType.GOLD),
        // FishModel(116, R.string.fish_116_name, R.drawable.fish_17, 340000, 580,FishMoveModel(180f, 520f, 100f, 200f, 1.15f, 1), requirementType = RequirementType.GOLD),
        // FishModel(117, R.string.fish_117_name, R.drawable.fish_18, 520000, 780,FishMoveModel(450f, 300f, 100f, 200f, 0.9f, -1), requirementType = RequirementType.GOLD),
        // FishModel(118, R.string.fish_118_name, R.drawable.fish_19, 800000, 1050,FishMoveModel(100f, 150f, 100f, 200f, 1.3f, 1), requirementType = RequirementType.GOLD),
        // FishModel(119, R.string.fish_119_name, R.drawable.fish_20, 1200000, 1400,FishMoveModel(350f, 500f, 100f, 200f, 1.05f, -1), requirementType = RequirementType.GOLD),
        // FishModel(120, R.string.fish_120_name, R.drawable.fish_21, 1800000, 1850,FishMoveModel(200f, 300f, 100f, 200f, 1.2f, 1), requirementType = RequirementType.GOLD),
        // FishModel(121, R.string.fish_121_name, R.drawable.fish_22, 2700000, 2400,FishMoveModel(420f, 450f, 100f, 200f, 0.85f, -1), requirementType = RequirementType.GOLD),
        // FishModel(122, R.string.fish_122_name, R.drawable.fish_23, 4000000, 3100,FishMoveModel(150f, 550f, 100f, 200f, 1.4f, 1), requirementType = RequirementType.GOLD),
        // FishModel(123, R.string.fish_123_name, R.drawable.fish_24, 6000000, 4000,FishMoveModel(380f, 220f, 100f, 200f, 1.0f, -1), requirementType = RequirementType.GOLD),
        // FishModel(124, R.string.fish_124_name, R.drawable.fish_25, 9000000, 5200,FishMoveModel(300f, 500f, 100f, 200f, 1.3f, 1), requirementType = RequirementType.GOLD),
    )

    fun getAllFishes(): List<FishModel> {
        return fishes
    }

    fun getFishByIds(ids: Set<Int>): List<FishModel> {
        return fishes.filter { it.id in ids }
    }

    fun getFishById(fishID: Int): FishModel {
        return fishes.find { it.id == fishID }!!
    }
}