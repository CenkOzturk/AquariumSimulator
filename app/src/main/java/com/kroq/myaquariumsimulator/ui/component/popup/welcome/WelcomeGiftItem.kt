package com.kroq.myaquariumsimulator.ui.component.popup.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.model.welcome.GiftModel

@Composable
fun WelcomeGiftItem(
    giftModel: GiftModel,
    claimed: Boolean,
    current: Boolean,
    lastDay: Boolean
) {

    val shellRes = getShellRes(current, claimed, lastDay)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier.size(
                size = if (current||lastDay) 40.dp else 34.dp),
            contentAlignment = Alignment.Center
        ) {

            Image(
                painter = painterResource(shellRes),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )

            if (claimed) {
                Icon(
                    painter = painterResource(R.drawable.ic_check_bold),
                    contentDescription = null,
                    tint = Color.Green,
                    modifier = Modifier.size(36.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Day ${giftModel.day}",
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF46637A)
        )
    }
}

private fun getShellRes(
    current: Boolean,
    claimed: Boolean,
    lastDay: Boolean
): Int {
    return when {
        current -> {
            R.drawable.shell_blue_pearl
        }

        lastDay -> {
            if (current) {
                R.drawable.shell_gold_pearl
            } else {
                R.drawable.shell_gold_empty
            }
        }

        claimed -> {
            R.drawable.shell_gray_pearl
        }

        else -> {
            R.drawable.shell_gray_empty
        }
    }
}