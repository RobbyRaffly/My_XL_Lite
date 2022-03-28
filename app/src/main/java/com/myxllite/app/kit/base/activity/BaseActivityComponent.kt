package com.myxllite.app.kit.base.activity

import android.app.Activity

/**
 * Created by Bacharudin Farisi on 28/03/22
 */
interface BaseActivityComponent<T : Activity> {
    fun inject(activity: T)
}