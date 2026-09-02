package com.kukurodev.mykukuroaquarium.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kukurodev.mykukuroaquarium.R
import com.kukurodev.mykukuroaquarium.managers.GameManager
import com.kukurodev.mykukuroaquarium.managers.TutorialManager
import com.kukurodev.mykukuroaquarium.model.GameUiState
import com.kukurodev.mykukuroaquarium.model.tutorial.TutorialBoundsType
import com.kukurodev.mykukuroaquarium.ui.component.buttons.DailyTaskButton
import com.kukurodev.mykukuroaquarium.ui.component.buttons.WelcomeGiftButton

@Composable
fun GameHud(
    uiState: GameUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ResourceBadge(
                stringResource(
                    R.string.coin_value,
                    GameManager.state.coins
                )
            )

            ResourceBadge(
                stringResource(
                    R.string.fish_food_value,
                    GameManager.state.foodCount
                )
            )

            ResourceBadge(
                stringResource(
                    R.string.cleaner_value,
                    GameManager.state.cleanerCount
                )
            )
        }

        DailyTaskButton(
            modifier = Modifier
                .padding(top = 24.dp)
                .onGloballyPositioned {
                    TutorialManager.updateBounds(
                        TutorialBoundsType.DAILY_TASK,
                        it
                    )
                },
            hasAnyTask = true,
            hasClaimableReward = true,
            onClick = {
                uiState.openDailyTasks()
                TutorialManager.next()
            }
        )

        WelcomeGiftButton(
            modifier = Modifier.padding(top = 8.dp),
            hasAnyTask = true,
            hasClaimableReward = true,
            onClick = {
                uiState.openWelcomeGift()
            }
        )
    }
}