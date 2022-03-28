package com.myxllite.app.kit.base.activity

import android.Manifest
import android.annotation.TargetApi
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

/**
 * Created by Bacharudin Farisi on 28/03/22
 */
abstract class BaseAppCompatActivity: AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = lifecycleScope.coroutineContext

    fun hideKeyboard() {
        val view = this.currentFocus
        view?.let {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    protected fun hasPermission(permission: String): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    fun setStatusBarSolidColor(@ColorRes color: Int, isLightStatusBar: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window?.let {
                it.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                if (isLightStatusBar && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    it.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
                it.statusBarColor = ContextCompat.getColor(this, color)
            }
        }
    }
}