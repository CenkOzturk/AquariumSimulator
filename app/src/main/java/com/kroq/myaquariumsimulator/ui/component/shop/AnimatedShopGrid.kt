package com.kroq.myaquariumsimulator.ui.component.shop

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import com.kroq.myaquariumsimulator.model.PlayerTier
import com.kroq.myaquariumsimulator.model.shop.ShopItem
import kotlinx.coroutines.delay

@Composable
fun AnimatedShopGrid(
    items: List<ShopItem>,
    onClick: (ShopItem) -> Unit,
    playerTier: PlayerTier
) {

    val visibleMap = remember { mutableStateMapOf<Int, Boolean>() }

    LaunchedEffect(items) {
        visibleMap.clear()

        items.forEachIndexed { index, item ->
            delay(index * 30L)
            visibleMap[item.id] = true
        }
    }

    ShopGrid(
        items = items,
        onClick = onClick,
        playerTier = playerTier
    )
}