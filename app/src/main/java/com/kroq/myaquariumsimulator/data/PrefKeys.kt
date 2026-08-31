package com.kroq.myaquariumsimulator.data

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey

object PrefKeys {
    val AQUARIUM = stringPreferencesKey("aquarium")
    val FISH = stringSetPreferencesKey("fish")
    val ITEMS = stringSetPreferencesKey("items")
    val COINS = intPreferencesKey("coins")
    val FOOD_COUNT = intPreferencesKey("foodCount")
    val DIRT_PARTICLE_COUNT = intPreferencesKey("dirtParticleCount")
    val CLEANER_COUNT = intPreferencesKey("cleanerCount")
    val DAILY_TASK = stringPreferencesKey("dailyTask")
    val WELCOME_GIFT_DAY = intPreferencesKey("welcomeGiftDay")
    val WELCOME_GIFT_CLAIMED = booleanPreferencesKey("welcomeGiftClaimed")
    val LAST_LOGIN_TIME = longPreferencesKey("lastLoginTime")
    val TUTORIAL_STEP = stringPreferencesKey("tutorialStep")
    val TUTORIAL_COMPLETED = booleanPreferencesKey("tutorialCompleted")
    val GOLD_FISH_UNLOCKED = booleanPreferencesKey("goldFishUnlocked")
    val LAST_GOLD_FISH_TIME = longPreferencesKey("lastGoldFishTime")
    val UPGRADES = stringPreferencesKey("upgrades")
}

