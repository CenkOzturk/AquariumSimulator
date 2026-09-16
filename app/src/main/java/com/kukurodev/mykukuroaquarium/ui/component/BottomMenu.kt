package com.kukurodev.mykukuroaquarium.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.visible
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import com.kukurodev.mykukuroaquarium.R
import com.kukurodev.mykukuroaquarium.managers.CleanerManager
import com.kukurodev.mykukuroaquarium.managers.DirtManager
import com.kukurodev.mykukuroaquarium.managers.TutorialManager
import com.kukurodev.mykukuroaquarium.model.GameUiState
import com.kukurodev.mykukuroaquarium.model.component.GameColors
import com.kukurodev.mykukuroaquarium.model.tutorial.TutorialBoundsType
import com.kukurodev.mykukuroaquarium.model.upgrade.UpgradeCategoryTab
import com.kukurodev.mykukuroaquarium.ui.component.buttons.MenuButton

@Composable
fun BottomMenu(
    uiState: GameUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.End
    ) {
        MenuButton(
            modifier = Modifier
                .visible(DirtManager.needCleaning())
                .onGloballyPositioned {
                    TutorialManager.updateBounds(
                        TutorialBoundsType.CLEAN,
                        it
                    )
                },
            gradient = GameColors.Pink,
            imgResId = R.drawable.ic_clean,
            imgSize = 52,
            onClick = {
                CleanerManager.cleanDirt()
                TutorialManager.onCleanClicked()
            }
        )

        Row(
            Modifier.padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MenuButton(
                gradient = GameColors.Pink,
                imgResId = R.drawable.ic_inventory,
                imgSize = 56,
                onClick = {
                    uiState.openInventory()
                }
            )

            MenuButton(
                gradient = GameColors.Ocean,
                imgResId = R.drawable.ic_upgrade,
                imgSize = 52,
                onClick = {
                    uiState.openUpgrade(UpgradeCategoryTab.FISH)
                }
            )

            MenuButton(
                modifier = Modifier.onGloballyPositioned {
                    TutorialManager.updateBounds(
                        TutorialBoundsType.SHOP,
                        it
                    )
                },
                gradient = GameColors.Sun,
                imgResId = R.drawable.ic_shop,
                imgSize = 52,
                onClick = {
                    uiState.openShop()
                    TutorialManager.onShopOpened()
                }
            )
        }
    }
}