package com.kroq.myaquariumsimulator.ui.component.shop

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kroq.myaquariumsimulator.model.shop.ShopItem

@Composable
fun ShopItemCard(
    item: ShopItem,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(6.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(18.dp)) // 🔥 ripple fix
            .background(Color.White)
            .border(2.dp, Color.Transparent, RoundedCornerShape(18.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = true)
            ) { onClick() }
            .padding(10.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            // 🔹 ICON
            Text(
                text = item.icon,
                fontSize = 28.sp
            )

            // 🔹 TITLE
            Text(
                text = item.title,
                fontSize = 13.sp
            )

            // 🔹 PRICE
            Text(
                text = "${item.price} 💰",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}