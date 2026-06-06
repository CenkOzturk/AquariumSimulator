package com.kroq.myaquariumsimulator.model.item

object FishFoodItemDatabase {
    private val foods = listOf(
        FishFoodItemModel(id = 350, price = 50, foodAmount = 10),
        FishFoodItemModel(id = 351, price = 100, foodAmount = 25),
        FishFoodItemModel(id = 352, price = 180, foodAmount = 50)
    )

    fun getAllFishFeed(): List<FishFoodItemModel> {
        return foods
    }

    fun getFoodCountByIds(ids: Int): Int {
        return foods.find { ids == it.id }?.foodAmount ?: 0
    }

    fun isFood(itemId: Int): Boolean {
        return foods.any { it.id == itemId }
    }
}