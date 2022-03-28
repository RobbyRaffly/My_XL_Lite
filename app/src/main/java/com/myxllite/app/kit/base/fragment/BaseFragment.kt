package com.myxllite.app.kit.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.myxllite.app.kit.base.viewmodel.BaseViewModel
import com.myxllite.app.kit.base.viewmodel.BaseViewModelContract

/**
 * Created by Bacharudin Farisi on 28/03/22
 */
abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel, I : BaseViewModelContract> :
    BaseCompatFragment() {

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

    override fun onAttach(context: Context) {
        performDependencyInjection()
        super.onAttach(context)
        viewModel = ViewModelProvider(this, getFactoryViewModel()).get(getClassViewModel())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.let {
            it.lifecycleOwner = this
            it.setVariable(getBindingVariable(), viewModel)
            it.executePendingBindings()
        }
    }

    protected fun getViewDataBinding(): T = viewDataBinding

    @Suppress("UNCHECKED_CAST")
    fun getViewModel(): I = viewModel as I
}