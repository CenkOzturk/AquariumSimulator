package com.kroq.myaquariumsimulator.ui.popup.task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kroq.myaquariumsimulator.model.task.TaskModel

@Composable
fun TaskRow(task: TaskModel) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            task.type.name,
            fontSize = 14.sp
        )

        Text(
            "${task.progress}/${task.target}",
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}