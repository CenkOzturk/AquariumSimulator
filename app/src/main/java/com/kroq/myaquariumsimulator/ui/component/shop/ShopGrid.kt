package com.kroq.myaquariumsimulator.ui.component.shop

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.kroq.myaquariumsimulator.game.AquariumManager
import com.kroq.myaquariumsimulator.game.ItemManager
import com.kroq.myaquariumsimulator.model.GameProgress
import com.kroq.myaquariumsimulator.model.PlayerTier
import com.kroq.myaquariumsimulator.model.calculateTier
import com.kroq.myaquariumsimulator.model.item.ItemDatabase
import com.kroq.myaquariumsimulator.model.shop.ShopItem

@Composable
fun ShopGrid(
    items: List<ShopItem>,
    onClick: (ShopItem) -> Unit
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(12.dp)
    ) {
        items(
            items = items,
            key = { it.id } // 🔥 önemli
        ) { item ->

            ShopItemCard(
                item = item,
                playerTier = GameProgress(
                    AquariumManager.getCurrentAquarium().type,
                    ItemManager.items.map { it.type }
                ).calculateTier(),
                onClick = { onClick(item) }
            )
        }
    }
}