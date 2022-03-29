package com.myxllite.app

import androidx.multidex.MultiDexApplication
import com.myxllite.app.core.di.AndroidContextModule
import com.myxllite.app.core.di.CoreComponent
import com.myxllite.app.core.di.CoreComponentProvider
import com.myxllite.app.core.di.DaggerCoreComponent

/**
 * Created by Bacharudin Farisi on 29/03/22
 */
class MainApplication:MultiDexApplication(),CoreComponentProvider {

    private lateinit var coreComponent: CoreComponent

    override fun onCreate() {
        super.onCreate()
        setupDagger()
    }

    override fun provideCoreComponent(): CoreComponent {
        return coreComponent
    }

    private fun setupDagger() {
        coreComponent = DaggerCoreComponent.builder()
            .androidContextModule(AndroidContextModule(this))
            .build()
    }
}