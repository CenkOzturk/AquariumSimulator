package com.kukurodev.mykukuroaquarium.ui.popup.background

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kukurodev.mykukuroaquarium.R
import com.kukurodev.mykukuroaquarium.managers.GameManager
import com.kukurodev.mykukuroaquarium.model.background.BackgroundDatabase
import com.kukurodev.mykukuroaquarium.model.component.GameColors
import com.kukurodev.mykukuroaquarium.ui.popup.GamePopup
import com.kukurodev.mykukuroaquarium.ui.popup.GeneralPopup

@Composable
fun BackgroundPopup(
    onClose: () -> Unit
) {
    val gradient = GameColors.SunDark
    val backgrounds = BackgroundDatabase.getAllBackgrounds()
    val activeBgId = GameManager.state.activeBackgroundId

    GeneralPopup(
        onClose = onClose
    ) { popupModifier, dismiss ->
        GamePopup(
            modifier = popupModifier
                .fillMaxWidth(0.92f),
            title = stringResource(R.string.backgrounds_title),
            subtitle = stringResource(R.string.backgrounds_subtitle),
            gradient = gradient,
            onClose = dismiss
        ) {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 310.dp),
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(4.dp)
            ) {
                items(backgrounds, key = { it.id }) { bg ->
                    BackgroundCard(
                        background = bg,
                        isSelected = bg.id == activeBgId,
                        onSelect = {
                            GameManager.update {
                                it.copy(activeBackgroundId = bg.id)
                            }
                        }
                    )
                }
            }
        }
    }
}
