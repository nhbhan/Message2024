package com.hannhb.message.core.helpers

import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

open class Event<out T>(private val content: T) {
    var hasBeenHandled = AtomicBoolean(false)
        private set

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled.get()) {
            null
        } else {
            hasBeenHandled.set(true)
            content
        }
    }

    fun peekContent(): T = content
}

class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(value: Event<T>) {
        value.getContentIfNotHandled()?.let {
            onEventUnhandledContent(it)
        }
    }
}
