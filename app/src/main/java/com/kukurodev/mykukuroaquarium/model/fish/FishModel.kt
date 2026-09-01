package com.kukurodev.mykukuroaquarium.model.fish

import com.kukurodev.mykukuroaquarium.data.Constants.FEED_DURATION
import com.kukurodev.mykukuroaquarium.model.shop.ShopItem
import com.kukurodev.mykukuroaquarium.model.shop.ShopTab

data class FishModel(
    val id: Int,
    val nameResId: Int,
    val resId: Int,
    val price: Int,
    val income: Int,
    val move: FishMoveModel,
    val fedUntil: Long = FEED_DURATION,
    val requirementType: RequirementType = RequirementType.FREE
)

fun FishModel.isFed(): Boolean {
    return fedUntil > System.currentTimeMillis()
}

fun FishModel.coinMultiplier(): Int {
    return if (isFed()) 2 else 1
}

fun FishModel.toShopItem(): ShopItem {
    return ShopItem(
        id = id,
        type = ShopTab.FISH,
        titleResId = nameResId,
        price = price,
        icon = "🐟",
        extraInfo = income.toString(),
        requiredTier = requirementType.toPlayerTier()
    )
}


