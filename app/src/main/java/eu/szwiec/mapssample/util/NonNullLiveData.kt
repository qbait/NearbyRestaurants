package eu.szwiec.mapssample.util

import androidx.lifecycle.MutableLiveData

class NonNullLiveData<T>(private val defaultValue: T) : MutableLiveData<T>() {

    override fun getValue(): T {
        return super.getValue() ?: defaultValue
    }

}