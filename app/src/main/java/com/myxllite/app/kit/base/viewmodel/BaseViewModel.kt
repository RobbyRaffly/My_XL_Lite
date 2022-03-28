package com.myxllite.app.kit.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

/**
 * Created by Bacharudin Farisi on 28/03/22
 */
abstract class BaseViewModel : ViewModel(), CoroutineScope, BaseViewModelContract {
    private val isLoading = MutableLiveData<Boolean>()

    override val coroutineContext: CoroutineContext
        get() = viewModelScope.coroutineContext

    override fun getIsLoading() = isLoading

    override fun setIsLoading(isLoading: Boolean) {
        this.isLoading.value = isLoading
    }
}