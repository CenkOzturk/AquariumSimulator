package com.kukurodev.mykukuroaquarium.ui.component

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
import com.kukurodev.mykukuroaquarium.data.Constants.BASE_COIN_EXPIRE_TIME
import com.kukurodev.mykukuroaquarium.managers.AudioManager
import com.kukurodev.mykukuroaquarium.managers.GameManager
import com.kukurodev.mykukuroaquarium.managers.TutorialManager
import com.kukurodev.mykukuroaquarium.managers.UpgradeManager
import com.kukurodev.mykukuroaquarium.model.CoinModel
import com.kukurodev.mykukuroaquarium.model.SoundEffect
import com.kukurodev.mykukuroaquarium.model.upgrade.UpgradeType
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