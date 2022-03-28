package com.myxllite.app.kit.base.fragment

import androidx.fragment.app.Fragment

/**
 * Created by Bacharudin Farisi on 28/03/22
 */
interface BaseFragmentComponent<T : Fragment> {

    fun inject(fragment: T)
}