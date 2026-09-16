package com.kukurodev.mykukuroaquarium.ui.popup.inventory

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.kukurodev.mykukuroaquarium.R
import com.kukurodev.mykukuroaquarium.managers.FishManager
import com.kukurodev.mykukuroaquarium.managers.GameManager
import com.kukurodev.mykukuroaquarium.model.component.GameColors
import com.kukurodev.mykukuroaquarium.model.fish.FishDatabase
import com.kukurodev.mykukuroaquarium.model.fish.toInventoryItem
import com.kukurodev.mykukuroaquarium.ui.popup.GamePopup
import com.kukurodev.mykukuroaquarium.ui.popup.GeneralPopup

@Composable
fun InventoryPopUp(
    onClose: () -> Unit
) {
    GeneralPopup(
        onClose = onClose
    ) { popupModifier, dismiss ->
        GamePopup(
            modifier = popupModifier
                .fillMaxWidth(.92f)
                .fillMaxHeight(.48f),
            title = stringResource(R.string.inventory),
            gradient = GameColors.Pink,
            onClose = dismiss
        ) {
            AnimatedInventoryGrid(
                modifier = Modifier.weight(1f),
                items = FishDatabase.getAllFishes()
                    .filter { it.id in GameManager.state.ownedFishIds }
                    .map { it.toInventoryItem() },
                onClick = { fish ->
                    if (GameManager.state.activeFishes.contains(fish.id)) {
                        GameManager.update {
                            it.copy(
                                activeFishes = GameManager.state.activeFishes - fish.id
                            )
                        }
                        FishManager.dropFromGameState()
                        onClose()
                    } else {
                        GameManager.update {
                            it.copy(
                                activeFishes = GameManager.state.activeFishes + fish.id
                            )
                        }
                        FishManager.syncWithGameState()
                        onClose()
                    }
                }
            )
        }
    }
}