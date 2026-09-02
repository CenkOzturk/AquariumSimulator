package com.kukurodev.mykukuroaquarium.ui.popup.task

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kukurodev.mykukuroaquarium.R
import com.kukurodev.mykukuroaquarium.model.component.GameColors
import com.kukurodev.mykukuroaquarium.model.task.TaskModel
import com.kukurodev.mykukuroaquarium.ui.popup.GamePopup
import com.kukurodev.mykukuroaquarium.ui.popup.GeneralPopup

@Composable
fun DailyTaskPopup(
    tasks: List<TaskModel>,
    allCompleted: Boolean,
    totalReward: Int,
    onCollect: () -> Unit,
    onClose: () -> Unit
) {
    val colors = GameColors.DailyTask

    GeneralPopup(
        onClose = onClose
    ) { popupModifier, dismiss ->
        GamePopup(
            modifier = popupModifier,
            title = stringResource(R.string.today_missions),
            subtitle = stringResource(R.string.daily_today_missions_subtitle),
            gradient = colors,
            widthFraction = .68f,
            buttonText = if (allCompleted) {
                stringResource(R.string.collect, totalReward)
            } else {
                stringResource(R.string.not_complete_tasks)
            },
            buttonEnabled = allCompleted,
            onButtonClick = onCollect,
            onClose = dismiss
        ) {

            tasks.forEach { task ->

                TaskRow(task)

                Spacer(Modifier.height(10.dp))
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color.White.copy(.9f),
                                Color(0xFFF4FFF4)
                            )
                        )
                    )
                    .border(
                        width = 2.dp,
                        color = colors.dark.copy(alpha = .45f),
                        shape = RoundedCornerShape(18.dp)
                    )
            ) {

                Column(
                    modifier = Modifier.padding(
                        horizontal = 18.dp,
                        vertical = 16.dp
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = stringResource(R.string.daily_total_reward),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = colors.dark
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = stringResource(
                            R.string.daily_total_reward_coins,
                            totalReward
                        ),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = colors.border
                    )
                }
            }
        }
    }
}