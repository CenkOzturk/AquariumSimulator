package com.kukurodev.mykukuroaquarium.ui.popup.achievement

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kukurodev.mykukuroaquarium.R
import com.kukurodev.mykukuroaquarium.managers.AchievementManager
import com.kukurodev.mykukuroaquarium.model.component.GameColors
import com.kukurodev.mykukuroaquarium.ui.popup.GamePopup
import com.kukurodev.mykukuroaquarium.ui.popup.GeneralPopup

@Composable
fun AchievementPopup(
    onClose: () -> Unit
) {
    val gradient = GameColors.PurpleDark
    val achievements = AchievementManager.getAchievements()

    GeneralPopup(
        onClose = onClose
    ) { popupModifier, dismiss ->
        GamePopup(
            modifier = popupModifier,
            title = stringResource(R.string.achievements_title),
            subtitle = stringResource(R.string.achievements_subtitle),
            gradient = gradient,
            widthFraction = 0.85f,
            onClose = dismiss
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(380.dp),
                contentPadding = PaddingValues(vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(achievements, key = { it.id }) { achievement ->
                    AchievementCard(
                        achievement = achievement,
                        onClaim = { id ->
                            AchievementManager.claimReward(id)
                        }
                    )
                }
            }
        }
    }
}
