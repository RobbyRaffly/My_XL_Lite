package com.myxllite.app.kit.base.fragment

import android.Manifest
import android.annotation.TargetApi
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.myxllite.app.kit.base.activity.BaseAppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

/**
 * Created by Bacharudin Farisi on 28/03/22
 */
abstract class BaseCompatFragment : Fragment(), CoroutineScope {

    private var baseActivity: BaseAppCompatActivity? = null

    override val coroutineContext: CoroutineContext
        get() = lifecycleScope.coroutineContext

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseAppCompatActivity) {
            baseActivity = context
        }
    }

    override fun onDetach() {
        baseActivity = null
        super.onDetach()
    }

    protected fun hideKeyboard() {
        baseActivity?.hideKeyboard()
    }

    @TargetApi(Build.VERSION_CODES.M)
    protected fun hasPermission(permission: String): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                activity?.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    @TargetApi(Build.VERSION_CODES.M)
    protected fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    protected fun isStoragePermissionGranted(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
        } else {
            hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    && hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    protected fun checkStoragePermission(
        requestCode: Int, onPermissionGranted: (() -> Unit)? = null
    ) {
        if (isStoragePermissionGranted()) {
            onPermissionGranted?.invoke()
        } else {
            val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            } else {
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            }

            requestPermissionsSafely(permissions, requestCode)
        }
    }

    protected fun getBaseActivity(): BaseAppCompatActivity? = baseActivity
}