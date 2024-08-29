package com.missclick.spy.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.missclick.spy.feature.game.navigation.GAME_ROUTE
import com.missclick.spy.feature.game.navigation.gameScreen
import com.missclick.spy.feature.game.navigation.navigateToGame
import com.missclick.spy.feature.game_options.navigation.gameOptionsScreen
import com.missclick.spy.feature.game_options.navigation.navigateBackToGameOptions
import com.missclick.spy.feature.game_options.navigation.navigateToGameOptions
import com.missclick.spy.feature.guide.navigation.guideNavigation
import com.missclick.spy.feature.guide.navigation.navigateToGuide
import com.missclick.spy.feature.rules.navigation.RULES_ROUTE
import com.missclick.spy.feature.rules.navigation.rulesScreen
import com.missclick.spy.feature.settings.navigation.navigateToSettings
import com.missclick.spy.feature.settings.navigation.settingsNavigation

@Composable
fun NavGraph(

) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = RULES_ROUTE) {
        rulesScreen(onSkipClick = navController::navigateToGameOptions)
        gameOptionsScreen(
            onStartClick = navController::navigateToGame,
            onGuideClick = navController::navigateToGuide,
            onSettingsClick = navController::navigateToSettings,
            onSelectSetClick = {},
        )
        gameScreen(onBackClick = navController::navigateBackToGameOptions)
        guideNavigation(onBackClick = navController::navigateBackToGameOptions)
        settingsNavigation(onBackClick = navController::navigateBackToGameOptions)
    }

}