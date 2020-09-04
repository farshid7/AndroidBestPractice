package com.example.androidbestpractice.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<U : ViewDataBinding> : Fragment() {
    lateinit var binding: U

    @LayoutRes
    protected abstract fun getLayout(): Int
    protected abstract fun init()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, getLayout(), container, false)
        init()
        binding.root.fitsSystemWindows = true
        binding.lifecycleOwner = this
        return binding.root
    }


    protected fun finish() {
        requireActivity().onBackPressed()
    }


    protected fun finishActivity() {
        requireActivity().finish()
        requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    protected fun startActivityAndFinish(cls: Class<*>) {
        startActivity(Intent(requireContext(), cls))
        requireActivity().finish()
        requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    protected fun startActivityAndFinish(intent: Intent) {
        startActivity(intent)
        finish()
        requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    protected fun startActivity(cls: Class<*>) {
        startActivity(Intent(requireContext(), cls))
        requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    protected fun startActivity(activity: Activity, intent: Intent) {
        activity.startActivity(intent)
        activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

}