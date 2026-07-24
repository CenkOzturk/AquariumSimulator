package com.kroq.myaquariumsimulator.model.item

import com.kroq.myaquariumsimulator.R

object CleanerDatabase {
    val cleaners = listOf(
        CleanerModel(
            id = 500,
            name = "Cloth",
            icon = R.drawable.ic_cleaner_cloth,
            price = 75,
            cleanerCount = 5
        ),
        CleanerModel(
            id = 501,
            name = "Sponge",
            icon = R.drawable.ic_cleaner_sponge,
            price = 220,
            cleanerCount = 20
        ),
        CleanerModel(
            id = 502,
            name = "Robot Cleaner",
            icon = R.drawable.ic_cleaner_robot,
            price = 5000,
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