package com.kroq.myaquariumsimulator.ui.navigaion

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object MainMenu : Screen("main_menu")
    data object Game : Screen("game")
    data object Credits : Screen("credits")
    data object Settings : Screen("settings")
}