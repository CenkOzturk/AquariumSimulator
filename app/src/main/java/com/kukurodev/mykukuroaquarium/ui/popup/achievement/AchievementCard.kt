package com.kukurodev.mykukuroaquarium.ui.popup.achievement

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kukurodev.mykukuroaquarium.R
import com.kukurodev.mykukuroaquarium.managers.AchievementManager
import com.kukurodev.mykukuroaquarium.managers.GameManager
import com.kukurodev.mykukuroaquarium.model.achievement.AchievementModel
import com.kukurodev.mykukuroaquarium.model.component.GameColors
import com.kukurodev.mykukuroaquarium.ui.component.buttons.RoundedGameButton

@Composable
fun AchievementCard(
    achievement: AchievementModel,
    onClaim: (Int) -> Unit
) {
    val state = GameManager.state
    val isUnlocked = AchievementManager.isUnlocked(achievement.id, state)
    val isClaimed = AchievementManager.isClaimed(achievement.id, state)

    val cardAlpha = if (isUnlocked || isClaimed) 1.0f else 0.65f

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .alpha(cardAlpha)
            .clip(RoundedCornerShape(20.dp))
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFFFFFFFF),
                        Color(0xFFF1F5F9)
                    )
                )
            )
            .border(
                width = 2.dp,
                color = if (isClaimed) GameColors.Leaf.border else if (isUnlocked) GameColors.Sun.border else Color(0xFFCBD5E1),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(CircleShape)
                    .background(
                        if (isClaimed) GameColors.Leaf.top
                        else if (isUnlocked) GameColors.Sun.top
                        else Color(0xFFE2E8F0)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (isUnlocked || isClaimed) achievement.icon else "🔒",
                    fontSize = 22.sp
                )
            }

            Spacer(Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(achievement.titleResId),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E293B)
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = stringResource(achievement.descriptionResId),
                    fontSize = 12.sp,
                    color = Color(0xFF64748B),
                    lineHeight = 16.sp
                )
            }

            Spacer(Modifier.width(8.dp))

            if (isClaimed) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(GameColors.Leaf.top)
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = "✓ " + stringResource(R.string.achievement_claimed),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = GameColors.Leaf.border
                    )
                }
            } else if (isUnlocked) {
                RoundedGameButton(
                    text = "💰 ${achievement.rewardCoins}",
                    enabled = true,
                    onClick = { onClaim(achievement.id) }
                )
            } else {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFE2E8F0))
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = stringResource(R.string.achievement_locked),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF64748B)
                    )
                }
            }
        }
    }
}
