package com.kukurodev.mykukuroaquarium.ui.popup.shop

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kukurodev.mykukuroaquarium.R
import com.kukurodev.mykukuroaquarium.model.PlayerTier
import com.kukurodev.mykukuroaquarium.model.item.CleanerDatabase
import com.kukurodev.mykukuroaquarium.model.item.FishFoodItemDatabase
import com.kukurodev.mykukuroaquarium.model.shop.ShopItem

@Composable
fun ShopGrid(
    modifier: Modifier = Modifier,
    items: List<ShopItem>,
    playerTier: PlayerTier,
    onClick: (ShopItem) -> Unit
) {
    val tanks = items.filter { it.id < 10 }
    val foods = items.filter { FishFoodItemDatabase.isFood(it.id) }
    val cleaners = items.filter { CleanerDatabase.isCleaner(it.id) }
    val isAquariumTabSections = foods.isNotEmpty() || cleaners.isNotEmpty()

    LazyVerticalGrid(
        modifier = modifier.heightIn(max = 290.dp),
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(6.dp)
    ) {
        if (isAquariumTabSections) {
            if (tanks.isNotEmpty()) {
                items(tanks, key = { it.id }) { item ->
                    ShopItemCard(item = item, playerTier = playerTier, onClick = { onClick(item) })
                }
            }

            if (foods.isNotEmpty()) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    SectionHeader(title = stringResource(R.string.shop_section_foods))
                }
                items(foods, key = { it.id }) { item ->
                    ShopItemCard(item = item, playerTier = playerTier, onClick = { onClick(item) })
                }
            }

            if (cleaners.isNotEmpty()) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    SectionHeader(title = stringResource(R.string.shop_section_cleaners))
                }
                items(cleaners, key = { it.id }) { item ->
                    ShopItemCard(item = item, playerTier = playerTier, onClick = { onClick(item) })
                }
            }
        } else {
            items(items, key = { it.id }) { item ->
                ShopItemCard(item = item, playerTier = playerTier, onClick = { onClick(item) })
            }
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        fontSize = 13.sp,
        fontWeight = FontWeight.ExtraBold,
        color = Color(0xFFD97706),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 4.dp, start = 4.dp)
    )
}
