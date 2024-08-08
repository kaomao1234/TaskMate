package com.example.taskmate.model

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

abstract class ViewState<T>(protected val listener: () -> MutableStateFlow<T>) : Cloneable {
    fun emit() {
        listener().update {
            this.copy()
        }
    }

    private fun copy(): T {
        return this.clone() as T
    }
}
