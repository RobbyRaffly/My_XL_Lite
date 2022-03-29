package com.myxllite.app.core.di

import com.myxllite.app.core.di.CoreComponent

interface CoreComponentProvider {

    fun provideCoreComponent(): CoreComponent
}