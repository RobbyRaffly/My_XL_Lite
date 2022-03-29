package com.myxllite.app

import android.app.Activity
import android.app.Service
import androidx.fragment.app.Fragment
import com.myxllite.app.core.di.CoreComponentProvider

/**
 * Created by Bacharudin Farisi on 29/03/22
 */

fun Activity.coreComponent() =
    (applicationContext as? CoreComponentProvider)?.provideCoreComponent()
        ?: throw IllegalStateException("CoreComponentProvider not implemented: $applicationContext")

fun Fragment.coreComponent() = requireActivity().coreComponent()

fun Service.coreComponent() =
    (applicationContext as? CoreComponentProvider)?.provideCoreComponent()
        ?: throw IllegalStateException("CoreComponentProvider not implemented: $applicationContext")