package com.kroq.myaquariumsimulator.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.kroq.myaquariumsimulator.game.AquariumManager
import com.kroq.myaquariumsimulator.game.CoinLoop
import com.kroq.myaquariumsimulator.game.FishManager
import com.kroq.myaquariumsimulator.game.GameManager
import com.kroq.myaquariumsimulator.model.aquarium.AquariumType
import com.kroq.myaquariumsimulator.model.loadGameState
import com.kroq.myaquariumsimulator.ui.component.AquariumView
import com.kroq.myaquariumsimulator.ui.component.Background
import com.kroq.myaquariumsimulator.ui.component.ConfirmPopup
import com.kroq.myaquariumsimulator.ui.component.ShopPopup
import com.kroq.myaquariumsimulator.ui.component.shop.ShopButton
import kotlinx.coroutines.delay

@Composable
fun GameScreen() {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.toFloat()
    val screenHeight = configuration.screenHeightDp.toFloat()

    var isShopOpen by remember { mutableStateOf(false) }
    var selectedTankState by remember {mutableStateOf(AquariumType.SMALL)}
    var showConfirm by remember { mutableStateOf(false) }

    val aquarium = AquariumManager.getCurrentAquarium(screenWidth, screenHeight)
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        val loaded = loadGameState(context)
        GameManager.initialize(loaded)
        FishManager.initFromGameState(loaded)
        CoinLoop.start(lifecycleOwner, context)
    }

    LaunchedEffect(aquarium.width, aquarium.height) {
        while (true) {
            FishManager.update(
                aquarium.width,
                aquarium.height
            )
            delay(16)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        Background()

        AquariumView(aquarium)

        ShopButton(
            onClick = { isShopOpen = true },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
        )

        Text(
            text = "💰 ${GameManager.state.coins}",
            fontSize = 18.sp,
            color = Color.White
        )

        if (isShopOpen) {
            ShopPopup(
                onClose = { isShopOpen = false },
                onTankSelected = { selectedTank ->
                    selectedTankState = selectedTank
                    showConfirm = true
                }
            )
        }

        if (showConfirm) {
            ConfirmPopup(
                onNo = { showConfirm = false },
                onYes = {
                    AquariumManager.upgrade(
                        context,
                        selectedTankState
                    )

                    showConfirm = false
                    isShopOpen = false
                }
            )
        }
    }
}
