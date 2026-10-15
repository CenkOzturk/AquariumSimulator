package com.kukurodev.mykukuroaquarium.managers

import android.content.Context
import com.kukurodev.mykukuroaquarium.model.GameState
import com.kukurodev.mykukuroaquarium.model.saveGameState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object SaveManager {
    private lateinit var appContext: Context

    fun init(context: Context) {
        appContext = context.applicationContext
        CloudSyncManager.init(context)
    }

    fun save(state: GameState) {
        CoroutineScope(Dispatchers.IO).launch {
            val updatedState = state.copy(lastLoginTime = System.currentTimeMillis())
            saveGameState(appContext, updatedState)
            CloudSyncManager.saveKeystoreBackup(updatedState)
            CloudSyncManager.pushToCloud(updatedState)
        }
    }
}
