package com.kroq.myaquariumsimulator.ui.component

import android.util.Log
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.kroq.myaquariumsimulator.game.AquariumManager
import com.kroq.myaquariumsimulator.game.GameManager
import com.kroq.myaquariumsimulator.model.shop.ShopTab
import com.kroq.myaquariumsimulator.model.aquarium.AquariumType
import com.kroq.myaquariumsimulator.model.shop.items
import com.kroq.myaquariumsimulator.ui.component.shop.CloseButton
import com.kroq.myaquariumsimulator.ui.component.shop.Handle
import com.kroq.myaquariumsimulator.ui.component.shop.ShopGrid
import com.kroq.myaquariumsimulator.ui.component.shop.ShopTabs
import com.kroq.myaquariumsimulator.utils.Utils
import kotlinx.coroutines.launch

@Composable
fun ShopPopup(
    onClose: () -> Unit,
    onTankSelected: (AquariumType) -> Unit,
    onFishSelected: (Int) -> Unit
) {
    val scope = rememberCoroutineScope()
    val offsetY = remember { Animatable(1000f) }
    var selectedTab by remember { mutableStateOf(ShopTab.AQUARIUM) }

    val currentItems = selectedTab.items()

    // 🎬 OPEN ANIMATION
    LaunchedEffect(Unit) {
        offsetY.animateTo(0f, tween(400))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(0.4f))
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .offset { IntOffset(0, offsetY.value.toInt()) }
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
                                    offsetY.animateTo(1000f)
                                    onClose()
                                }
                            } else {
                                scope.launch {
                                    offsetY.animateTo(0f)
                                }
                            }
                        }
                    )
                }
        ) {

            Column(modifier = Modifier.fillMaxSize()) {

                Handle(Modifier.align(Alignment.CenterHorizontally))

                ShopTabs(
                    selected = selectedTab,
                    onTabSelected = { selectedTab = it }
                )

                ShopGrid(
                    items = currentItems,
                    onClick = { item ->
                        when (selectedTab) {
                            ShopTab.AQUARIUM -> {
                                AquariumType.entries.getOrNull(item.id)?.let {
                                    onTankSelected(it)
                                }
                            }

                            ShopTab.FISH -> {
                                if (GameManager.state.ownedFishIds.count() >=
                                    AquariumManager.getCurrentAquarium().fishCount)
                                    Utils.showToast(message = "Akvaryum sınırına ulaşıldı. Daha çok balık alabilmek içön lütfen akvaryumu büyütünüz")
                                else
                                    onFishSelected(item.id)
                            }

                            ShopTab.ITEMS -> {
                                // şimdilik boş
                            }
                        }
                    }
                )

                Spacer(modifier = Modifier.weight(1f))

                CloseButton(
                    Modifier.align(Alignment.CenterHorizontally),
                    onClose = {
                        scope.launch {
                            offsetY.animateTo(1000f, tween(300))
                            onClose()
                        }
                    }
                )
            }
        }
    }
}