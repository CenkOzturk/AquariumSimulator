package com.kukurodev.mykukuroaquarium.ui.popup.inventory

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.kukurodev.mykukuroaquarium.model.inventory.InventoryItem
import kotlinx.coroutines.delay
import kotlin.collections.set

@Composable
fun AnimatedInventoryGrid(
    modifier: Modifier = Modifier,
    items: List<InventoryItem>,
    onClick: (InventoryItem) -> Unit
) {
    val visibleMap = remember { mutableStateMapOf<Int, Boolean>() }

    LaunchedEffect(items) {
        visibleMap.clear()

        items.forEachIndexed { index, item ->
            delay(index * 30L)
            visibleMap[item.id] = true
        }
    }

    InventoryGrid(
        modifier = modifier,
        items = items,
        onClick = onClick
    )
}