package com.kroq.myaquariumsimulator.ui.component.popup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kroq.myaquariumsimulator.model.component.GameGradient
import com.kroq.myaquariumsimulator.ui.component.shop.CloseButton

@Composable
fun GamePopup(
    title: String,
    subtitle: String? = null,
    gradient: GameGradient,
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
    widthFraction: Float = .72f,
    buttonText: String? = null,
    buttonEnabled: Boolean = true,
    onButtonClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = .45f))
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onClose()
            },
        contentAlignment = Alignment.Center
    ) {

        Box {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(widthFraction)
                    .clickable(
                        indication = null,
                        interactionSource = remember {
                            MutableInteractionSource()
                        }
                    ) {},
                shape = RoundedCornerShape(30.dp),
                color = Color.Transparent,
                border = BorderStroke(
                    3.dp,
                    Brush.verticalGradient(
                        listOf(
                            gradient.border,
                            gradient.dark
                        )
                    )
                )
            ) {
                Column(
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
                        .padding(
                            horizontal = 20.dp,
                            vertical = 22.dp
                        )
                ) {

                    Spacer(Modifier.height(6.dp))

                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = title,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = gradient.border
                    )

                    if (subtitle != null) {

                        Spacer(Modifier.height(6.dp))

                        Text(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            text = subtitle,
                            fontSize = 13.sp,
                            color = gradient.border.copy(alpha = .85f),
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )

                        Spacer(Modifier.height(22.dp))
                    } else {

                        Spacer(Modifier.height(22.dp))
                    }

                    content()

                    if (buttonText != null && onButtonClick != null) {

                        Spacer(Modifier.height(22.dp))

                        RoundedGameButton(
                            modifier = Modifier.fillMaxWidth(),
                            enabled = buttonEnabled,
                            text = buttonText,
                            onClick = onButtonClick
                        )
                    }
                }
            }

            CloseButton(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(
                        x = 16.dp,
                        y = (-16).dp
                    ),
                gradient = gradient,
                onClose = onClose
            )
        }
    }
}