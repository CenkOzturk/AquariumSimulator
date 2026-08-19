package com.kroq.myaquariumsimulator.ui.component.settings

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kroq.myaquariumsimulator.R

@Composable
fun SettingsVersionItem(
    version: String
) {
    Surface(
        shape = RoundedCornerShape(18.dp),
        color = Color.White.copy(.10f)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.settings_version),
                color = Color.White,
                modifier = Modifier.weight(1f)
            )

            Text(
                version,
                color = Color.White.copy(.7f)
            )
        }
    }
}