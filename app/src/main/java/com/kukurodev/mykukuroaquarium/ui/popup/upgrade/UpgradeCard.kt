package com.kukurodev.mykukuroaquarium.ui.popup.upgrade

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kukurodev.mykukuroaquarium.R
import com.kukurodev.mykukuroaquarium.model.upgrade.UpgradeModel
import com.kukurodev.mykukuroaquarium.model.upgrade.getLevel
import com.kukurodev.mykukuroaquarium.ui.component.InfoView

@Composable
fun UpgradeCard(
    upgrade: UpgradeModel,
    currentLevel: Int,
    isMaxLevel: Boolean,
    onInfoClick: () -> Unit,
    onUpgradeClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(20.dp)
            )
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFFF4FDFF),
                        Color(0xFFDDF7FF),
                        Color(0xFFC5EEFA)
                    )
                )
            )
            .border(
                width = 2.dp,
                color = Color(0xFF31B8E6),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(
                horizontal = 10.dp,
                vertical = 10.dp
            )
            .clickable(
                enabled = !isMaxLevel
            ) {
                onUpgradeClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
        ) {

            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 8.dp)
                    .size(62.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        Color.White.copy(.75f)
                    )
                    .border(
                        1.5.dp,
                        Color(0xFF8DD9EE),
                        RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.size(60.dp),
                    painter = painterResource(upgrade.iconResId),
                    contentDescription = null
                )
            }

            InfoView(
                modifier = Modifier.align(Alignment.TopEnd),
                onClick = {
                    onInfoClick()
                }
            )
        }

        Spacer(
            Modifier.height(4.dp)
        )

        Text(
            text = stringResource(
                upgrade.nameResId
            ),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF164D66),
            textAlign = TextAlign.Center,
            maxLines = 2
        )

        Spacer(
            Modifier.height(4.dp)
        )

        Text(
            text = stringResource(upgrade.upgradeResId,
                (upgrade.getLevel(upgrade.currentLevel).value).toString()),
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF5BC2E5)
        )

        Spacer(
            Modifier.height(4.dp)
        )

        Text(
            text = stringResource(
                R.string.upgrade_level_format,
                currentLevel
            ),
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF2AA7D7)
        )

        Spacer(
            Modifier.height(4.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp)
                .clip(
                    RoundedCornerShape(18.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            if (isMaxLevel) {
                Text(
                    text = stringResource(
                        R.string.upgrade_max_level
                    ),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF888888)
                )
            } else {
                Text(
                    text = stringResource(
                        R.string.coin_value,
                        upgrade.getLevel(upgrade.currentLevel).cost),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFE58B16)
                )
            }
        }
    }
}