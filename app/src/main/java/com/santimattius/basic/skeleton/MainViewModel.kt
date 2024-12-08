package com.santimattius.basic.skeleton

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santimattius.basic.skeleton.core.SayHelloServices
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import kotlin.random.Random

data class MainUiState(
    val isLoading: Boolean = false,
    val message: String = "",
)

@KoinViewModel
class MainViewModel(
    private val sayHelloServices: SayHelloServices
) : ViewModel() {
    private val _state = MutableStateFlow(MainUiState())
    val state: StateFlow<MainUiState> = _state.onStart {
        sayHello()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = MainUiState()
    )

    private fun sayHello() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val message = sayHelloServices.sayHello()
            _state.update { it.copy(isLoading = false, message = message) }
        }
    }
}