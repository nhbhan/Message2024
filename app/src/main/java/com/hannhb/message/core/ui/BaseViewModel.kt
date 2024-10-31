package com.hannhb.message.core.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import com.hannhb.message.core.helpers.Event

enum class LoadingState {
    LOADING,
    IDLE,
}

abstract class BaseViewModel : ViewModel() {
    private val context by inject<Context>(Context::class.java)

    private val _loadingState = MutableLiveData(LoadingState.IDLE)
    val loadingState: LiveData<LoadingState> = _loadingState

    private val _errorMessage = MutableLiveData<Event<String>>()
    val errorMessage: LiveData<Event<String>> = _errorMessage

    protected fun dispatchErrorMessageEvent(message: String) {
        _errorMessage.value = Event(message)
    }

    protected fun dispatchErrorMessageEvent(resId: Int) {
        dispatchErrorMessageEvent(context.getString(resId))
    }

    protected fun call(
        handler: suspend () -> Unit,
        showLoading: Boolean = true,
        onSuccess: (() -> Unit)? = null,
        onFailure: (Throwable) -> Unit = { _errorMessage.value = Event(it.message ?: it.toString()) }
    ) {
        viewModelScope.launch {
            var success = true
            try {
                if (showLoading) {
                    _loadingState.value = LoadingState.LOADING
                }
                handler()
            } catch (throwable: Throwable) {
                success = false
                onFailure(throwable)
            } finally {
                if (showLoading) {
                    _loadingState.value = LoadingState.IDLE
                }
                if (success) {
                    onSuccess?.invoke()
                }
            }
        }
    }
}
