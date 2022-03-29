package com.myxllite.app.presentation.splashscreen.di

import com.myxllite.app.core.di.ActivityScope
import com.myxllite.app.core.di.CoreComponent
import com.myxllite.app.kit.base.activity.BaseActivityComponent
import com.myxllite.app.presentation.splashscreen.SplashScreenActivity
import dagger.Component

/**
 * Created by Bacharudin Farisi on 29/03/22
 */
@ActivityScope
@Component(modules = [SplashScreenModule::class], dependencies = [CoreComponent::class])
interface SplashScreenActivityComponent : BaseActivityComponent<SplashScreenActivity> {
}