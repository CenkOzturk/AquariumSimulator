package com.kroq.myaquariumsimulator.model.upgrade

import com.kroq.myaquariumsimulator.R

object UpgradeDatabase {
    private val upgrades = listOf(
        UpgradeModel(
            id = 1001,
            category = UpgradeCategoryTab.FISH,
            type = UpgradeType.COIN_VALUE,
            nameResId = R.string.upgrade_coin_value,
            upgradeResId = R.string.upgrade_increase_income,
            descriptionResId = R.string.upgrade_coin_value_description,
            iconResId = R.drawable.ic_upgrade_coin_value,
            currentLevel = 1,
            levelList = listOf(
                UpgradeLevelModel(
                    level = 1,
                    cost = 100,
                    value = 1
                ),
                UpgradeLevelModel(
                    level = 2,
                    cost = 250,
                    value = 2
                ),
                UpgradeLevelModel(
                    level = 3,
                    cost = 500,
                    value = 4
                ),
                UpgradeLevelModel(
                    level = 4,
                    cost = 1000,
                    value = 9
                ),
                UpgradeLevelModel(
                    level = 5,
                    cost = 2000,
                    value = 14
                )
            )
        ),

        UpgradeModel(
            id = 1002,
            category = UpgradeCategoryTab.FISH,
            type = UpgradeType.COIN_DURATION,
            nameResId = R.string.upgrade_coin_speed,
            upgradeResId = R.string.upgrade_time,
            descriptionResId = R.string.upgrade_coin_speed_description,
            iconResId = R.drawable.ic_upgrade_coin_time,
            currentLevel = 1,
            levelList = listOf(
                UpgradeLevelModel(
                    level = 1,
                    cost = 100,
                    value = 1
                ),
                UpgradeLevelModel(
                    level = 2,
                    cost = 250,
                    value = 2
                ),
                UpgradeLevelModel(
                    level = 3,
                    cost = 500,
                    value = 3
                ),
                UpgradeLevelModel(
                    level = 4,
                    cost = 1000,
                    value = 4
                ),
                UpgradeLevelModel(
                    level = 5,
                    cost = 2000,
                    value = 6
                )
            )
        ),

        UpgradeModel(
            id = 1003,
            category = UpgradeCategoryTab.BUBBLE,
            type = UpgradeType.BUBBLE_VALUE,
            nameResId = R.string.upgrade_bubble_value,
            upgradeResId = R.string.upgrade_increase_income,
            descriptionResId = R.string.upgrade_bubble_value_description,
            iconResId = R.drawable.ic_upgrade_bubble_value,
            currentLevel = 1,
            levelList = listOf(
                UpgradeLevelModel(
                    level = 1,
                    cost = 1000,
                    value = 3
                ),
                UpgradeLevelModel(
                    level = 2,
                    cost = 2500,
                    value = 5
                ),
                UpgradeLevelModel(
                    level = 3,
                    cost = 5000,
                    value = 5
                ),
                UpgradeLevelModel(
                    level = 4,
                    cost = 10000,
                    value = 10
                ),
                UpgradeLevelModel(
                    level = 5,
                    cost = 20000,
                    value = 20
                )
            )
        ),

        UpgradeModel(
            id = 1004,
            category = UpgradeCategoryTab.BUBBLE,
            type = UpgradeType.BUBBLE_TIME,
            nameResId = R.string.upgrade_bubble_time,
            upgradeResId = R.string.upgrade_time,
            descriptionResId = R.string.upgrade_bubble_time_description,
            iconResId = R.drawable.ic_upgrade_bubble_time,
            currentLevel = 1,
            levelList = listOf(
                UpgradeLevelModel(
                    level = 1,
                    cost = 1000,
                    value = 1
                ),
                UpgradeLevelModel(
                    level = 2,
                    cost = 2500,
                    value = 2
                ),
                UpgradeLevelModel(
                    level = 3,
                    cost = 5000,
                    value = 2
                ),
                UpgradeLevelModel(
                    level = 4,
                    cost = 10000,
                    value = 2
                ),
                UpgradeLevelModel(
                    level = 5,
                    cost = 20000,
                    value = 3
                )
            )
        )
    )

    fun getAllUpgrades(): List<UpgradeModel> = upgrades

    fun getUpgrade(
        id: Int
    ): UpgradeModel? {
        return upgrades.firstOrNull {
            it.id == id
        }
    }
}