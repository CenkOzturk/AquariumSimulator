package com.kukurodev.mykukuroaquarium.ui.tutorial

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kukurodev.mykukuroaquarium.R
import com.kukurodev.mykukuroaquarium.managers.TutorialManager
import com.kukurodev.mykukuroaquarium.model.tutorial.TutorialStep
import com.kukurodev.mykukuroaquarium.model.tutorial.toUiState

@Composable
fun TutorialPopup() {
    val step = TutorialManager.currentStep
    if (step == TutorialStep.NONE) return
    val state = step.toUiState()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFDFBF5)
        ),
        elevation = CardDefaults.cardElevation(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "🐠",
                    fontSize = 28.sp
                )

                Spacer(Modifier.width(12.dp))

                Text(
                    text = stringResource(R.string.tutorial_title),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color(0xFF184A6C)
                )
            }

            Spacer(Modifier.height(16.dp))

            Text(
                text = stringResource(state.titleResId),
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = stringResource(state.messageResId),
                fontSize = 15.sp,
                lineHeight = 22.sp,
                color = Color.DarkGray
            )

            Spacer(Modifier.height(20.dp))

            if (state.showContinue) {
                Button(
                    modifier = Modifier.align(Alignment.End),
                    onClick = {
                        TutorialManager.next()
                    }
                ) {
                    Text(stringResource(R.string.btn_continue))
                }

            } else {
                Text(
                    modifier = Modifier.align(Alignment.End),
                    text = stringResource(R.string.waiting_action),
                    color = Color.Gray,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}