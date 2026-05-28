package com.kroq.myaquariumsimulator.game

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.kroq.myaquariumsimulator.game.FishManager.fishes
import com.kroq.myaquariumsimulator.game.ItemManager.items
import com.kroq.myaquariumsimulator.model.GameState
import com.kroq.myaquariumsimulator.model.fish.FishDatabase
import com.kroq.myaquariumsimulator.model.item.ItemDatabase
import com.kroq.myaquariumsimulator.model.saveGameState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object GameManager {
    var state by mutableStateOf(GameState())
        private set

    fun initialize(newState: GameState) {
        state = newState

        val initialFishes = FishDatabase.getFishByIds(state.ownedFishIds)

        fishes.clear()
        fishes.addAll(initialFishes)

        val initialItems = ItemDatabase.getItemByIds(state.ownedItemIds)

        items.clear()
        items.addAll(initialItems)
    }

    fun update(context: Context, reducer: (GameState) -> GameState) {
        val newState = reducer(state)
        state = newState

        CoroutineScope(Dispatchers.IO).launch {
            saveGameState(context, newState)
        }
    }
}