package com.sdk.jetcongrats.presentation.bottom.favorite

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdk.jetcongrats.domain.model.FavoriteData
import com.sdk.jetcongrats.domain.use_case.UseCases
import com.sdk.jetcongrats.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {
    private val _state: MutableState<FavoriteState> = mutableStateOf(FavoriteState())
    val state: State<FavoriteState> get() = _state

    init {
        getAllFavoriteList()
    }

    private fun getAllFavoriteList() {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true)
            useCases.getAllFavoritesUseCase.invoke().collect {
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

    fun deleteFromFavorite(favoriteData: FavoriteData) {
        viewModelScope.launch {
            useCases.deleteFavoriteUseCase.invoke(favoriteData)
        }
    }
    fun copyText(text: String) {
        useCases.copyTextUseCase.invoke(text)
    }
}