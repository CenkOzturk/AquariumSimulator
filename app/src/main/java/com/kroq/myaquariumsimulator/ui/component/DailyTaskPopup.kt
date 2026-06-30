package com.kroq.myaquariumsimulator.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.model.task.TaskModel

@Composable
fun DailyTaskPopup(
    tasks: List<TaskModel>,
    allCompleted: Boolean,
    totalReward: Int,
    onCollect: () -> Unit,
    onClose: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(0.4f))
            .clickable { onClose() } // dış tık kapatma
    ) {

        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.65f)
                .wrapContentHeight()
                .clip(RoundedCornerShape(28.dp))
                .background(Color(0xFFF8F9FA))
                .clickable(enabled = false) {} // içeride tıklama kapat
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp) // daha sıkı
            ) {
                // HANDLE (shop uyumlu)
                Box(
                    modifier = Modifier
                        .width(34.dp)
                        .height(4.dp)
                        .align(Alignment.CenterHorizontally)
                        .background(Color.Gray.copy(0.4f), CircleShape)
                )

                Spacer(Modifier.height(16.dp))

                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = stringResource(R.string.today_missions).uppercase(),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(8.dp))

                tasks.forEach { task ->
                    TaskRow(task)
                }

                Spacer(Modifier.height(10.dp))

                // 🟢 COMPACT COLLECT BUTTON
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(38.dp) // küçültüldü
                        .clip(RoundedCornerShape(14.dp))
                        .background(
                            if (allCompleted)
                                Color(0xFF4CAF50)
                            else
                                Color(0xFFB3DAAF).copy(alpha = 0.3f)
                        )
                        .clickable(enabled = allCompleted) {
                            onCollect()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (allCompleted)
                            stringResource(R.string.collect, totalReward)
                        else
                            stringResource(R.string.not_complete_tasks),
                        color = Color(0xFF422516),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(Modifier.height(6.dp)) // alt boşluk azaltıldı
            }
        }
    }
}