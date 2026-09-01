package com.kukurodev.mykukuroaquarium.ui.component.settings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kukurodev.mykukuroaquarium.R
import com.kukurodev.mykukuroaquarium.model.AppLanguage

@Composable
fun SettingsLanguageItem(
    language: AppLanguage,
    onLanguageSelected: (AppLanguage) -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    expanded = true
                },
            shape = RoundedCornerShape(18.dp),
            color = Color.White.copy(.14f),
            border = BorderStroke(
                1.dp,
                Color.White.copy(.30f)
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp)
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = stringResource(
                        R.string.settings_language
                    ),
                    color = Color.White,
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = when (language) {
                        AppLanguage.ENGLISH -> "English"
                        AppLanguage.TURKISH -> "Türkçe"
                    },
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    Modifier.width(6.dp)
                )

                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
            modifier = Modifier
                .background(
                    Color(0xFF2196D3),
                    RoundedCornerShape(16.dp)
                )
        ) {
            DropdownMenuItem(
                text = {
                    Text(
                        text = "English",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                onClick = {
                    expanded = false
                    onLanguageSelected(
                        AppLanguage.ENGLISH
                    )
                }
            )

            DropdownMenuItem(
                text = {
                    Text(
                        text = "Türkçe",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                onClick = {
                    expanded = false
                    onLanguageSelected(
                        AppLanguage.TURKISH
                    )
                }
            )
        }
    }
}