package com.missclick.spy.feature.game_options

import androidx.lifecycle.ViewModel
import com.missclick.spy.core.common.Constant.PLAYERS_MAX
import com.missclick.spy.core.common.Constant.PLAYERS_MIN
import com.missclick.spy.core.common.Constant.SPIES_MAX
import com.missclick.spy.core.common.Constant.SPIES_MIN
import com.missclick.spy.core.common.Constant.TIMER_MAX
import com.missclick.spy.core.common.Constant.TIMER_MIN
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class GameOptionsViewModel: ViewModel() {

    private val _viewState = MutableStateFlow(GameOptionsViewState())
    val viewState: StateFlow<GameOptionsViewState> = _viewState.asStateFlow()


    fun onUpPlayers() {
        if (viewState.value.players < PLAYERS_MAX) {
            _viewState.update {
                it.copy(
                    players = it.players + 1,
                    isPlayersUpEnabled = it.players + 1 != PLAYERS_MAX,
                    isPlayersDownEnabled = true,
                    isSpiesUpEnabled = true
                )
            }
        }
    }

    fun onDownPlayers() {
        if (viewState.value.players > PLAYERS_MIN) {
            _viewState.update {
                it.copy(
                    players = it.players - 1,
                    isPlayersDownEnabled = it.players - 1 != PLAYERS_MIN,
                    isPlayersUpEnabled = true,
                    spies = if (it.players - 1 == it.spies) it.spies - 1 else it.spies,
                    isSpiesUpEnabled = it.spies != it.players - 2
                )
            }
        }
    }

    fun onUpTimer() {
        if (viewState.value.timerMinute < TIMER_MAX) {
            _viewState.update {
                it.copy(
                    timerMinute = it.timerMinute + 1,
                    isTimerUpEnabled = it.timerMinute + 1 != TIMER_MAX,
                    isTimerDownEnabled = true
                )
            }
        }
    }

    fun onDownTimer() {
        if (viewState.value.timerMinute > TIMER_MIN) {
            _viewState.update {
                it.copy(
                    timerMinute = it.timerMinute - 1,
                    isTimerDownEnabled = it.timerMinute - 1 != TIMER_MIN,
                    isTimerUpEnabled = true
                )
            }
        }
    }

    fun onUpSpies() {
        if (viewState.value.spies < viewState.value.players - 1) {
            _viewState.update {
                it.copy(
                    spies = it.spies + 1,
                    isSpiesUpEnabled = it.spies + 1 != it.players - 1,
                    isSpiesDownEnabled = true
                )
            }
        }
    }

    fun onDownSpies() {
        if (viewState.value.spies > SPIES_MIN) {
            _viewState.update {
                it.copy(
                    spies = it.spies - 1,
                    isSpiesDownEnabled = it.spies - 1 != SPIES_MIN,
                    isSpiesUpEnabled = true
                )
            }
        }
    }



}

data class GameOptionsViewState(
    val players: Int = 3,
    val isPlayersUpEnabled: Boolean = true,
    val isPlayersDownEnabled: Boolean = false,

    val spies: Int = 1,
    val isSpiesUpEnabled: Boolean = true,
    val isSpiesDownEnabled: Boolean = false,

    val timerMinute: Int = 2,
    val isTimerUpEnabled: Boolean = true,
    val isTimerDownEnabled: Boolean = true,

    val set: String = "base",
)