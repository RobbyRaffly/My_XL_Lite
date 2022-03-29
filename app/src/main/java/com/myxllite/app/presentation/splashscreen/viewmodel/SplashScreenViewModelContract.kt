package com.myxllite.app.presentation.splashscreen.viewmodel

import com.myxllite.app.kit.base.viewmodel.BaseViewModelContract

/**
 * Created by Bacharudin Farisi on 29/03/22
 */
interface SplashScreenViewModelContract : BaseViewModelContract {
    fun setInAppUpdateShow(isShow: Boolean)
}