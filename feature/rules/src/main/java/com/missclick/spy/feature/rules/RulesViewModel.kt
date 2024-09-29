package com.missclick.spy.feature.rules

import android.content.res.Resources
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.lang.Thread.State
import com.missclick.spy.core.ui.R
import kotlinx.coroutines.flow.update

class RulesViewModel : ViewModel() {

    private val _viewState = MutableStateFlow(RulesViewState())
    val viewState: StateFlow<RulesViewState> = _viewState.asStateFlow()

    init {
        _viewState.update {
            it.copy(
                rules = getRules()
            )
        }
    }

    private fun getRules() : List<Rule> = listOf(
        Rule(
            icon = R.drawable.ic_hat,
            title = R.string.first_page_title,
            text = R.string.first_page_text
        ),
        Rule(
            icon = R.drawable.ic_hat_location,
            title = R.string.second_page_title,
            text = R.string.second_page_text
        ),
        Rule(
            icon = R.drawable.ic_location,
            title = R.string.third_page_title,
            text = R.string.third_page_text
        ),
        Rule(
            icon = R.drawable.ic_reply,
            title = R.string.fourth_page_title,
            text = R.string.fourth_page_text
        ),
        Rule(
            icon = R.drawable.ic_hand,
            title = R.string.fifth_page_title,
            text = R.string.fifth_page_text
        ),
        Rule(
            icon = R.drawable.ic_medal,
            title = R.string.sixth_page_title,
            text = R.string.sixth_page_text
        ),
        Rule(
            icon = R.drawable.ic_timer,
            title = R.string.seventh_page_title,
            text = R.string.seventh_page_text
        ),
    )

}

data class RulesViewState(
    val rules: List<Rule> = emptyList(),
)

data class Rule(
    val icon: Int,
    val title: Int,
    val text: Int,
)