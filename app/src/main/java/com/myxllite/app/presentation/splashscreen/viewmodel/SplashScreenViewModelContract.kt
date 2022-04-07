package com.myxllite.app.presentation.splashscreen.viewmodel

import com.myxllite.app.kit.base.viewmodel.BaseViewModelContract
import kotlinx.coroutines.Job

/**
 * Created by Bacharudin Farisi on 29/03/22
 */
interface SplashScreenViewModelContract : BaseViewModelContract {

    fun setInAppUpdateShow(isShow: Boolean)

    fun clearCacheAndResetAvatarSignature()

    fun deleteAllStoreConfig(): Job
}