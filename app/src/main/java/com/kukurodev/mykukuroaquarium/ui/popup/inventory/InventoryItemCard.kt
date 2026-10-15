package com.kukurodev.mykukuroaquarium.ui.popup.inventory

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kukurodev.mykukuroaquarium.managers.GameManager
import com.kukurodev.mykukuroaquarium.model.inventory.InventoryItem
import com.kukurodev.mykukuroaquarium.model.shop.ShopTab
import com.kukurodev.mykukuroaquarium.ui.popup.shop.ShopItemInfo

@Composable
fun InventoryItemCard(
    item: InventoryItem,
    onClick: () -> Unit
) {
    val isUnlocked = GameManager.state.activeFishes.contains(item.id)
    val contentAlpha = if (isUnlocked) 1f else 0.45f

    Box(
        modifier = Modifier
            .padding(6.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(
                if (isUnlocked) {
                    Color.White
                } else {
                    Color(0xFFF2F2F2)
                }
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = true)
            ) {
                onClick()
            }
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // ICON
            Text(
                text = item.icon,
                fontSize = 34.sp,
                modifier = Modifier.alpha(contentAlpha)
            )

            Spacer(modifier = Modifier.height(6.dp))

            // TITLE
            Text(
                text = stringResource(item.titleResId),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black.copy(alpha = contentAlpha),
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(6.dp))

            // EXTRA INFO
            ShopItemInfo(
                shopTab = ShopTab.FISH,
                extraInfo = item.extraInfo,
                alpha = contentAlpha
            )
        }

        // LOCK OVERLAY
        if (!isUnlocked) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Color.White.copy(alpha = 0.45f)
                    ),
                contentAlignment = Alignment.Center
            ) {
                /*Text(
                    text = "🔒",
                    fontSize = 20.sp
                )*/
            }
        }
    }
}