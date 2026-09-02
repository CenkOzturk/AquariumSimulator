package com.kukurodev.mykukuroaquarium.model

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.kukurodev.mykukuroaquarium.data.PrefKeys
import com.kukurodev.mykukuroaquarium.data.dataStore
import com.kukurodev.mykukuroaquarium.model.aquarium.AquariumType
import com.kukurodev.mykukuroaquarium.model.shop.ShopTab
import com.kukurodev.mykukuroaquarium.model.task.DailyTaskModel
import com.kukurodev.mykukuroaquarium.model.tutorial.TutorialStep
import com.kukurodev.mykukuroaquarium.model.upgrade.UpgradeDatabase
import com.kukurodev.mykukuroaquarium.model.upgrade.UpgradeState
import com.kukurodev.mykukuroaquarium.model.upgrade.toUpgradeState
import com.kukurodev.mykukuroaquarium.model.upgrade.toUpgradeStateModel
import com.kukurodev.mykukuroaquarium.utils.Utils.emptyString
import com.kukurodev.mykukuroaquarium.utils.Utils.fromJson
import com.kukurodev.mykukuroaquarium.utils.Utils.toJson
import kotlinx.coroutines.flow.first

data class GameState(
    val aquariumType: String = AquariumType.SMALL.name,
    val ownedFishIds: Set<Int> = emptySet(),
    val ownedItemIds: Set<Int> = emptySet(),
    val coins: Int = 25,
    val foodCount: Int = 10,
    val cleanerCount: Int = 10,
    val dirtParticleCount: Int = 0,
    val selectedShopTab: ShopTab = ShopTab.FISH,
    val dailyTask: DailyTaskModel? = null,
    val welcomeGiftDay: Int = 0,
    val welcomeGiftClaimed: Boolean = false,
    val lastLoginTime: Long = 0L,
    val tutorialStep: String = TutorialStep.WELCOME.name,
    val tutorialCompleted: Boolean = false,
    val goldFishUnlocked: Boolean = false,
    val lastGoldFishTime: Long = 0L,
    val ownedUpgrades: UpgradeState =
        UpgradeDatabase.getAllUpgrades().map { it.toUpgradeStateModel() }.toUpgradeState()
)

suspend fun loadGameState(context: Context): GameState {
    val prefs = context.dataStore.data.first()

    return GameState(
        aquariumType = prefs[PrefKeys.AQUARIUM] ?: AquariumType.SMALL.name,
        ownedFishIds = prefs[PrefKeys.FISH]?.map { it.toInt() }?.toSet() ?: emptySet(),
        ownedItemIds = prefs[PrefKeys.ITEMS]?.map { it.toInt() }?.toSet() ?: emptySet(),
        coins = prefs[PrefKeys.COINS] ?: 25,
        foodCount = prefs[PrefKeys.FOOD_COUNT] ?: 10,
        cleanerCount = prefs[PrefKeys.CLEANER_COUNT] ?: 10,
        dirtParticleCount = prefs[PrefKeys.DIRT_PARTICLE_COUNT] ?: 0,
        dailyTask = prefs[PrefKeys.DAILY_TASK]?.fromJson<DailyTaskModel>(),
        welcomeGiftDay = prefs[PrefKeys.WELCOME_GIFT_DAY] ?: 0,
        welcomeGiftClaimed = prefs[PrefKeys.WELCOME_GIFT_CLAIMED] ?: false,
        lastLoginTime = prefs[PrefKeys.LAST_LOGIN_TIME] ?: 0L,
        tutorialStep = prefs[PrefKeys.TUTORIAL_STEP] ?: TutorialStep.WELCOME.name,
        tutorialCompleted = prefs[PrefKeys.TUTORIAL_COMPLETED] ?: false,
        goldFishUnlocked = prefs[PrefKeys.GOLD_FISH_UNLOCKED] ?: false,
        lastGoldFishTime = prefs[PrefKeys.LAST_GOLD_FISH_TIME] ?: 0L,
        ownedUpgrades = prefs[PrefKeys.UPGRADES]?.fromJson<UpgradeState>() ?: UpgradeState(emptyList()),
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
        prefs[PrefKeys.FOOD_COUNT] = state.foodCount
        prefs[PrefKeys.CLEANER_COUNT] = state.cleanerCount
        prefs[PrefKeys.DIRT_PARTICLE_COUNT] = state.dirtParticleCount
        prefs[PrefKeys.DAILY_TASK] = state.dailyTask?.toJson() ?: emptyString()
        prefs[PrefKeys.WELCOME_GIFT_DAY] = state.welcomeGiftDay
        prefs[PrefKeys.WELCOME_GIFT_CLAIMED] = state.welcomeGiftClaimed
        prefs[PrefKeys.LAST_LOGIN_TIME] = state.lastLoginTime
        prefs[PrefKeys.TUTORIAL_STEP] = state.tutorialStep
        prefs[PrefKeys.TUTORIAL_COMPLETED] = state.tutorialCompleted
        prefs[PrefKeys.GOLD_FISH_UNLOCKED] = state.goldFishUnlocked
        prefs[PrefKeys.LAST_GOLD_FISH_TIME] = state.lastGoldFishTime
        prefs[PrefKeys.UPGRADES] = state.ownedUpgrades.toJson()
    }
}