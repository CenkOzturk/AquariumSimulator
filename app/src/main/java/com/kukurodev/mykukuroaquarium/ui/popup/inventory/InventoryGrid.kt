package com.kukurodev.mykukuroaquarium.ui.popup.inventory

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kukurodev.mykukuroaquarium.model.inventory.InventoryItem

@Composable
fun InventoryGrid(
    modifier: Modifier = Modifier,
    items: List<InventoryItem>,
    onClick: (InventoryItem) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(12.dp)
    ) {
        items(
            items = items,
            key = { it.id }
        ) { item ->
            InventoryItemCard(
                item = item,
                onClick = { onClick(item) }
            )
        }
    }
}