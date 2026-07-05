package com.kroq.myaquariumsimulator.ui.component.popup.shop

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kroq.myaquariumsimulator.model.PlayerTier
import com.kroq.myaquariumsimulator.model.shop.ShopItem

@Composable
fun ShopGrid(
    modifier: Modifier = Modifier,
    items: List<ShopItem>,
    playerTier: PlayerTier,
    onClick: (ShopItem) -> Unit
) {

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(12.dp)
    ) {
        items(
            items = items,
            key = { it.id } // 🔥 önemli
        ) { item ->

            ShopItemCard(
                item = item,
                playerTier = playerTier,
                onClick = { onClick(item) }
            )
        }
    }
}