package com.kukurodev.mykukuroaquarium.ui.popup.shop

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kukurodev.mykukuroaquarium.managers.TutorialManager
import com.kukurodev.mykukuroaquarium.model.PlayerTier
import com.kukurodev.mykukuroaquarium.model.canAccess
import com.kukurodev.mykukuroaquarium.model.shop.ShopItem
import com.kukurodev.mykukuroaquarium.model.tutorial.TutorialBoundsType

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
            .padding(4.dp)
            .height(124.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(
                if (isUnlocked) {
                    Color.White
                } else {
                    Color(0xFFF1F5F9)
                }
            )
            .border(
                width = 1.5.dp,
                color = if (isUnlocked) Color(0xFFE2E8F0) else Color(0xFFCBD5E1),
                shape = RoundedCornerShape(18.dp)
            )
            .clickable(
                enabled = isUnlocked,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = true)
            ) {
                onClick()
            }
            .padding(8.dp)
            .onGloballyPositioned {
                if (item.id == 100) {
                    TutorialManager.updateBounds(
                        TutorialBoundsType.FIRST_FISH,
                        it
                    )
                }
            }
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(10.dp)
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
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = item.icon,
                fontSize = 32.sp,
                modifier = Modifier.alpha(contentAlpha)
            )

            Text(
                text = stringResource(item.titleResId),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0F172A).copy(alpha = contentAlpha),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            if (item.extraInfo.isNotEmpty()) {
                ShopItemInfo(
                    shopTab = item.type,
                    extraInfo = item.extraInfo,
                    alpha = contentAlpha
                )
            } else {
                Spacer(modifier = Modifier.height(12.dp))
            }

            Text(
                text = "${item.price} 💰",
                fontSize = 11.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFFD97706).copy(alpha = contentAlpha),
                textAlign = TextAlign.Center
            )
        }

        if (!isUnlocked) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Color.White.copy(alpha = 0.50f)
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
