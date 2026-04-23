package com.kroq.myaquariumsimulator.ui.component.shop

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kroq.myaquariumsimulator.model.shop.ShopTab

@Composable
fun ShopTabs(
    selected: ShopTab,
    onTabSelected: (ShopTab) -> Unit
) {

    Row(
        modifier = Modifier
            .padding(16.dp)
            .background(Color.LightGray.copy(0.3f), RoundedCornerShape(50))
    ) {

        ShopTab.entries.forEach { tab ->

            val isSelected = tab == selected

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
                    .clip(RoundedCornerShape(50))
                    .background(if (isSelected) Color.White else Color.Transparent)
                    .clickable { onTabSelected(tab) }
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = when (tab) {
                        ShopTab.FISH -> "🐟 Fish"
                        ShopTab.AQUARIUM -> "🧪 Tank"
                        ShopTab.ITEMS -> "🧱 Items"
                    }
                )
            }
        }
    }
}