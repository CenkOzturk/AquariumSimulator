package com.kukurodev.mykukuroaquarium.ui.popup

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kukurodev.mykukuroaquarium.R
import com.kukurodev.mykukuroaquarium.managers.ForceUpdateInfo
import com.kukurodev.mykukuroaquarium.model.component.GameColors
import com.kukurodev.mykukuroaquarium.ui.component.buttons.RoundedGameButton

@Composable
fun ForceUpdatePopup(
    info: ForceUpdateInfo
) {
    BackHandler(enabled = true) {
        // Prevent dismissing force update dialog on back press
    }

    val context = LocalContext.current
    val colors = GameColors.Ocean

    val titleText = info.title.ifEmpty { stringResource(R.string.force_update_title) }
    val messageText = info.message.ifEmpty { stringResource(R.string.force_update_message) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.75f))
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                // Prevent outside tap dismissal
            },
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.82f)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {},
            shape = RoundedCornerShape(30.dp),
            color = Color.Transparent,
            border = BorderStroke(
                3.dp,
                Brush.verticalGradient(
                    listOf(
                        colors.border,
                        colors.dark
                    )
                )
            )
        ) {
            Column(
                modifier = Modifier
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                colors.top,
                                colors.light,
                                colors.base
                            )
                        )
                    )
                    .padding(horizontal = 22.dp, vertical = 26.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "🐟",
                    fontSize = 42.sp
                )

                Spacer(Modifier.height(10.dp))

                Text(
                    text = titleText,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = colors.border,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(12.dp))

                Text(
                    text = messageText,
                    fontSize = 14.sp,
                    color = colors.border.copy(alpha = 0.90f),
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp
                )

                Spacer(Modifier.height(24.dp))

                RoundedGameButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.force_update_button),
                    enabled = true,
                    onClick = {
                        val packageName = context.packageName
                        val updateUrl = info.updateUrl.ifEmpty {
                            "https://play.google.com/store/apps/details?id=$packageName"
                        }
                        try {
                            val playStoreIntent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")).apply {
                                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            }
                            context.startActivity(playStoreIntent)
                        } catch (e: Exception) {
                            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(updateUrl)).apply {
                                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            }
                            context.startActivity(browserIntent)
                        }
                    }
                )
            }
        }
    }
}
