package com.example.gb_libs_lesson1

import android.os.Parcelable
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory

interface FragmentInitializer<T: Parcelable> {

    fun newInstance(arg: T): Fragment {
        val declarationClassName = this::class.java.declaringClass?.name
        return FragmentFactory().instantiate(
            this::class.java.declaringClass?.classLoader!!,
            declarationClassName!!
        ).apply {
            arguments = bundleOf(
                declarationClassName to arg
            )
        }
    }

    fun <T: Parcelable> Fragment.extractInitParams(): T {
        val initParams = arguments?.getParcelable<T>(this::class.java.name)
        return initParams!!
    }

    fun <T: Parcelable> Fragment.initParams() = lazy<T> {
        extractInitParams()
    }

}