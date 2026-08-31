package com.kroq.myaquariumsimulator.model.upgrade

data class UpgradeState(
    val list: List<UpgradeStateModel>
)

fun List<UpgradeStateModel>.toUpgradeState(): UpgradeState {
    return UpgradeState(this)
}