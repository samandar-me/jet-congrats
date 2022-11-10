package com.sdk.jetcongrats.presentation.bottom.detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdk.jetcongrats.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {
    private val _state: MutableState<DetailState> = mutableStateOf(DetailState())
    val state: State<DetailState> get() = _state

    fun whichItem(id: String) {
        viewModelScope.launch {

        }
    }
}