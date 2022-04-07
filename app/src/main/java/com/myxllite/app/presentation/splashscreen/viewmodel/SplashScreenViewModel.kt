package com.myxllite.app.presentation.splashscreen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.myxllite.app.core.scheduler.SchedulerProvider
import com.myxllite.app.domain.interactor.splashscreen.SplashScreenInteractor
import com.myxllite.app.kit.base.viewmodel.BaseViewModel
import com.myxllite.app.kit.data.SingleLiveEvent
import com.myxllite.app.kit.data.StatefulLiveData
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Bacharudin Farisi on 29/03/22
 */
class SplashScreenViewModel @Inject constructor(
    private val splashScreenInteractor: SplashScreenInteractor,
    private val scheduler: SchedulerProvider
) : BaseViewModel(), SplashScreenViewModelContract {

    private val deleteStoreConfigLiveData: SingleLiveEvent<Int> = SingleLiveEvent()
    private val deleteStoreConfig: LiveData<StatefulLiveData<Int>> = SingleLiveEvent()

    override fun setInAppUpdateShow(isShow: Boolean) {
        splashScreenInteractor.setInAppUpdateShow(isShow)
    }

    override fun clearCacheAndResetAvatarSignature() {
        splashScreenInteractor.clearNetworkAndFailSafeCache()
        splashScreenInteractor.resetAvatarSignature(System.currentTimeMillis().toString())
    }

    override fun deleteAllStoreConfig(): Job = launch(scheduler.ui()) {
        val result = withContext(scheduler.io()) {
            splashScreenInteractor.deleteAllStoreConfig()
        }
        deleteStoreConfigLiveData.value = result
    }
}