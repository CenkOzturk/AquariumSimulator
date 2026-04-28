package com.kroq.myaquariumsimulator

import android.app.Application
import com.kroq.myaquariumsimulator.utils.Utils

class GameApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
    }
}