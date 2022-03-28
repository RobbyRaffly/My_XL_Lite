package com.myxllite.app.kit.base.viewmodel

import androidx.lifecycle.LiveData

/**
 * Created by Bacharudin Farisi on 28/03/22
 */
interface BaseViewModelContract {
    fun getIsLoading(): LiveData<Boolean>

    fun setIsLoading(isLoading: Boolean)
}