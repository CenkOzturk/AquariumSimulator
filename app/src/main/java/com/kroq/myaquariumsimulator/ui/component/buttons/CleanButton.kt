package com.kroq.myaquariumsimulator.ui.component.buttons

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.managers.TutorialManager
import com.kroq.myaquariumsimulator.model.tutorial.TutorialBoundsType

@Composable
fun CleanButton(
    visible: Boolean,
    onClick: () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + scaleIn(),
        exit = fadeOut() + scaleOut()
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFFFC4C4),
                            Color(0xFFFF8A8A),
                            Color(0xFFE95C5C)
                        )
                    )
                )
                .border(
                    width = 2.dp,
                    color = Color(0xFFC94343),
                    shape = CircleShape
                )
                .onGloballyPositioned {
                    TutorialManager.updateBounds(
                        TutorialBoundsType.CLEAN,
                        it
                    )
                }
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {

            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(CircleShape)
                    .background(
                        Color.White.copy(alpha = 0.14f)
                    )
            )

            Image(
                painter = painterResource(R.drawable.ic_clean),
                contentDescription = null,
                modifier = Modifier.size(52.dp)
            )
        }
    }
}