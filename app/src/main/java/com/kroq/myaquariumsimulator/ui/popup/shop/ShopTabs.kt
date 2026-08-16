package com.kroq.myaquariumsimulator.ui.popup.shop

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.model.shop.ShopTab

@Composable
fun ShopTabs(
    selected: ShopTab,
    onTabSelected: (ShopTab) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .background(
                Color.LightGray.copy(alpha = 0.3f),
                RoundedCornerShape(50)
            )
            .padding(4.dp)
    ) {

        ShopTab.entries.forEach { tab ->

            val isSelected = tab == selected

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(50))
                    .background(
                        if (isSelected) Color.White
                        else Color.Transparent
                    )
                    .clickable {
                        onTabSelected(tab)
                    }
                    .padding(
                        horizontal = 6.dp,
                        vertical = 10.dp
                    ),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = when (tab) {
                        ShopTab.FISH ->
                            stringResource(R.string.shop_tab_fish)

                        ShopTab.AQUARIUM ->
                            stringResource(R.string.shop_tab_aquarium)

                        ShopTab.ITEMS ->
                            stringResource(R.string.shop_tab_items)
                    },
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}