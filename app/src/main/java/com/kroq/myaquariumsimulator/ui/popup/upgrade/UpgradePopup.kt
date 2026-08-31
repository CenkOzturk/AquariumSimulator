package com.kroq.myaquariumsimulator.ui.popup.upgrade

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.model.component.GameColors
import com.kroq.myaquariumsimulator.model.upgrade.UpgradeCategoryTab
import com.kroq.myaquariumsimulator.model.upgrade.UpgradeModel
import com.kroq.myaquariumsimulator.ui.popup.GamePopup
import com.kroq.myaquariumsimulator.ui.popup.GeneralPopup

@Composable
fun UpgradePopup(
    selectedTab: UpgradeCategoryTab,
    onTabSelected: (UpgradeCategoryTab) -> Unit,
    upgrades: List<UpgradeModel>,
    onUpgradeClick: (UpgradeModel) -> Unit,
    onClose: () -> Unit
) {
    var infoUpgrade by remember {
        mutableStateOf<UpgradeModel?>(null)
    }

    GeneralPopup(
        onClose = onClose
    ) { popupModifier, dismiss ->

        GamePopup(
            modifier = popupModifier,
            title = stringResource(R.string.upgrades_title),
            subtitle = stringResource(R.string.upgrades_subtitle),
            gradient = GameColors.Ocean,
            onClose = dismiss
        ) {
            UpgradeTabs(
                selectedTab = selectedTab,
                onTabSelected = onTabSelected
            )

            Spacer(
                Modifier.height(16.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(
                        max = 430.dp
                    ),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(
                    horizontal = 2.dp,
                    vertical = 4.dp
                )
            ) {
                items(
                    items = upgrades,
                    key = {
                        it.id
                    }
                ) { upgrade ->
                    UpgradeCard(
                        upgrade = upgrade,
                        currentLevel = upgrade.currentLevel,
                        isMaxLevel = (upgrade.currentLevel == upgrade.levelList.size),
                        onInfoClick = {
                            infoUpgrade = upgrade
                        },
                        onUpgradeClick = {
                            onUpgradeClick(upgrade)
                        }
                    )
                }
            }
        }
    }

    infoUpgrade?.let { upgrade ->
        UpgradeInfoPopup(
            title = stringResource(upgrade.nameResId),
            desc = stringResource(upgrade.descriptionResId),
            onClose = {
                infoUpgrade = null
            }
        )
    }
}