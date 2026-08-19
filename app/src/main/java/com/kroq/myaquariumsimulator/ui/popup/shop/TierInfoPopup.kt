package com.kroq.myaquariumsimulator.ui.popup.shop

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.model.PlayerTier
import com.kroq.myaquariumsimulator.model.component.GameColors
import com.kroq.myaquariumsimulator.ui.popup.GamePopup
import com.kroq.myaquariumsimulator.ui.popup.GeneralPopup

@Composable
fun TierInfoPopup(
    onClose: () -> Unit
) {

    val colors = GameColors.Shop

    GeneralPopup(
        onClose = onClose
    ) { popupModifier, dismiss ->

        GamePopup(
            modifier = popupModifier,
            title = stringResource(R.string.tier_info_title),
            subtitle = stringResource(R.string.tier_info_subtitle),
            gradient = colors,
            widthFraction = .72f,
            buttonText = stringResource(R.string.ok),
            buttonEnabled = true,
            onButtonClick = dismiss,
            onClose = dismiss
        ) {

            TierInfoRow(
                tier = PlayerTier.FREE,
                title = stringResource(R.string.tier_free),
                description = stringResource(R.string.tier_free_description),
                icon = "🌱"
            )

            Spacer(Modifier.height(10.dp))

            TierInfoRow(
                tier = PlayerTier.BRONZE,
                title = stringResource(R.string.tier_bronze),
                description = stringResource(R.string.tier_bronze_description),
                icon = "🥉"
            )

            Spacer(Modifier.height(10.dp))

            TierInfoRow(
                tier = PlayerTier.SILVER,
                title = stringResource(R.string.tier_silver),
                description = stringResource(R.string.tier_silver_description),
                icon = "🥈"
            )

            Spacer(Modifier.height(10.dp))

            TierInfoRow(
                tier = PlayerTier.GOLD,
                title = stringResource(R.string.tier_gold),
                description = stringResource(R.string.tier_gold_description),
                icon = "🥇"
            )
        }
    }
}