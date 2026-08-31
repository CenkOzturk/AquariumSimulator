package com.kroq.myaquariumsimulator.model.upgrade

data class UpgradeModel(
    val id: Int,
    val category: UpgradeCategoryTab,
    val type: UpgradeType,
    val nameResId: Int,
    val upgradeResId: Int,
    val descriptionResId: Int,
    val iconResId: Int,
    var currentLevel: Int,
    val levelList: List<UpgradeLevelModel>
)

fun UpgradeModel.getLevel(level: Int): UpgradeLevelModel {
    return this.levelList.find { it.level == level }!!
}

fun UpgradeModel.toUpgradeStateModel(): UpgradeStateModel {
    return UpgradeStateModel(
        this.id,
        this.currentLevel
    )
}