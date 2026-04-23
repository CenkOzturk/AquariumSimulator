package com.kroq.myaquariumsimulator.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ConfirmPopup(
    onYes: () -> Unit,
    onNo: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(0.5f)),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .padding(40.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .padding(20.dp)
        ) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Text("Buy this aquarium?", fontSize = 16.sp)

                Spacer(modifier = Modifier.height(20.dp))

                Row {

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.LightGray)
                            .clickable { onNo() }
                            .padding(10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No")
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFF1E88E5))
                            .clickable { onYes() }
                            .padding(10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Yes", color = Color.White)
                    }
                }
            }
        }
    }
}