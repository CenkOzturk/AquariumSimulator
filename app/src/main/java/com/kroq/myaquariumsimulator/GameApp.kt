package com.kroq.myaquariumsimulator

import android.app.Application
import com.kroq.myaquariumsimulator.managers.AudioManager
import com.kroq.myaquariumsimulator.managers.LanguageManager
import com.kroq.myaquariumsimulator.managers.SaveManager
import com.kroq.myaquariumsimulator.utils.Utils

class GameApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
        AudioManager.initialize()
        LanguageManager.init(this)
        SaveManager.init(this)
    }
}