package com.dmikhov.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData

class ConsumableSingleEventLiveData<T> : MutableLiveData<T>() {
    private var consumed = false

    fun observeSingleEvent(owner: LifecycleOwner, observer: (T?) -> Unit) {
        this.observe(owner, {
            if (!consumed) {
                observer(it)
                consumed = true
                value = null
            }
        })
    }

    override fun postValue(value: T) {
        consumed = false
        super.postValue(value)
    }
}