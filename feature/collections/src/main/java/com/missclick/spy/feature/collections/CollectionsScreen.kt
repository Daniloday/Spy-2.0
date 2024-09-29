package com.missclick.spy.feature.collections

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.contentcapture.ContentCaptureManager.Companion.isEnabled
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.missclick.spy.core.common.Constant.COLLECTION_NAME_MAX_LENGTH
import com.missclick.spy.core.ui.R
import com.missclick.spy.core.ui.kit.AppDivider
import com.missclick.spy.core.ui.kit.AppTextField
import com.missclick.spy.core.ui.kit.Triangle
import com.missclick.spy.core.ui.kit.buttons.SecondaryButton
import com.missclick.spy.core.ui.theme.AppTheme
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun CollectionsRoute(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onCollectionClick: (String) -> Unit,
    vm: CollectionsViewModel = koinViewModel(),
) {

    val viewState by vm.viewState.collectAsState()

    CollectionsScreen(
        modifier = modifier,
        viewState = viewState,
        onBackClick = onBackClick,
        onCollectionClick = onCollectionClick,
        onAddNewCollectionClick = vm::addNewCollection,
        onNewCollectionSaveClick = vm::saveNewCollection,
        onNewCollectionNameChange = vm::onNewCollectionNameChange
    )
}

@Composable
private fun CollectionsScreen(
    modifier: Modifier = Modifier,
    viewState: CollectionsViewState,
    onBackClick: () -> Unit,
    onCollectionClick: (String) -> Unit,
    onAddNewCollectionClick: () -> Unit,
    onNewCollectionNameChange: (String) -> Unit,
    onNewCollectionSaveClick: () -> Unit,
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
                onNewCollectionSaveClick = {
                    onNewCollectionSaveClick()
                    if (viewState.newCollection.isNotBlank()) {
                        onCollectionClick(viewState.newCollection)
                    }
                },
                onNewCollectionNameChange = onNewCollectionNameChange,
                viewState = viewState,
            )
        }
    }
}

@Composable
private fun CollectionsScreenSuccess(
    modifier: Modifier = Modifier,
    viewState: CollectionsViewState.Success,
    onCollectionClick: (String) -> Unit,
    onAddNewCollectionClick: () -> Unit,
    onNewCollectionNameChange: (String) -> Unit,
    onNewCollectionSaveClick: () -> Unit,
) {

    val lazyListState = rememberLazyListState()

    LaunchedEffect(key1 = viewState.isEnteringNewCollection) {
        if (viewState.collectionViews.isNotEmpty()) {
            lazyListState.animateScrollToItem(viewState.collectionViews.lastIndex)
        }
    }

    SecondaryButton(
        modifier = Modifier.padding(vertical = 16.dp),
        onClick = onAddNewCollectionClick,
        text = stringResource(id = R.string.add_set)
    )
    LazyColumn(
        modifier = modifier,
        state = lazyListState
    ) {
        items(viewState.collectionViews) { collection ->
            CollectionCard(
                modifier = Modifier.animateItem(),
                onCollectionClick = {
                   onCollectionClick(collection.name)
                },
                collectionName = collection.name,
                isSelected = collection.isSelected
            )
        }
        item {
            AppDivider(
                color = AppTheme.colors.onPrimary
            )
        }
        if (viewState.isEnteringNewCollection) {
            item {
                AddNewCollectionCard(
                    name = viewState.newCollection,
                    onValueChange = onNewCollectionNameChange,
                    onSaveClick = onNewCollectionSaveClick
                )
            }
        }
    }
}

@Composable
private fun AddNewCollectionCard(
    modifier: Modifier = Modifier,
    name: String,
    onValueChange: (String) -> Unit,
    onSaveClick: () -> Unit,
) {

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp),
        shape = AppTheme.shapes.rectangle,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterStart),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppTextField(
                    modifier = Modifier.focusRequester(focusRequester),
                    value = name,
                    maxLength = COLLECTION_NAME_MAX_LENGTH,
                    onDone = onSaveClick,
                    onValueChange = onValueChange,
                    placeholder = stringResource(id = R.string.enter_dictionary)
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = onSaveClick) {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        painter = painterResource(id = R.drawable.ic_ok),
                        tint = AppTheme.colors.primary,
                        contentDescription = null
                    )
                }
            }

            AppDivider(
                modifier = Modifier.align(Alignment.TopCenter),
                color = AppTheme.colors.onPrimary
            )
        }
    }
}

@Composable
private fun CollectionCard(
    modifier: Modifier = Modifier,
    collectionName: String,
    isSelected: Boolean,
    onCollectionClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp),
        shape = AppTheme.shapes.rectangle,
        onClick = onCollectionClick,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterStart),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = collectionName,
                    style = AppTheme.types.h28,
                    color = if (isSelected) AppTheme.colors.secondary else AppTheme.colors.primary
                )
                Spacer(modifier = Modifier.weight(1f))
                Triangle(
                    modifier = Modifier.size(48.dp),
                    isEnabled = false,
                    isBorderActive = true,
                    disabledBorderColor = if(isSelected) AppTheme.colors.secondary else AppTheme.colors.primary,
                    disabledColor = if(isSelected) AppTheme.colors.secondary else Color.Transparent
                )
            }

            AppDivider(
                modifier = Modifier.align(Alignment.TopCenter),
                color = AppTheme.colors.onPrimary
            )
        }
    }
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
        AppDivider(modifier = Modifier.align(Alignment.BottomCenter))
    }
}