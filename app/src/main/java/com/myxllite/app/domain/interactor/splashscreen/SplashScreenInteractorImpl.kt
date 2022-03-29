package com.myxllite.app.domain.interactor.splashscreen

import com.myxllite.app.domain.repository.local.LocalDataSource
import com.myxllite.app.domain.repository.remote.RemoteDataSource
import javax.inject.Inject

/**
 * Created by Bacharudin Farisi on 29/03/22
 */
class SplashScreenInteractorImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : SplashScreenInteractor {
    override fun setInAppUpdateShow(isShow: Boolean) {
        localDataSource.setInAppUpdateShow(isShow)
    }
}