package com.sdk.jetcongrats.presentation.bottom.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdk.jetcongrats.data.BoxFeature
import com.sdk.jetcongrats.domain.use_case.UseCases
import com.sdk.jetcongrats.util.ColorObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {
    private val _list: MutableState<List<BoxFeature>> = mutableStateOf(emptyList())
    val list: State<List<BoxFeature>> = _list

    init {
        whichItem()
    }

    private fun whichItem() {
        viewModelScope.launch {
            useCases.getColorUseCase.invoke().collect { index ->
                _list.value = ColorObject.items().map {
                    it.copy(
                        id = it.id,
                        title = it.title,
                        boxColor = ColorObject.boxColorList()[index]
                    )
                }
            }
        }
    }
}