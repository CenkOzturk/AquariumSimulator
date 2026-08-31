package com.kroq.myaquariumsimulator.ui.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kroq.myaquariumsimulator.data.Constants.BASE_COIN_EXPIRE_TIME
import com.kroq.myaquariumsimulator.managers.AudioManager
import com.kroq.myaquariumsimulator.managers.GameManager
import com.kroq.myaquariumsimulator.managers.TutorialManager
import com.kroq.myaquariumsimulator.managers.UpgradeManager
import com.kroq.myaquariumsimulator.model.CoinModel
import com.kroq.myaquariumsimulator.model.SoundEffect
import com.kroq.myaquariumsimulator.model.upgrade.UpgradeType
import kotlinx.coroutines.delay

@Composable
fun AnimatedCoin(
    coin: CoinModel,
    onClick: (Float) -> Unit,
    onExpired: () -> Unit
) {
    val animatedY = remember {
        Animatable(coin.startY)
    }

    LaunchedEffect(Unit) {
        animatedY.animateTo(
            targetValue = coin.startY - 20f,
            animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
        )
        animatedY.animateTo(
            targetValue = coin.targetY,
            animationSpec = tween(durationMillis = 2000, easing = LinearEasing)
        )
        if (!GameManager.state.tutorialCompleted) {
            return@LaunchedEffect
        }
        delay(BASE_COIN_EXPIRE_TIME + UpgradeManager.getUpgradeValue(UpgradeType.COIN_DURATION))
        onExpired()
    }

    CoinView(
        modifier = Modifier.offset(x = coin.x.dp, y = animatedY.value.dp),
        amount = coin.amount,
        onClick = {
            onClick(animatedY.value)
            if (!GameManager.state.tutorialCompleted) {
                TutorialManager.onCoinCollected()
            }
            AudioManager.playEffect(SoundEffect.COIN_COLLECT)
        }
    )
}