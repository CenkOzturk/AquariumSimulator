package com.kukurodev.mykukuroaquarium.ui.popup.shop

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kukurodev.mykukuroaquarium.R
import com.kukurodev.mykukuroaquarium.managers.AquariumManager
import com.kukurodev.mykukuroaquarium.managers.GameManager
import com.kukurodev.mykukuroaquarium.model.PlayerTier
import com.kukurodev.mykukuroaquarium.model.aquarium.AquariumType
import com.kukurodev.mykukuroaquarium.model.component.GameColors
import com.kukurodev.mykukuroaquarium.model.shop.ShopItem
import com.kukurodev.mykukuroaquarium.model.shop.ShopTab
import com.kukurodev.mykukuroaquarium.model.shop.items
import com.kukurodev.mykukuroaquarium.ui.popup.GamePopup
import com.kukurodev.mykukuroaquarium.ui.popup.GeneralPopup
import com.kukurodev.mykukuroaquarium.utils.Utils

@Composable
fun ShopPopup(
    onClose: () -> Unit,
    playerTier: PlayerTier,
    onTankSelected: (AquariumType) -> Unit,
    onFishSelected: (ShopItem) -> Unit,
    onItemSelected: (ShopItem) -> Unit
) {
    var currentTab by rememberSaveable {
        mutableStateOf(GameManager.state.selectedShopTab)
    }
    var showTierInfoPopup by rememberSaveable {
        mutableStateOf(false)
    }

    val currentItems = currentTab.items()
    val gradient = GameColors.Sun

    GeneralPopup(
        onClose = onClose
    ) { popupModifier, dismiss ->
        GamePopup(
            modifier = popupModifier
                .fillMaxWidth(0.92f),
            title = stringResource(R.string.shop),
            subtitle = stringResource(R.string.shop_subtitle),
            gradient = gradient,
            onClose = dismiss
        ) {
            ShopTabs(
                selected = currentTab,
                onTabSelected = { tab ->
                    currentTab = tab
                    GameManager.updateSelectedTab(tab)
                }
            )

            Spacer(Modifier.height(8.dp))

            AnimatedShopGrid(
                modifier = Modifier.fillMaxWidth(),
                items = currentItems,
                playerTier = playerTier,
                onClick = { item ->
                    when (currentTab) {
                        ShopTab.AQUARIUM -> {
                            AquariumType.entries
                                .getOrNull(item.id)
                                ?.let(onTankSelected)
                        }

                        ShopTab.FISH -> {
                            if (GameManager.state.activeFishes.size >=
                                AquariumManager.currentAquarium.fishCount
                            ) {
                                Utils.showToast(
                                    R.string.shop_too_much_fish
                                )
                            } else {
                                onFishSelected(item)
                            }
                        }

                        ShopTab.ITEMS -> {
                            onItemSelected(item)
                        }
                    }
                }
            )
        }

        if (showTierInfoPopup) {
            TierInfoPopup(
                onClose = {
                    showTierInfoPopup = false
                }
            )
        }
    }
}
