package com.missclick.spy.feature.rules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.missclick.spy.core.ui.kit.Image
import com.missclick.spy.core.ui.kit.Tab
import com.missclick.spy.core.ui.kit.buttons.PrimaryButton
import com.missclick.spy.core.ui.R
import com.missclick.spy.core.ui.theme.AppTheme


@Composable
internal fun RulesRoute(
    modifier: Modifier = Modifier,
    onSkipClick: () -> Unit,
    vm: RulesViewModel = viewModel(),
) {

    val viewState by vm.viewState.collectAsState()

    RulesScreen(
        modifier = modifier,
        onSkipClick = onSkipClick,
        viewState = viewState,
    )

}

@Composable
private fun RulesScreen(
    modifier: Modifier = Modifier,
    onSkipClick: () -> Unit,
    viewState: RulesViewState,
) {

    val horizontalPagerState = rememberPagerState {
        viewState.rules.size
    }

    Column(modifier = modifier) {
        ViewPager(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            state = horizontalPagerState,
            rules = viewState.rules
        )
        Tabs(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp),
            count = horizontalPagerState.pageCount,
            selectedIndex = horizontalPagerState.currentPage
        )
        PrimaryButton(
            modifier = Modifier.padding(bottom = 16.dp),
            onClick = onSkipClick,
            text = if (horizontalPagerState.canScrollForward)
                stringResource(id = R.string.skip)
            else
                stringResource(id = R.string.next)
        )
    }

}

@Composable
private fun ViewPager(
    modifier: Modifier = Modifier,
    state: PagerState,
    rules: List<Rule>,
) {
    HorizontalPager(
        modifier = modifier,
        state = state
    ) { currentPage ->
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 8.dp)
                ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                Image(painter = painterResource(id = rules[currentPage].icon))
                Text(
                    text = stringResource(id = rules[currentPage].title),
                    style = AppTheme.types.h42,
                    color = AppTheme.colors.primary,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = stringResource(id = rules[currentPage].text),
                    style = AppTheme.types.h22,
                    color = AppTheme.colors.primary,
                    textAlign = TextAlign.Center
                )
            }
        }

    }
}

@Composable
private fun Tabs(
    modifier: Modifier = Modifier,
    count: Int,
    selectedIndex: Int,
) {
    Box(modifier = modifier) {
        Row(
            modifier = Modifier.align(Alignment.Center),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(count) { currentIndex ->
                Tab(isSelected = currentIndex == selectedIndex)
            }
        }
    }
}

