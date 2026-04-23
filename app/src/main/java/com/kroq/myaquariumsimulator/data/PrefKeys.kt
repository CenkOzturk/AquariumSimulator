package com.kroq.myaquariumsimulator.data

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey

object PrefKeys {
    val AQUARIUM = stringPreferencesKey("aquarium")
    val FISH = stringSetPreferencesKey("fish")
    val ITEMS = stringSetPreferencesKey("items")
    val COINS = intPreferencesKey("coins")
}

