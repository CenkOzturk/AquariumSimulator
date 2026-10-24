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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kukurodev.mykukuroaquarium.R
import com.kukurodev.mykukuroaquarium.data.Constants.WELCOME_GIFT_DAYS
import com.kukurodev.mykukuroaquarium.managers.GameManager
import com.kukurodev.mykukuroaquarium.managers.WelcomeGiftManager
import com.kukurodev.mykukuroaquarium.model.component.GameColors
import com.kukurodev.mykukuroaquarium.model.welcome.GiftModel
import com.kukurodev.mykukuroaquarium.model.welcome.WelcomeGiftDatabase
import com.kukurodev.mykukuroaquarium.ui.popup.GamePopup
import com.kukurodev.mykukuroaquarium.ui.popup.GeneralPopup
import com.kukurodev.mykukuroaquarium.utils.Utils
import kotlinx.coroutines.delay

@Composable
fun WelcomeGiftPopup(
    currentDay: Int,
    gift: GiftModel,
    canClaim: Boolean,
    claimedToday: Boolean,
    onClaim: () -> Unit,
    onClose: () -> Unit
) {
    var currentTime by remember {
        mutableLongStateOf(System.currentTimeMillis())
    }

    LaunchedEffect(Unit) {
        while (true) {
            currentTime = System.currentTimeMillis()
            WelcomeGiftManager.refreshIfNeeded()
            delay(1000L)
        }
    }

    val colors = GameColors.Purple
    val remainingTime = WelcomeGiftManager.remainingTime(currentTime)
    val currentCanClaim = WelcomeGiftManager.canClaim()
    val activeDay = WelcomeGiftManager.currentDay()
    val activeGift = WelcomeGiftManager.currentGift()

    GeneralPopup(
        onClose = onClose
    ) { popupModifier, dismiss ->
        GamePopup(
            modifier = popupModifier,
            title = stringResource(R.string.welcome_gift_title),
            subtitle = stringResource(R.string.welcome_gift_subtitle),
            gradient = colors,
            buttonText = if (currentCanClaim) {
                stringResource(R.string.welcome_gift_claim)
            } else {
                stringResource(R.string.welcome_gift_claimed)
            },
            buttonEnabled = currentCanClaim,
            onButtonClick = onClaim,
            onClose = dismiss
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                WelcomeGiftDatabase
                    .getAllGifts()
                    .forEach { item ->
                        WelcomeGiftItem(
                            giftModel = item,
                            claimed = item.day < activeDay || (item.day == activeDay && !currentCanClaim),
                            current = item.day == activeDay,
                            lastDay = item.day == WELCOME_GIFT_DAYS
                        )
                    }
            }

            Spacer(Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                colors.top,
                                colors.light
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
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.welcome_gift_today_reward),
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = colors.dark,
                        textAlign = TextAlign.Center
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = if (activeGift.rewardResId != 0) stringResource(activeGift.rewardResId) else "",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp,
                        color = colors.border,
                        textAlign = TextAlign.Center
                    )
                }
            }

            if (!currentCanClaim && GameManager.state.welcomeGiftDay != WELCOME_GIFT_DAYS) {
                Spacer(Modifier.height(10.dp))

                val redGradient = GameColors.Red

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(18.dp))
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    redGradient.top,
                                    redGradient.light
                                )
                            )
                        )
                        .border(
                            width = 1.5.dp,
                            color = redGradient.dark,
                            shape = RoundedCornerShape(18.dp)
                        )
                        .padding(horizontal = 14.dp, vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "⏳ " + stringResource(R.string.welcome_gift_next_reward_in) + " ",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = redGradient.dark
                        )
                        Text(
                            text = Utils.formatRemainingTime(remainingTime),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = redGradient.border
                        )
                    }
                }
            }
        }
    }
}
