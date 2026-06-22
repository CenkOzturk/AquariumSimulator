package com.kroq.myaquariumsimulator.game

import android.content.Context
import com.kroq.myaquariumsimulator.model.GameState
import com.kroq.myaquariumsimulator.model.saveGameState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object SaveManager {
    private lateinit var appContext: Context

    fun init(context: Context) {
        appContext = context.applicationContext
    }

    fun save(state: GameState) {
        CoroutineScope(Dispatchers.IO).launch {
            saveGameState(appContext, state)
        }
    }
}