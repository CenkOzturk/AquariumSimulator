package com.kroq.myaquariumsimulator.ui.popup

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.model.component.GameColors

@Composable
fun ResetProgressPopup(
    onClose: () -> Unit,
    onReset: () -> Unit
) {

    val colors = GameColors.Red

    GeneralPopup(
        onClose = onClose
    ) { popupModifier, dismiss ->

        GamePopup(
            modifier = popupModifier,
            title = stringResource(R.string.reset_progress_title),
            subtitle = stringResource(R.string.reset_progress_subtitle),
            gradient = colors,
            widthFraction = .68f,
            buttonText = stringResource(R.string.reset_progress_button),
            buttonEnabled = true,
            onButtonClick = {
                onReset()
                dismiss()
            },
            onClose = dismiss
        ) {

            ResetWarningRow(
                icon = "🐟",
                text = stringResource(R.string.reset_progress_fish)
            )

            Spacer(Modifier.height(8.dp))

            ResetWarningRow(
                icon = "🪸",
                text = stringResource(R.string.reset_progress_items)
            )

            Spacer(Modifier.height(8.dp))

            ResetWarningRow(
                icon = "🏠",
                text = stringResource(R.string.reset_progress_aquarium)
            )

            Spacer(Modifier.height(8.dp))

            ResetWarningRow(
                icon = "💰",
                text = stringResource(R.string.reset_progress_coins)
            )

            Spacer(Modifier.height(14.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color(0xFFFFF5F5),
                                Color(0xFFFFEAEA)
                            )
                        )
                    )
                    .border(
                        width = 2.dp,
                        color = colors.dark.copy(alpha = .35f),
                        shape = RoundedCornerShape(18.dp)
                    )
            ) {
                Text(
                    text = stringResource(R.string.reset_progress_warning),
                    modifier = Modifier.padding(
                        horizontal = 18.dp,
                        vertical = 14.dp
                    ),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = colors.dark,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}