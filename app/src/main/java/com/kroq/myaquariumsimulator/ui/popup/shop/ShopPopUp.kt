package com.kroq.myaquariumsimulator.ui.popup.shop

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.managers.AquariumManager
import com.kroq.myaquariumsimulator.managers.GameManager
import com.kroq.myaquariumsimulator.model.PlayerTier
import com.kroq.myaquariumsimulator.model.aquarium.AquariumType
import com.kroq.myaquariumsimulator.model.component.GameColors
import com.kroq.myaquariumsimulator.model.shop.ShopItem
import com.kroq.myaquariumsimulator.model.shop.ShopTab
import com.kroq.myaquariumsimulator.model.shop.items
import com.kroq.myaquariumsimulator.ui.component.buttons.CloseButton
import com.kroq.myaquariumsimulator.ui.popup.GeneralPopup
import com.kroq.myaquariumsimulator.utils.Utils

@Composable
fun ShopPopup(
    onClose: () -> Unit,
    playerTier: PlayerTier,
    onTankSelected: (AquariumType) -> Unit,
    onFishSelected: (ShopItem) -> Unit,
    onItemSelected: (ShopItem) -> Unit
) {

    var currentTab by remember {
        mutableStateOf(GameManager.state.selectedShopTab)
    }

    val currentItems = currentTab.items()

    GeneralPopup(
        onClose = onClose
    ) { popupModifier, dismiss ->

        Box(
            modifier = popupModifier
                .fillMaxWidth(.92f)
                .fillMaxHeight(.68f)
        ) {

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clip(RoundedCornerShape(30.dp))
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                GameColors.Shop.top,
                                GameColors.Shop.light,
                                GameColors.Shop.base
                            )
                        )
                    )
                    .border(
                        width = 3.dp,
                        brush = Brush.verticalGradient(
                            listOf(
                                GameColors.Shop.border,
                                GameColors.Shop.dark
                            )
                        ),
                        shape = RoundedCornerShape(30.dp)
                    )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            horizontal = 20.dp,
                            vertical = 18.dp
                        )
                ) {

                    Spacer(Modifier.height(8.dp))

                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = "Shop",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = GameColors.Shop.dark
                    )

                    Spacer(Modifier.height(4.dp))

                    ShopTabs(
                        selected = currentTab,
                        onTabSelected = { tab ->
                            currentTab = tab
                            GameManager.updateSelectedTab(tab)
                        }
                    )

                    AnimatedShopGrid(
                        modifier = Modifier.weight(1f),
                        items = currentItems,
                        playerTier = playerTier,
                        onClick = { item ->

                            when (currentTab) {

                                ShopTab.AQUARIUM -> {
                                    AquariumType.entries
                                        .getOrNull(item.id)
                                        ?.let(onTankSelected)
                                }

                                ShopTab.FISH -> {

                                    if (GameManager.state.ownedFishIds.size >=
                                        AquariumManager.currentAquarium.fishCount
                                    ) {

                                        Utils.showToast(
                                            R.string.shop_too_much_fish
                                        )

                                    } else {

                                        onFishSelected(item)
                                    }
                                }

                                ShopTab.ITEMS -> {
                                    onItemSelected(item)
                                }
                            }
                        }
                    )
                }
            }

            CloseButton(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(
                        x = 16.dp,
                        y = (-16).dp
                    ),
                gradient = GameColors.Shop,
                onClose = dismiss
            )
        }
    }
}