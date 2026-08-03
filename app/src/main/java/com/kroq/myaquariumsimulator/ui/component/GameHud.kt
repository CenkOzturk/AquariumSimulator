package com.kroq.myaquariumsimulator.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.managers.GameManager
import com.kroq.myaquariumsimulator.model.GameUiState
import com.kroq.myaquariumsimulator.ui.component.buttons.DailyTaskButton

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
            modifier = Modifier.padding(top = 24.dp),
            hasAnyTask = true,
            hasClaimableReward = true,
            onClick = {
                uiState.openDailyTasks()
            }
        )

        DailyTaskButton(
            modifier = Modifier.padding(top = 24.dp),
            hasAnyTask = true,
            hasClaimableReward = true,
            onClick = {
                uiState.openWelcomeGift()
            }
        )
    }
}