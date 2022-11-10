package com.sdk.jetcongrats.presentation.bottom.detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdk.jetcongrats.data.BoxFeature
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
    private val _favorite: MutableState<Boolean> = mutableStateOf(false)
    val favorite: State<Boolean> = _favorite

    fun whichItem(id: String) {
        viewModelScope.launch {
            val text = when (id) {
                "0" -> "weddings"
                "1" -> "proverbs"
                "2" -> "boy"
                "3" -> "girl"
                "4" -> "riddle"
                "5" -> "quickly"
                else -> ""
            }
            delay(200L)
            useCases.getDataUseCase.invoke(text).collect {
                when (it) {
                    is Response.Loading -> _state.value = _state.value.copy(loading = true)
                    is Response.Error -> _state.value =
                        _state.value.copy(loading = false, error = it.error)
                    is Response.Success -> _state.value =
                        _state.value.copy(success = it.data, loading = false)
                }
            }
        }
    }

    fun saveToFavorite(favoriteData: FavoriteData) {
        viewModelScope.launch {
            useCases.uploadFavoriteUseCase.invoke(favoriteData).collect {
                when (it) {
                    is Response.Error -> _favorite.value = false
                    is Response.Success -> _favorite.value = true
                    is Response.Loading -> Unit
                }
            }
        }
    }
}