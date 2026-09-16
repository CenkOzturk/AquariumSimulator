package com.kukurodev.mykukuroaquarium.model.inventory

data class InventoryItem(
    val id: Int,
    val titleResId: Int,
    val icon: String,
    val extraInfo: String,
    val isActive: Boolean
)
