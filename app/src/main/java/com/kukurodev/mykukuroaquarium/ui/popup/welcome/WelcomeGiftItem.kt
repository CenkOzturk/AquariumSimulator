package com.kukurodev.mykukuroaquarium.ui.popup.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kukurodev.mykukuroaquarium.R
import com.kukurodev.mykukuroaquarium.model.component.GameColors
import com.kukurodev.mykukuroaquarium.model.welcome.GiftModel

@Composable
fun WelcomeGiftItem(
    giftModel: GiftModel,
    claimed: Boolean,
    current: Boolean,
    lastDay: Boolean
) {
    val shellRes = getShellRes(current, claimed, lastDay)
    val itemSize = if (current || lastDay) 48.dp else 38.dp

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.size(itemSize),
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
                    tint = GameColors.LeafDark.top,
                    modifier = Modifier.size(32.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = stringResource(R.string.welcome_gift_day_format, giftModel.day),
            fontSize = if (current) 12.sp else 11.sp,
            fontWeight = if (current) FontWeight.ExtraBold else FontWeight.Bold,
            color = if (current) GameColors.PurpleDark.top else GameColors.Slate.border
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
            if (lastDay) R.drawable.shell_gold_pearl else R.drawable.shell_blue_pearl
        }
        lastDay -> {
            R.drawable.shell_gold_empty
        }
        claimed -> {
            R.drawable.shell_gray_pearl
        }
        else -> {
            R.drawable.shell_gray_empty
        }
    }
}
