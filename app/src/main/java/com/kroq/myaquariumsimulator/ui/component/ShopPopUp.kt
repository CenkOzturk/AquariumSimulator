package com.kroq.myaquariumsimulator.ui.component

import android.content.Context
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.kroq.myaquariumsimulator.game.AquariumManager
import com.kroq.myaquariumsimulator.game.GameManager
import com.kroq.myaquariumsimulator.model.PlayerTier
import com.kroq.myaquariumsimulator.model.aquarium.AquariumType
import com.kroq.myaquariumsimulator.model.shop.ShopTab
import com.kroq.myaquariumsimulator.model.shop.items
import com.kroq.myaquariumsimulator.ui.component.shop.AnimatedShopGrid
import com.kroq.myaquariumsimulator.ui.component.shop.CloseButton
import com.kroq.myaquariumsimulator.ui.component.shop.Handle
import com.kroq.myaquariumsimulator.ui.component.shop.ShopTabs
import com.kroq.myaquariumsimulator.utils.Utils
import kotlinx.coroutines.launch

@Composable
fun ShopPopup(
    context: Context,
    onClose: () -> Unit,
    playerTier: PlayerTier,
    onTankSelected: (AquariumType) -> Unit,
    onFishSelected: (Int) -> Unit,
    onItemSelected: (Int) -> Unit
) {
    val scope = rememberCoroutineScope()

    val offsetY = remember { Animatable(1000f) }
    val alpha = remember { Animatable(0f) }
    val scale = remember { Animatable(0.95f) }

    var currentTab by remember {
        mutableStateOf(GameManager.state.selectedShopTab)
    }

    val currentItems = currentTab.items()

    // 🎬 OPEN ANIMATION (smooth entry)
    LaunchedEffect(Unit) {
        launch {
            offsetY.animateTo(0f, tween(450))
        }
        launch {
            alpha.animateTo(1f, tween(250))
        }
        launch {
            scale.animateTo(1f, tween(300))
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(0.4f * alpha.value))
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 64.dp, start = 16.dp, end = 16.dp)
                .offset { IntOffset(0, offsetY.value.toInt()) }
                .graphicsLayer {
                    this.alpha = alpha.value
                    this.scaleX = scale.value
                    this.scaleY = scale.value
                }
                .background(
                    Color(0xFFF8F9FA),
                    RoundedCornerShape(28.dp)
                )
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDrag = { change, drag ->
                            change.consume()
                            if (drag.y > 0) {
                                scope.launch {
                                    offsetY.snapTo(offsetY.value + drag.y)
                                }
                            }
                        },
                        onDragEnd = {
                            if (offsetY.value > 250f) {
                                scope.launch {
                                    launch { offsetY.animateTo(1000f) }
                                    launch { alpha.animateTo(0f) }
                                    launch { scale.animateTo(0.95f) }
                                    onClose()
                                }
                            } else {
                                scope.launch {
                                    offsetY.animateTo(0f, tween(300))
                                }
                            }
                        }
                    )
                }
        ) {

            Column(modifier = Modifier.fillMaxSize()) {

                Handle(Modifier.align(Alignment.CenterHorizontally))

                // 🔥 TAB SWITCH SMOOTHNESS
                ShopTabs(
                    selected = currentTab,
                    onTabSelected = { tab ->
                        currentTab = tab
                        GameManager.update(context) {
                            it.copy(selectedShopTab = tab)
                        }
                    }
                )

                // 🔥 GRID ENTRY ANIMATION WRAPPER
                AnimatedShopGrid(
                    items = currentItems,
                    onClick = { item ->
                        when (currentTab) {

                            ShopTab.AQUARIUM -> {
                                AquariumType.entries.getOrNull(item.id)?.let {
                                    onTankSelected(it)
                                }
                            }

                            ShopTab.FISH -> {
                                if (GameManager.state.ownedFishIds.count() >=
                                    AquariumManager.getCurrentAquarium().fishCount
                                ) {
                                    Utils.showToast(
                                        message = "Akvaryum sınırına ulaşıldı. Lütfen akvaryumu büyütünüz"
                                    )
                                } else {
                                    onFishSelected(item.id)
                                }
                            }

                            ShopTab.ITEMS -> {
                                onItemSelected(item.id)
                            }
                        }
                    },
                    playerTier = playerTier
                )

                Spacer(modifier = Modifier.weight(1f))

                CloseButton(
                    Modifier.align(Alignment.CenterHorizontally),
                    onClose = {
                        scope.launch {
                            launch { offsetY.animateTo(1000f, tween(300)) }
                            launch { alpha.animateTo(0f, tween(200)) }
                            launch { scale.animateTo(0.95f, tween(200)) }
                            onClose()
                        }
                    }
                )
            }
        }
    }
}