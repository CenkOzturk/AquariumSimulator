package com.kukurodev.mykukuroaquarium.ui.popup.background

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kukurodev.mykukuroaquarium.R
import com.kukurodev.mykukuroaquarium.model.background.BackgroundModel
import com.kukurodev.mykukuroaquarium.model.component.GameColors

@Composable
fun BackgroundCard(
    background: BackgroundModel,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(Color.White)
            .border(
                width = if (isSelected) 3.dp else 1.5.dp,
                color = if (isSelected) GameColors.Leaf.border else Color(0xFFE2E8F0),
                shape = RoundedCornerShape(18.dp)
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = true)
            ) {
                onSelect()
            }
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(68.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(12.dp))
            ) {
                Image(
                    painter = painterResource(background.resId),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                )

                Box(
                    modifier = Modifier
                        .padding(6.dp)
                        .size(14.dp)
                        .clip(CircleShape)
                        .background(background.dominantColor)
                        .border(1.5.dp, Color.White, CircleShape)
                        .align(Alignment.TopStart)
                )
            }

            Spacer(Modifier.height(6.dp))

            Text(
                text = stringResource(background.nameResId),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0F172A),
                maxLines = 1,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(4.dp))

            if (isSelected) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(GameColors.Leaf.top)
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = "✓ " + stringResource(R.string.bg_selected),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = GameColors.Leaf.border
                    )
                }
            } else {
                Spacer(Modifier.height(18.dp))
            }
        }
    }
}
