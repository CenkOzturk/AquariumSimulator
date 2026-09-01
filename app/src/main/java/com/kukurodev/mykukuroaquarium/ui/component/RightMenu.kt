package com.kukurodev.mykukuroaquarium.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kukurodev.mykukuroaquarium.managers.CleanerManager
import com.kukurodev.mykukuroaquarium.managers.DirtManager
import com.kukurodev.mykukuroaquarium.managers.TutorialManager
import com.kukurodev.mykukuroaquarium.model.GameUiState
import com.kukurodev.mykukuroaquarium.model.upgrade.UpgradeCategoryTab
import com.kukurodev.mykukuroaquarium.ui.component.buttons.CleanButton
import com.kukurodev.mykukuroaquarium.ui.component.buttons.ShopButton
import com.kukurodev.mykukuroaquarium.ui.component.buttons.UpgradeButton

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
                TutorialManager.onCleanClicked()
            }
        )

        Spacer(Modifier.padding(12.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            UpgradeButton(
                modifier = Modifier.padding(bottom = 24.dp),
                onClick = {
                    uiState.openUpgrade(UpgradeCategoryTab.FISH)
                }
            )

            ShopButton(
                modifier = Modifier.padding(bottom = 24.dp),
                onClick = {
                    uiState.openShop()
                    TutorialManager.onShopOpened()
                }
            )
        }
    }
}