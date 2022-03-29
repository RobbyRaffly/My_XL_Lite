package com.myxllite.app.presentation.splashscreen.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.myxllite.app.domain.interactor.splashscreen.SplashScreenInteractor
import com.myxllite.app.domain.interactor.splashscreen.SplashScreenInteractorImpl
import com.myxllite.app.kit.base.viewmodel.ViewModelProviderFactory
import com.myxllite.app.presentation.splashscreen.viewmodel.SplashScreenViewModel
import dagger.Binds
import dagger.Module

/**
 * Created by Bacharudin Farisi on 29/03/22
 */
@Module
interface SplashScreenModule {

    @Binds
    fun bindSplashScreenInteractor(splashScreenInteractorImpl: SplashScreenInteractorImpl): SplashScreenInteractor

    @Binds
    fun bindSplashScreenViewModel(splashScreenViewModel: SplashScreenViewModel): ViewModel

    @Binds
    fun bindSplashScreenViewModelProviderFactory(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}