package com.kukurodev.mykukuroaquarium.model.achievement

import com.kukurodev.mykukuroaquarium.R

object AchievementDatabase {
    fun getAllAchievements(): List<AchievementModel> = listOf(
        AchievementModel(
            id = 1,
            titleResId = R.string.achievement_1_title,
            descriptionResId = R.string.achievement_1_desc,
            icon = "🐟",
            rewardCoins = 50
        ),
        AchievementModel(
            id = 2,
            titleResId = R.string.achievement_2_title,
            descriptionResId = R.string.achievement_2_desc,
            icon = "🐠",
            rewardCoins = 100
        ),
        AchievementModel(
            id = 3,
            titleResId = R.string.achievement_3_title,
            descriptionResId = R.string.achievement_3_desc,
            icon = "⚡",
            rewardCoins = 100
        ),
        AchievementModel(
            id = 4,
            titleResId = R.string.achievement_4_title,
            descriptionResId = R.string.achievement_4_desc,
            icon = "🫧",
            rewardCoins = 100
        ),
        AchievementModel(
            id = 5,
            titleResId = R.string.achievement_5_title,
            descriptionResId = R.string.achievement_5_desc,
            icon = "🫧",
            rewardCoins = 300
        ),
        AchievementModel(
            id = 6,
            titleResId = R.string.achievement_6_title,
            descriptionResId = R.string.achievement_6_desc,
            icon = "🦈",
            rewardCoins = 300
        ),
        AchievementModel(
            id = 7,
            titleResId = R.string.achievement_7_title,
            descriptionResId = R.string.achievement_7_desc,
            icon = "🪸",
            rewardCoins = 100
        ),
        AchievementModel(
            id = 8,
            titleResId = R.string.achievement_8_title,
            descriptionResId = R.string.achievement_8_desc,
            icon = "🧪",
            rewardCoins = 100
        ),
        AchievementModel(
            id = 9,
            titleResId = R.string.achievement_9_title,
            descriptionResId = R.string.achievement_9_desc,
            icon = "🍤",
            rewardCoins = 200
        ),
        AchievementModel(
            id = 10,
            titleResId = R.string.achievement_10_title,
            descriptionResId = R.string.achievement_10_desc,
            icon = "🍤",
            rewardCoins = 500
        ),
        AchievementModel(
            id = 11,
            titleResId = R.string.achievement_11_title,
            descriptionResId = R.string.achievement_11_desc,
            icon = "🧽",
            rewardCoins = 150
        ),
        AchievementModel(
            id = 12,
            titleResId = R.string.achievement_12_title,
            descriptionResId = R.string.achievement_12_desc,
            icon = "💰",
            rewardCoins = 500
        ),
        AchievementModel(
            id = 13,
            titleResId = R.string.achievement_13_title,
            descriptionResId = R.string.achievement_13_desc,
            icon = "👑",
            rewardCoins = 2000
        ),
        AchievementModel(
            id = 14,
            titleResId = R.string.achievement_14_title,
            descriptionResId = R.string.achievement_14_desc,
            icon = "🌟",
            rewardCoins = 500
        ),
        AchievementModel(
            id = 15,
            titleResId = R.string.achievement_15_title,
            descriptionResId = R.string.achievement_15_desc,
            icon = "🏆",
            rewardCoins = 500
        ),
        AchievementModel(
            id = 16,
            titleResId = R.string.achievement_16_title,
            descriptionResId = R.string.achievement_16_desc,
            icon = "🥇",
            rewardCoins = 1000
        )
    )
}
