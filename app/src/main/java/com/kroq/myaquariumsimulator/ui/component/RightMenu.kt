package com.kroq.myaquariumsimulator.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kroq.myaquariumsimulator.managers.CleanerManager
import com.kroq.myaquariumsimulator.managers.DirtManager
import com.kroq.myaquariumsimulator.model.GameUiState
import com.kroq.myaquariumsimulator.ui.component.buttons.CleanButton
import com.kroq.myaquariumsimulator.ui.component.buttons.ShopButton

@Composable
fun RightMenu(
    uiState: GameUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.End
    ) {
        CleanButton(
            visible = DirtManager.needCleaning(),
            onClick = {
                CleanerManager.cleanDirt()
            }
        )

        ShopButton(
            onClick = {
                uiState.openShop()
            }
        )
    }
}