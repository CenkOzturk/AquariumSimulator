package com.kroq.myaquariumsimulator.ui.popup.welcome

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kroq.myaquariumsimulator.data.Constants.WELCOME_GIFT_DAYS
import com.kroq.myaquariumsimulator.model.component.GameColors
import com.kroq.myaquariumsimulator.model.welcome.GiftModel
import com.kroq.myaquariumsimulator.model.welcome.WelcomeGiftDatabase
import com.kroq.myaquariumsimulator.ui.popup.GamePopup
import com.kroq.myaquariumsimulator.ui.popup.GeneralPopup

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
            title = "Welcome Gift",
            subtitle = "Come back every day to collect rewards!",
            gradient = colors,
            buttonText = if (canClaim) {
                "Claim Reward"
            } else {
                "Already Claimed"
            },
            buttonEnabled = canClaim,
            onButtonClick = onClaim,
            onClose = dismiss
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                WelcomeGiftDatabase.getAllGifts().forEach { gift ->
                    WelcomeGiftItem(
                        giftModel = gift,
                        claimed = gift.day < currentDay ||
                                (gift.day == currentDay && claimedToday),
                        current = gift.day == currentDay,
                        lastDay = gift.day == WELCOME_GIFT_DAYS
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
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
                        text = "TODAY'S REWARD",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = colors.dark
                    )

                    Spacer(Modifier.height(10.dp))

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