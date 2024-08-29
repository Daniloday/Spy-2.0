package com.missclick.spy.feature.game_options

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.missclick.spy.core.ui.kit.Image
import com.missclick.spy.core.ui.kit.buttons.SecondaryButton
import com.missclick.spy.core.ui.R
import com.missclick.spy.core.ui.kit.AppDivider
import com.missclick.spy.core.ui.kit.FrameText
import com.missclick.spy.core.ui.kit.Triangle
import com.missclick.spy.core.ui.kit.buttons.PrimaryButton
import com.missclick.spy.core.ui.theme.AppTheme

@Composable
internal fun GameOptionsRoute(
    modifier: Modifier = Modifier,
    onSettingsClick: () -> Unit,
    onGuideClick: () -> Unit,
    onStartClick: () -> Unit,
    onSelectSetClick: () -> Unit,
    vm: GameOptionsViewModel = viewModel(),
) {

    val viewState by vm.viewState.collectAsState()

    GameOptionsScreen(
        modifier = modifier,
        onStart = onStartClick,
        onGuideClick = onGuideClick,
        onSettingsClick = onSettingsClick,
        vm = vm,
        viewState = viewState,
        onSelectSetClick = onSelectSetClick,
    )

}

@Composable
private fun GameOptionsScreen(
    modifier: Modifier = Modifier,
    onStart: () -> Unit,
    onSettingsClick: () -> Unit,
    onGuideClick: () -> Unit,
    onSelectSetClick: () -> Unit,
    viewState: GameOptionsViewState,
    vm: GameOptionsViewModel,
) {
    Column(
        modifier = modifier
            .padding(bottom = 16.dp)
            .fillMaxSize()
    ) {
        TopBar(
            onGuideClick = onGuideClick,
            onSettingsClick = onSettingsClick,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Options(
                modifier = Modifier,
                vm = vm,
                viewState = viewState
            )
        }
        PrimaryButton(
            onClick = onStart,
            text = stringResource(R.string.start)
        )
    }
}

@Composable
private fun Options(
    modifier: Modifier = Modifier,
    viewState: GameOptionsViewState,
    vm: GameOptionsViewModel,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Option(
            name = stringResource(id = R.string.players),
            value = viewState.players.toString(),
            isDownEnabled = viewState.isPlayersDownEnabled,
            isUpEnabled = viewState.isPlayersUpEnabled,
            onUpClick = vm::onUpPlayers,
            onDownClick = vm::onDownPlayers
        )
        Option(
            name = stringResource(id = R.string.spies),
            value = viewState.spies.toString(),
            isDownEnabled = viewState.isSpiesDownEnabled,
            isUpEnabled = viewState.isSpiesUpEnabled,
            onUpClick = vm::onUpSpies,
            onDownClick = vm::onDownSpies
        )
        Option(
            name = stringResource(id = R.string.timer),
            value = viewState.timerMinute.toString(),
            isDownEnabled = viewState.isTimerDownEnabled,
            isUpEnabled = viewState.isTimerUpEnabled,
            onUpClick = vm::onUpTimer,
            onDownClick = vm::onDownTimer
        )
    }
}

@Composable
private fun Option(
    modifier: Modifier = Modifier,
    name: String,
    value: String,
    isDownEnabled: Boolean,
    isUpEnabled: Boolean,
    onUpClick: () -> Unit,
    onDownClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = name,
            color = AppTheme.colors.primary,
            style = AppTheme.types.h28,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Triangle(
                modifier = Modifier
                    .size(48.dp)
                    .rotate(180f),
                isEnabled = isDownEnabled,
                onClick = onDownClick
            )
            FrameText(text = value)
            Triangle(
                modifier = Modifier.size(48.dp),
                isEnabled = isUpEnabled,
                onClick = onUpClick
            )
        }
    }
}

@Composable
private fun TopBar(
    modifier: Modifier = Modifier,
    onGuideClick: () -> Unit,
    onSettingsClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .height(AppTheme.dimens.topBarHeight)
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.align(Alignment.Center)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = onGuideClick) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(id = R.drawable.ic_book),
                    contentDescription = null,
                    tint = AppTheme.colors.primary
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(onClick = onSettingsClick) {
                Icon(
                    modifier = Modifier.size(40.dp),
                    painter = painterResource(id = R.drawable.ic_settings),
                    contentDescription = null,
                    tint = AppTheme.colors.primary
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
        AppDivider(modifier = Modifier.align(Alignment.BottomCenter))
    }
}