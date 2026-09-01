package com.kukurodev.mykukuroaquarium.model.item

import com.kukurodev.mykukuroaquarium.R

object CleanerDatabase {
    val cleaners = listOf(
        CleanerModel(
            id = 500,
            nameResId = R.string.cleaner_cloth_name,
            icon = R.drawable.ic_cleaner_cloth,
            price = 75,
            cleanerCount = 5
        ),
        CleanerModel(
            id = 501,
            nameResId = R.string.cleaner_sponge_name,
            icon = R.drawable.ic_cleaner_sponge,
            price = 300,
            cleanerCount = 20
        ),
        CleanerModel(
            id = 502,
            nameResId = R.string.cleaner_robot_name,
            icon = R.drawable.ic_cleaner_robot,
            price = 50000,
            cleanerCount = 999999 // TODO Robot sistemi geldiğinde değiştirilecek.
        )
    )

    fun getAllCleaners(): List<CleanerModel> {
        return cleaners
    }

    fun get(id: Int): CleanerModel? {
        return cleaners.firstOrNull { it.id == id }
    }

    fun isCleaner(itemId: Int): Boolean {
        return cleaners.any { it.id == itemId }
    }
}