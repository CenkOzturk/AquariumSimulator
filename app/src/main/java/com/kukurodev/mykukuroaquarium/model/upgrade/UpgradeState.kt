package com.kukurodev.mykukuroaquarium.model.upgrade

data class UpgradeState(
    val list: List<UpgradeStateModel>
)

fun List<UpgradeStateModel>.toUpgradeState(): UpgradeState {
    return UpgradeState(this)
}