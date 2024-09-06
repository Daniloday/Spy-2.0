package com.missclick.spy.feature.collections

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.missclick.spy.core.ui.R
import com.missclick.spy.core.ui.theme.AppTheme
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun CollectionsRoute(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onCollectionClick: (String) -> Unit,
    onAddNewCollectionClick: () -> Unit,
    vm: CollectionsViewModel = koinViewModel(),
) {

    val viewState by vm.viewState.collectAsState()

    CollectionsScreen(
        modifier = modifier,
        viewState = viewState,
        onBackClick = onBackClick,
        onCollectionClick = onCollectionClick,
        onAddNewCollectionClick = onAddNewCollectionClick
    )
}

@Composable
private fun CollectionsScreen(
    modifier: Modifier = Modifier,
    viewState: CollectionsViewState,
    onBackClick: () -> Unit,
    onCollectionClick: (String) -> Unit,
    onAddNewCollectionClick: () -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        TopBar(onBackClick = onBackClick)
        when(viewState){
            is CollectionsViewState.Loading -> Unit
            is CollectionsViewState.Success -> CollectionsScreenSuccess(
                modifier = modifier,
                onAddNewCollectionClick = onAddNewCollectionClick,
                onCollectionClick = onCollectionClick,
                viewState = viewState
            )
        }
    }
}

@Composable
private fun CollectionsScreenSuccess(
    modifier: Modifier = Modifier,
    viewState: CollectionsViewState.Success,
    onCollectionClick: (String) -> Unit,
    onAddNewCollectionClick: () -> Unit
) {

}


@Composable
private fun TopBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(AppTheme.dimens.topBarHeight),
        contentAlignment = Alignment.CenterStart
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                modifier = Modifier.size(32.dp),
                painter = painterResource(id = R.drawable.ic_back),
                tint = AppTheme.colors.primary,
                contentDescription = null
            )
        }
    }
}