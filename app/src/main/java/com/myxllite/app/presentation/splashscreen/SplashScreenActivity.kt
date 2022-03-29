package com.myxllite.app.presentation.splashscreen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.myxllite.app.BR
import com.myxllite.app.R
import com.myxllite.app.coreComponent
import com.myxllite.app.databinding.ActivitySplashScreenBinding
import com.myxllite.app.kit.base.activity.BaseActivity
import com.myxllite.app.presentation.splashscreen.di.DaggerSplashScreenActivityComponent
import com.myxllite.app.presentation.splashscreen.viewmodel.SplashScreenViewModel
import com.myxllite.app.presentation.splashscreen.viewmodel.SplashScreenViewModelContract
import javax.inject.Inject

/**
 * Created by Bacharudin Farisi on 29/03/22
 */
@SuppressLint("CustomSplashScreen")
class SplashScreenActivity :BaseActivity<ActivitySplashScreenBinding, SplashScreenViewModel, SplashScreenViewModelContract>(), SplashScreenActivityView {

    @Inject
    lateinit var viewModelfactory:ViewModelProvider.Factory

    override fun getLayoutId(): Int = R.layout.activity_splash_screen

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getFactoryViewModel(): ViewModelProvider.Factory = viewModelfactory

    override fun getClassViewModel(): Class<SplashScreenViewModel> = SplashScreenViewModel::class.java

    override fun performDependencyInjection() {
        DaggerSplashScreenActivityComponent.builder().coreComponent(coreComponent()).build().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewModel().setInAppUpdateShow(true)
    }
}