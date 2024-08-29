package com.missclick.spy.feature.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class GameViewModel : ViewModel() {

    private val _viewState = MutableStateFlow<GameViewState>(GameViewState.Loading)
    val viewState = _viewState.asStateFlow()

    init {
        //todo get the data from ..
        val locations = listOf("Home", "Cafe")
        val players = 5
        val spies = 2
        val timer = 1


        val location = locations.random(Random(System.currentTimeMillis()))

        val localCads = List(players - spies) {
            CardInfo(
                cardState = CardState.CLOSED,
                location = location,
                isSpy = false
            )
        }

        val spyCards = List(spies) {
            CardInfo(
                cardState = CardState.CLOSED,
                location = location,
                isSpy = true
            )
        }

        val cards = (localCads + spyCards).shuffled(Random(System.currentTimeMillis()))

        _viewState.update {
            GameViewState.Preparing(
                cards = cards,
                timerMin = timer
            )
        }


    }

    fun onCardClick() {
        val preparingState = viewState.value as? GameViewState.Preparing ?: return
        var done = false
        _viewState.update {
            preparingState.copy(
                cards = preparingState.cards.mapIndexed { index, cardInfo ->
                    if (done || cardInfo.cardState == CardState.SKIPPED) {
                        return@mapIndexed cardInfo
                    }
                    if (index == preparingState.cards.size - 1 && cardInfo.cardState == CardState.OPENED) runTimer()
                    done = true
                    when (cardInfo.cardState) {
                        CardState.CLOSED -> cardInfo.copy(cardState = CardState.OPENED)
                        CardState.OPENED -> cardInfo.copy(cardState = CardState.SKIPPED)
                        else -> cardInfo
                    }
                }
            )
        }
    }

    @OptIn(ObsoleteCoroutinesApi::class)
    private fun runTimer() {
        viewModelScope.launch {
            delay(500)
            val preparingState = viewState.value as? GameViewState.Preparing ?: return@launch
            _viewState.update {
                GameViewState.Timer(
                    timeSecLeft = preparingState.timerMin * 60,
                    spyNumbers = preparingState.cards.mapIndexedNotNull { index, cardInfo ->
                        if (cardInfo.isSpy) return@mapIndexedNotNull index + 1
                        null
                    }
                )
            }
            val ticker = ticker(1000, 0L, Job())


            ticker.consumeEach {
                val timerState = viewState.value as? GameViewState.Timer
                if (timerState == null || timerState.timeSecLeft == 0) {
                    ticker.cancel()
                    return@consumeEach
                }
                _viewState.update {
                    timerState.copy(
                        timeSecLeft = timerState.timeSecLeft - 1
                    )
                }
            }
        }
    }

    fun showSpies() {
        val timerState = viewState.value as? GameViewState.Timer ?: return
        _viewState.update {
            GameViewState.End(spyNumbers = timerState.spyNumbers)
        }
    }

}

sealed class GameViewState {

    data object Loading : GameViewState()

    data class Preparing(
        val cards: List<CardInfo>,
        val timerMin: Int,
    ) : GameViewState()

    data class Timer(
        val timeSecLeft: Int,
        val spyNumbers: List<Int>,
    ) : GameViewState()

    data class End(
        val spyNumbers: List<Int>,
    ) : GameViewState()

}

data class CardInfo(
    val cardState: CardState,
    val location: String,
    val isSpy: Boolean,
)

enum class CardState {
    CLOSED, OPENED, SKIPPED
}

fun Int.toMinSec(): String {
    val duration = this.seconds
    val minutesPart = duration.inWholeMinutes
    val secondsPart = (duration - minutesPart.minutes).inWholeSeconds
    return "%01d:%02d".format(minutesPart, secondsPart)
}

