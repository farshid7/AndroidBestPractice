package com.example.androidbestpractice.ui.search

import android.util.Log
import androidx.fragment.app.viewModels
import com.example.androidbestpractice.R
import com.example.androidbestpractice.base.BaseFragment
import com.example.androidbestpractice.databinding.FragmentSearchBinding
import com.example.androidbestpractice.di.networkManager.Status

class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    private val viewModel: SearchFragmentViewModel by viewModels()
    override fun getLayout() = R.layout.fragment_search

    override fun init() {
        binding.viewModel = viewModel
        viewModel.searchLiveData.observe(viewLifecycleOwner) {
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