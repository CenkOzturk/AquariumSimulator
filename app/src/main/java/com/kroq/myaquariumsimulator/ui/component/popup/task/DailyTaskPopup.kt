package com.kroq.myaquariumsimulator.ui.component.popup.task

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
import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.model.component.GameColors
import com.kroq.myaquariumsimulator.model.task.TaskModel

@Composable
fun DailyTaskPopup(
    tasks: List<TaskModel>,
    allCompleted: Boolean,
    totalReward: Int,
    onCollect: () -> Unit,
    onClose: () -> Unit
) {
    val colors = GameColors.DailyTask

    _root_ide_package_.com.kroq.myaquariumsimulator.ui.component.popup.GeneralPopup(
        onClose = onClose
    ) { popupModifier, dismiss ->

        _root_ide_package_.com.kroq.myaquariumsimulator.ui.component.popup.GamePopup(
            modifier = popupModifier,
            title = stringResource(R.string.today_missions),
            subtitle = "Complete every mission to claim today's reward.",
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
                        text = "TOTAL REWARD",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = colors.dark
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "$totalReward Coins",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = colors.border
                    )
                }
            }
        }
    }
}