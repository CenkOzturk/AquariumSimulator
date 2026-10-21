package com.kukurodev.mykukuroaquarium.ui.tutorial

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kukurodev.mykukuroaquarium.R
import com.kukurodev.mykukuroaquarium.managers.TutorialManager
import com.kukurodev.mykukuroaquarium.model.component.GameColors
import com.kukurodev.mykukuroaquarium.model.tutorial.TutorialStep
import com.kukurodev.mykukuroaquarium.model.tutorial.toUiState
import com.kukurodev.mykukuroaquarium.ui.component.buttons.RoundedGameButton

@Composable
fun TutorialPopup() {
    val step = TutorialManager.currentStep
    if (step == TutorialStep.NONE) return
    val state = step.toUiState()
    val gradient = GameColors.Ocean

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 10.dp),
        shape = RoundedCornerShape(28.dp),
        color = gradient.top,
        shadowElevation = 14.dp,
        border = BorderStroke(
            width = 3.dp,
            brush = Brush.verticalGradient(
                listOf(
                    gradient.border,
                    gradient.dark
                )
            )
        )
    ) {
        Box(
            modifier = Modifier
                .background(
                    Brush.verticalGradient(
                        listOf(
                            gradient.top,
                            gradient.light,
                            gradient.base
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier.padding(18.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.verticalGradient(
                                    listOf(
                                        gradient.top,
                                        gradient.light
                                    )
                                )
                            )
                            .border(2.dp, gradient.border, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "🐠",
                            fontSize = 22.sp
                        )
                    }

                    Spacer(Modifier.width(12.dp))

                    Column {
                        Text(
                            text = stringResource(R.string.tutorial_title),
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 17.sp,
                            color = gradient.dark
                        )
                        Text(
                            text = stringResource(state.titleResId),
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = gradient.border
                        )
                    }
                }

                Spacer(Modifier.height(12.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(18.dp))
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    gradient.top,
                                    gradient.light
                                )
                            )
                        )
                        .border(
                            width = 1.5.dp,
                            color = gradient.border.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(18.dp)
                        )
                        .padding(14.dp)
                ) {
                    Text(
                        text = stringResource(state.messageResId),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 20.sp,
                        color = gradient.dark
                    )
                }

                Spacer(Modifier.height(14.dp))

                if (state.showContinue) {
                    RoundedGameButton(
                        modifier = Modifier.align(Alignment.End),
                        text = stringResource(R.string.btn_continue),
                        enabled = true,
                        onClick = {
                            TutorialManager.next()
                        }
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .align(Alignment.End)
                            .clip(RoundedCornerShape(14.dp))
                            .background(gradient.border.copy(alpha = 0.12f))
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "⏳ " + stringResource(R.string.waiting_action),
                            color = gradient.dark,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic
                        )
                    }
                }
            }
        }
    }
}
