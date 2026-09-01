package com.kukurodev.mykukuroaquarium.ui.popup.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kukurodev.mykukuroaquarium.R
import com.kukurodev.mykukuroaquarium.data.Constants.WELCOME_GIFT_DAYS
import com.kukurodev.mykukuroaquarium.model.component.GameColors
import com.kukurodev.mykukuroaquarium.model.welcome.GiftModel
import com.kukurodev.mykukuroaquarium.model.welcome.WelcomeGiftDatabase
import com.kukurodev.mykukuroaquarium.ui.popup.GamePopup
import com.kukurodev.mykukuroaquarium.ui.popup.GeneralPopup

@Composable
fun WelcomeGiftPopup(
    currentDay: Int,
    gift: GiftModel,
    canClaim: Boolean,
    claimedToday: Boolean,
    onClaim: () -> Unit,
    onClose: () -> Unit
) {
    val colors = GameColors.WelcomeGift

    GeneralPopup(
        onClose = onClose
    ) { popupModifier, dismiss ->

        GamePopup(
            modifier = popupModifier,
            title = stringResource(R.string.welcome_gift_title),
            subtitle = stringResource(R.string.welcome_gift_subtitle),
            gradient = colors,
            buttonText = if (canClaim) {
                stringResource(R.string.welcome_gift_claim)
            } else {
                stringResource(R.string.welcome_gift_claimed)
            },
            buttonEnabled = canClaim,
            onButtonClick = onClaim,
            onClose = dismiss
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                WelcomeGiftDatabase
                    .getAllGifts()
                    .forEach { gift ->

                        WelcomeGiftItem(
                            giftModel = gift,
                            claimed = gift.day < currentDay ||
                                    (
                                            gift.day == currentDay &&
                                                    claimedToday
                                            ),
                            current = gift.day == currentDay,
                            lastDay = gift.day == WELCOME_GIFT_DAYS
                        )
                    }
            }

            Spacer(
                Modifier.height(24.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(18.dp)
                    )
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color(0xFFFDF9FF),
                                Color(0xFFF3EAFF)
                            )
                        )
                    )
                    .border(
                        width = 2.dp,
                        color = colors.dark.copy(alpha = .6f),
                        shape = RoundedCornerShape(18.dp)
                    )
            ) {

                Column(
                    modifier = Modifier.padding(
                        horizontal = 20.dp,
                        vertical = 18.dp
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = stringResource(
                            R.string.welcome_gift_today_reward
                        ),
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = colors.dark
                    )

                    Spacer(
                        Modifier.height(10.dp)
                    )

                    Text(
                        text = gift.rewardText,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        color = colors.border
                    )
                }
            }
        }
    }
}