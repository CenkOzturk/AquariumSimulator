package com.kroq.myaquariumsimulator.ui.popup.shop

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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kroq.myaquariumsimulator.managers.TutorialManager
import com.kroq.myaquariumsimulator.model.PlayerTier
import com.kroq.myaquariumsimulator.model.canAccess
import com.kroq.myaquariumsimulator.model.shop.ShopItem
import com.kroq.myaquariumsimulator.model.tutorial.TutorialBoundsType

@Composable
fun ShopItemCard(
    item: ShopItem,
    playerTier: PlayerTier,
    onClick: () -> Unit
) {

    val isUnlocked = playerTier.canAccess(item.requiredTier)

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
                enabled = isUnlocked,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = true)
            ) {
                onClick()
            }
            .padding(10.dp)
            .onGloballyPositioned {
                if (item.id == 100) {
                    TutorialManager.updateBounds(
                        TutorialBoundsType.FIRST_FISH,
                        it
                    )
                }
            }
    ) {

        // TIER BADGE
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(12.dp)
                .background(
                    color = when (item.requiredTier) {
                        PlayerTier.FREE -> Color(0xFF66BB6A)
                        PlayerTier.BRONZE -> Color(0xFFCD7F32)
                        PlayerTier.SILVER -> Color(0xFFC0C0C0)
                        PlayerTier.GOLD -> Color(0xFFFFD700)
                    },
                    shape = CircleShape
                )
        )

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
                item = item,
                alpha = contentAlpha
            )

            Spacer(modifier = Modifier.height(6.dp))

            // PRICE
            Text(
                text = "${item.price} 💰",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFA000).copy(alpha = contentAlpha)
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
                Text(
                    text = "🔒",
                    fontSize = 20.sp
                )
            }
        }
    }
}