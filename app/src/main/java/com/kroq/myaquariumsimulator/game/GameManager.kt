package com.kroq.myaquariumsimulator.game

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.kroq.myaquariumsimulator.model.GameState
import com.kroq.myaquariumsimulator.model.saveGameState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object GameManager {

    var state by mutableStateOf(GameState())
        private set

    fun initialize(newState: GameState) {
        state = newState
    }

    fun update(context: Context, reducer: (GameState) -> GameState) {
        val newState = reducer(state)
        state = newState

        CoroutineScope(Dispatchers.IO).launch {
            saveGameState(context, newState)
        }
    }
}