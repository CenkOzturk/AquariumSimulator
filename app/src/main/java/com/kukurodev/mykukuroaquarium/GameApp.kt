package com.kukurodev.mykukuroaquarium

import android.app.Application
import com.kukurodev.mykukuroaquarium.managers.AudioManager
import com.kukurodev.mykukuroaquarium.managers.LanguageManager
import com.kukurodev.mykukuroaquarium.managers.SaveManager
import com.kukurodev.mykukuroaquarium.utils.Utils

class GameApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
        AudioManager.initialize()
        LanguageManager.init(this)
        SaveManager.init(this)
    }
}