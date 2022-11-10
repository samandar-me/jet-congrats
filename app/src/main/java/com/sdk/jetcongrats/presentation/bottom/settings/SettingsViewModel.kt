package com.sdk.jetcongrats.presentation.bottom.settings

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdk.jetcongrats.domain.use_case.UseCases
import com.sdk.jetcongrats.ui.theme.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {
    private val _color: MutableState<Color> = mutableStateOf(PurpleGrey80)
    val color: State<Color> get() = _color
    private val _backColor: MutableState<Boolean> = mutableStateOf(false)
    val backColor: State<Boolean> get() = _backColor

    init {
        getColor()
        getBackColor()
    }

    fun saveColor(color: Int) {
        viewModelScope.launch {
            useCases.saveColorUseCase.invoke(color)
        }
    }

    private fun getColor() {
        viewModelScope.launch {
            useCases.getColorUseCase.invoke().collect {
                _color.value = when (it) {
                    0 -> BlueViolet3
                    1 -> OrangeYellow3
                    2 -> Blue3
                    3 -> Beige3
                    4 -> LightGreen3
                    5 -> Grey95
                    else -> BlueViolet3
                }
            }
        }
    }

    private fun getBackColor() {
        viewModelScope.launch {
            useCases.getBackColorUseCase.invoke().collect {
                _backColor.value = it
            }
        }
    }

    fun saveBackColor(isDark: Boolean) {
        viewModelScope.launch {
            useCases.saveBackColorUseCase.invoke(isDark)
        }
    }
}