package com.sdk.jetcongrats.presentation.bottom.detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdk.jetcongrats.domain.model.FavoriteData
import com.sdk.jetcongrats.domain.use_case.UseCases
import com.sdk.jetcongrats.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {
    private val _state: MutableState<DetailState> = mutableStateOf(DetailState())
    val state: State<DetailState> get() = _state

    fun whichItem(id: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true)
            delay(300L)
            useCases.getDataUseCase.invoke(id).collect {
                when (it) {
                    is Response.Loading -> Unit
                    is Response.Error -> _state.value =
                        _state.value.copy(loading = false, error = it.error)
                    is Response.Success -> _state.value =
                        _state.value.copy(loading = false, success = it.data)
                }
            }
        }
    }

    fun saveToFavorite(favoriteData: FavoriteData) {
        viewModelScope.launch {
            useCases.saveFavoriteUseCase.invoke(favoriteData)
        }
    }

    fun copyText(text: String) {
        useCases.copyTextUseCase.invoke(text)
    }
}