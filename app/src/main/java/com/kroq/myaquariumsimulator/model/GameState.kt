package com.kroq.myaquariumsimulator.model

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.kroq.myaquariumsimulator.data.PrefKeys
import com.kroq.myaquariumsimulator.data.dataStore
import com.kroq.myaquariumsimulator.model.aquarium.AquariumType
import kotlinx.coroutines.flow.first

data class GameState(
    val aquariumType: String = AquariumType.SMALL.name,
    val ownedFishIds: Set<Int> = setOf(100),
    val ownedItemIds: Set<Int> = setOf(),
    val coins: Int = 0
)

suspend fun loadGameState(context: Context): GameState {

    val prefs = context.dataStore.data.first()

    return GameState(
        aquariumType = prefs[PrefKeys.AQUARIUM] ?: AquariumType.SMALL.name,
        ownedFishIds = prefs[PrefKeys.FISH]?.map { it.toInt() }?.toSet() ?: setOf(100),
        ownedItemIds = prefs[PrefKeys.ITEMS]?.map { it.toInt() }?.toSet() ?: setOf(),
        coins = prefs[PrefKeys.COINS] ?: 0
    )
}

suspend fun saveGameState(
    context: Context,
    state: GameState
) {
    context.dataStore.edit { prefs ->

        prefs[PrefKeys.AQUARIUM] = state.aquariumType

        prefs[PrefKeys.FISH] = state.ownedFishIds.map { it.toString() }.toSet()

        prefs[PrefKeys.ITEMS] = state.ownedItemIds.map { it.toString() }.toSet()

        prefs[PrefKeys.COINS] = state.coins
    }
}