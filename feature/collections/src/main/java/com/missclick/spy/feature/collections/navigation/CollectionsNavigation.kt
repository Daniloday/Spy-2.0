package com.missclick.spy.feature.collections.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.missclick.spy.feature.collections.CollectionsRoute


const val COLLECTIONS_ROUTE = "Collections"

fun NavGraphBuilder.collectionsScreen(
    onBackClick: () -> Unit
) {

    composable(
        route = COLLECTIONS_ROUTE,
    ) {
        CollectionsRoute(
            onBackClick = onBackClick,
            onAddNewCollectionClick = {},
            onCollectionClick = {}
        )
    }

}

fun NavController.navigateToCollections() {
    navigate(COLLECTIONS_ROUTE)
}