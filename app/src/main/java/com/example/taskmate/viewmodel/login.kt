package com.example.taskmate.viewmodel

import androidx.lifecycle.ViewModel
import com.example.taskmate.model.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class LoginState(
    listener: () -> MutableStateFlow<LoginState>,
) : ViewState<LoginState>(listener) {
    var count: Int = 0
}


class LoginViewModel() : ViewModel() {

    private val _state: MutableStateFlow<LoginState> =
        MutableStateFlow(LoginState { getListener() })
    private val state by lazy {
        _state.value
    }
    val stateFlow = _state.asStateFlow()
    private fun getListener(): MutableStateFlow<LoginState> {
        return this._state
    }

    fun increment() {
        state.count += 1
        state.emit()
    }

    fun decrement() {
        state.count -= 1
        state.emit()
    }

}