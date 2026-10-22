package com.kukurodev.mykukuroaquarium.ui.tutorial

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kukurodev.mykukuroaquarium.R
import com.kukurodev.mykukuroaquarium.managers.TutorialManager
import com.kukurodev.mykukuroaquarium.model.component.GameColors
import com.kukurodev.mykukuroaquarium.model.tutorial.TutorialStep
import com.kukurodev.mykukuroaquarium.model.tutorial.toUiState
import com.kukurodev.mykukuroaquarium.ui.component.buttons.SmallGameButton

@Composable
fun TutorialPopup() {
    val step = TutorialManager.currentStep
    if (step == TutorialStep.NONE) return
    val gradient = GameColors.Ocean

    val infiniteTransition = rememberInfiniteTransition(label = "TalkingFishTransition")
    val fishYOffset by infiniteTransition.animateFloat(
        initialValue = -3f,
        targetValue = 3f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "FishYBobbing"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.fish_01),
            contentDescription = null,
            modifier = Modifier
                .size(56.dp)
                .offset { IntOffset(0, fishYOffset.dp.roundToPx()) }
        )

        Spacer(Modifier.width(10.dp))

        AnimatedContent(
            targetState = step,
            modifier = Modifier.weight(1f),
            transitionSpec = {
                (scaleIn(initialScale = 0.94f, animationSpec = tween(180)) + fadeIn(tween(180)))
                    .togetherWith(scaleOut(targetScale = 0.94f, animationSpec = tween(150)) + fadeOut(tween(150)))
            },
            label = "SpeechBubbleTransition"
        ) { currentStep ->
            val state = currentStep.toUiState()
            val buttonTextRes = if (currentStep == TutorialStep.FINISH) R.string.btn_finish else R.string.btn_continue

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(26.dp),
                color = gradient.top,
                shadowElevation = 14.dp,
                border = BorderStroke(
                    width = 3.5.dp,
                    brush = Brush.verticalGradient(
                        listOf(
                            gradient.border,
                            gradient.dark,
                            gradient.border
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
                        .padding(horizontal = 16.dp, vertical = 14.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(state.titleResId),
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 16.sp,
                            color = gradient.dark,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(Modifier.height(6.dp))

                        Text(
                            text = stringResource(state.messageResId),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 18.sp,
                            color = gradient.dark,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(Modifier.height(10.dp))

                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            if (state.showContinue) {
                                SmallGameButton(
                                    text = stringResource(buttonTextRes) + " ➔",
                                    onClick = {
                                        TutorialManager.next()
                                    }
                                )
                            } else {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(gradient.border.copy(alpha = 0.15f))
                                        .padding(horizontal = 10.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        text = "⏳ " + stringResource(R.string.waiting_action),
                                        color = gradient.dark,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontStyle = FontStyle.Italic
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
