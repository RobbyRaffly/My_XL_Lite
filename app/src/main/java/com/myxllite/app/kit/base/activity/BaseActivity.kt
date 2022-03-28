package com.myxllite.app.kit.base.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.myxllite.app.kit.base.viewmodel.BaseViewModel
import com.myxllite.app.kit.base.viewmodel.BaseViewModelContract

/**
 * Created by Bacharudin Farisi on 28/03/22
 */
abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel, I : BaseViewModelContract> :
    BaseAppCompatActivity() {

    private lateinit var viewDataBinding: T
    private lateinit var viewModel: V

    /**
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract fun getBindingVariable(): Int

    abstract fun getFactoryViewModel(): ViewModelProvider.Factory

    abstract fun getClassViewModel(): Class<V>

    abstract fun performDependencyInjection()

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, getFactoryViewModel()).get(getClassViewModel())
        performDataBinding()
    }

    protected fun getViewDataBinding(): T = viewDataBinding

    @Suppress("UNCHECKED_CAST")
    fun getViewModel(): I = viewModel as I

    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        viewDataBinding.setVariable(getBindingVariable(), getViewModel())
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.executePendingBindings()
    }
}
