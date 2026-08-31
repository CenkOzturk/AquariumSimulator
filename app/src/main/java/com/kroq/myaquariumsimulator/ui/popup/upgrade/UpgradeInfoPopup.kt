package com.kroq.myaquariumsimulator.ui.popup.upgrade

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kroq.myaquariumsimulator.model.component.GameColors
import com.kroq.myaquariumsimulator.ui.popup.GamePopup
import com.kroq.myaquariumsimulator.ui.popup.GeneralPopup

@Composable
fun UpgradeInfoPopup(
    title: String,
    desc: String,
    onClose: () -> Unit
) {
    GeneralPopup(
        onClose = onClose
    ) { popupModifier, dismiss ->

        GamePopup(
            modifier = popupModifier,
            title = title,
            subtitle = null,
            gradient = GameColors.Ocean,
            onClose = dismiss
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(18.dp)
                    )
                    .background(
                        Color.White.copy(.55f)
                    )
                    .border(
                        1.5.dp,
                        Color.White.copy(.7f),
                        RoundedCornerShape(18.dp)
                    )
                    .padding(20.dp)
            ) {

                Text(
                    text = desc,
                    fontSize = 14.sp,
                    color = Color(0xFF164D66),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}