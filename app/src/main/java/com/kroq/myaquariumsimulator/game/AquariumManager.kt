package com.kroq.myaquariumsimulator.game

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.preferences.core.edit
import com.kroq.myaquariumsimulator.data.PrefKeys
import com.kroq.myaquariumsimulator.data.dataStore
import com.kroq.myaquariumsimulator.model.aquarium.AquariumModel
import com.kroq.myaquariumsimulator.model.aquarium.AquariumType
import com.kroq.myaquariumsimulator.model.aquarium.createAquarium
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object AquariumManager {

    val aquariumType = AquariumType.valueOf(GameManager.state.aquariumType)
    var currentType by mutableStateOf(aquariumType)
        private set

    var currentAquarium by mutableStateOf<AquariumModel?>(null)
        private set

    fun init(
        context: Context,
        screenWidth: Float,
        screenHeight: Float
    ) {
        CoroutineScope(Dispatchers.IO).launch {

            val prefs = context.dataStore.data.first()

            val storedType = prefs[PrefKeys.AQUARIUM] ?: "SMALL"

            val type = AquariumType.valueOf(storedType)

            withContext(Dispatchers.Main) {
                currentType = type

                currentAquarium = createAquarium(
                    type,
                    screenWidth,
                    screenHeight
                )
            }
        }
    }

    fun upgrade(
        context: Context,
        type: AquariumType,
        screenWidth: Float,
        screenHeight: Float
    ) {
        currentType = type

        currentAquarium = createAquarium(
            type,
            screenWidth,
            screenHeight
        )

        // 💾 SAVE
        CoroutineScope(Dispatchers.IO).launch {
            saveAquariumType(context, type)
        }
    }

    suspend fun saveAquariumType(
        context: Context,
        type: AquariumType
    ) {
        context.dataStore.edit { prefs ->
            prefs[PrefKeys.AQUARIUM] = type.name
        }
    }
}