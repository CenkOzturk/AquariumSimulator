package com.kroq.myaquariumsimulator.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.data.Constants.COIN_SIZE

@Composable
fun CoinView(
    modifier: Modifier = Modifier,
    //amount: Int = 1,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .size(COIN_SIZE.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
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