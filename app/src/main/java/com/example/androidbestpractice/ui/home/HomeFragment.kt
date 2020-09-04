package com.example.androidbestpractice.ui.home

import android.util.Log
import androidx.fragment.app.viewModels
import com.example.androidbestpractice.R
import com.example.androidbestpractice.base.BaseFragment
import com.example.androidbestpractice.databinding.FragmentHomeBinding
import com.example.androidbestpractice.di.networkManager.Status

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val viewModel: HomeFragmentViewModel by viewModels()


    override fun getLayout() = R.layout.fragment_home

    override fun init() {
        viewModel.discoverLiveData.observe(viewLifecycleOwner){
            when (it.status) {
                Status.LOADING -> {
                    Log.e("TAG", "LOADING: ")
                }
                Status.SUCCESS -> {
                    Log.e("TAG", "SUCCESS: " + it.data)
                }
                Status.ERROR -> {
                    Log.e("TAG", "ERROR: " + it.errorBody)
                }
                Status.FILED -> {
                    Log.e("TAG", "FILED: ")
                }
            }
        }
    }
}