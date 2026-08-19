package com.kroq.myaquariumsimulator.ui.popup.shop

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.model.shop.ShopItem
import com.kroq.myaquariumsimulator.model.shop.ShopTab

@Composable
fun ShopItemInfo(
    item: ShopItem,
    alpha: Float
) {
    when (item.type) {
        ShopTab.FISH -> {
            Text(
                text = stringResource(R.string.shop_extra_info_fish, item.extraInfo),
                fontSize = 11.sp,
                color = Color(0xFF43A047).copy(alpha = alpha),
                fontWeight = FontWeight.Medium,
                maxLines = 1
            )
        }

        ShopTab.AQUARIUM -> {
            Text(
                text = stringResource(R.string.shop_extra_info_aquarium, item.extraInfo),
                fontSize = 11.sp,
                color = Color(0xFF1E88E5).copy(alpha = alpha),
                fontWeight = FontWeight.Medium,
                maxLines = 1
            )
        }

        ShopTab.ITEMS -> {
            Text(
                text = stringResource(R.string.shop_extra_info_item, item.extraInfo),
                fontSize = 11.sp,
                color = Color(0xFF1E88E5).copy(alpha = alpha),
                fontWeight = FontWeight.Medium,
                maxLines = 1
            )
        }

        else -> Unit
    }
}