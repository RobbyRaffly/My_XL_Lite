package com.myxllite.app.presentation.splashscreen.viewmodel

import com.myxllite.app.core.scheduler.SchedulerProvider
import com.myxllite.app.domain.interactor.splashscreen.SplashScreenInteractor
import com.myxllite.app.kit.base.viewmodel.BaseViewModel
import javax.inject.Inject

/**
 * Created by Bacharudin Farisi on 29/03/22
 */
class SplashScreenViewModel @Inject constructor(
    private val splashScreenInteractor: SplashScreenInteractor,
    private val scheduler: SchedulerProvider
) : BaseViewModel(), SplashScreenViewModelContract {
    override fun setInAppUpdateShow(isShow: Boolean) {
        splashScreenInteractor.setInAppUpdateShow(isShow)
    }
}