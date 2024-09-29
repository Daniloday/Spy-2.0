package com.missclick.spy.feature.words.navigation

import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.missclick.spy.core.model.Collection
import com.missclick.spy.feature.words.WordsRoute
import kotlinx.serialization.Serializable

@Serializable
data class WordsNavRoute(
    val selectedCollectionName: String
)

fun NavGraphBuilder.wordsScreen(
    onBackClick: () -> Unit,
    onSelectCollection: () -> Unit,
) {

    composable<WordsNavRoute> { navBackStackEntry ->
        val wordsRoute = remember {
            navBackStackEntry.toRoute<WordsNavRoute>()
        }
        WordsRoute(
            onBackClick = onBackClick,
            onSelectCollection = onSelectCollection,
            selectedCollectionName = wordsRoute.selectedCollectionName
        )
    }

}

fun NavController.navigateToWords(selectedCollectionName: String) {
    navigate(WordsNavRoute(selectedCollectionName))
}