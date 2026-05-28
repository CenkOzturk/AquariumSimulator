package com.kroq.myaquariumsimulator.ui.component.shop

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kroq.myaquariumsimulator.model.PlayerTier
import com.kroq.myaquariumsimulator.model.canAccess
import com.kroq.myaquariumsimulator.model.shop.ShopItem

@Composable
fun ShopItemCard(
    item: ShopItem,
    playerTier: PlayerTier,
    onClick: () -> Unit
) {

    val isUnlocked = playerTier.canAccess(item.requiredTier)

    Box(
        modifier = Modifier
            .padding(6.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(
                if (isUnlocked) Color.White else Color(0xFFF2F2F2)
            )
            .clickable(
                enabled = isUnlocked,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = true)
            ) { onClick() }
            .padding(12.dp)
    ) {

        // TIER BADGE
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(10.dp)
                .background(
                    color = when (item.requiredTier) {
                        PlayerTier.FREE -> Color.White
                        PlayerTier.BRONZE -> Color(0xFFCD7F32)
                        PlayerTier.SILVER -> Color(0xFFC0C0C0)
                        PlayerTier.GOLD -> Color(0xFFFFD700)
                    },
                    shape = RoundedCornerShape(50)
                )
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // ICON
            Text(
                text = item.icon,
                fontSize = 30.sp,
                color = if (isUnlocked) Color.Unspecified else Color.Gray
            )

            Spacer(modifier = Modifier.height(6.dp))

            // TITLE
            Text(
                text = item.title,
                fontSize = 13.sp,
                color = if (isUnlocked) Color.Black else Color.Gray,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(2.dp))

            // PRICE
            Text(
                text = "${item.price} 💰",
                fontSize = 12.sp,
                color = if (isUnlocked) Color.Gray else Color.Gray.copy(alpha = 0.7f)
            )
        }

        // LOCK OVERLAY
        if (!isUnlocked) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color.White.copy(alpha = 0.55f))
            )
        }
    }
}