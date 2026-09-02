package com.kukurodev.mykukuroaquarium.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import com.kukurodev.mykukuroaquarium.R
import com.kukurodev.mykukuroaquarium.managers.CoinManager.getCoinSize
import com.kukurodev.mykukuroaquarium.managers.TutorialManager
import com.kukurodev.mykukuroaquarium.model.tutorial.TutorialBoundsType

@Composable
fun CoinView(
    modifier: Modifier = Modifier,
    amount: Int = 1,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .size(getCoinSize(amount))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .onGloballyPositioned {
                TutorialManager.updateBounds(
                    TutorialBoundsType.COLLECT_COIN,
                    it
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.ic_coin),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        /*Text(
            modifier = Modifier.align(Alignment.Center),
            text = amount.toString(),
            fontSize = 16.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.SansSerif,
            color = Color(0xFF6B3E00),
            textAlign = TextAlign.Center
        )*/
    }
}