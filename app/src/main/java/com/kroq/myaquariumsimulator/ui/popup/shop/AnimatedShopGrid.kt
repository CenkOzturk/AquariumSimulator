package com.kroq.myaquariumsimulator.ui.popup.shop

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.kroq.myaquariumsimulator.model.PlayerTier
import com.kroq.myaquariumsimulator.model.shop.ShopItem
import kotlinx.coroutines.delay

@Composable
fun AnimatedShopGrid(
    modifier: Modifier = Modifier,
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
        modifier = modifier,
        items = items,
        onClick = onClick,
        playerTier = playerTier
    )
}