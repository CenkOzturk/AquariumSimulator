package com.kroq.myaquariumsimulator.ui.component.shop

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ShopGrid() {

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.padding(horizontal = 12.dp),
        contentPadding = PaddingValues(bottom = 20.dp)
    ) {

        items(12) {
            ShopItemCard()
        }
    }
}