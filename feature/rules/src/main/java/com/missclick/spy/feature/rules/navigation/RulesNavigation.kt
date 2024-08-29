package com.missclick.spy.feature.rules.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.missclick.spy.feature.rules.RulesRoute

const val RULES_ROUTE = "rulesRoute"

fun NavGraphBuilder.rulesScreen(
    onSkipClick: () -> Unit
) {

    composable(
        route = RULES_ROUTE,
    ) {
       RulesRoute(onSkipClick = onSkipClick)
    }

}