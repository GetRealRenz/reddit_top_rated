package com.getrealrenz.redditbrowser.presentation.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.getrealrenz.redditbrowser.simpleErasedName
import dagger.android.support.DaggerFragment
import java.lang.reflect.ParameterizedType

/**
 * Created on 02.10.2018.
 */

abstract class BaseFragment<B : ViewDataBinding> : DaggerFragment()
{
    protected lateinit var binding: B
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
        return binding.root
    }

    private fun getLayoutRes(): Int {
        val fragmentLayoutName = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
                .simpleErasedName()
                .replace("Binding", "")
                .split("(?<=[a-z])(?=[A-Z])|(?<=[A-Z])(?=[A-Z][a-z])".toRegex())
                .joinToString(separator = "_")
                .toLowerCase()

        val resourceName = "${context?.applicationContext?.packageName}:layout/$fragmentLayoutName"
        return resources.getIdentifier(resourceName, null, null)

    }

    abstract fun viewCreated()


}
