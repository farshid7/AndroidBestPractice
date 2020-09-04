package com.example.androidbestpractice.view.ui.home

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidbestpractice.R
import com.example.androidbestpractice.adapter.DiscoverAdapter
import com.example.androidbestpractice.base.BaseFragment
import com.example.androidbestpractice.databinding.FragmentHomeBinding
import com.example.androidbestpractice.di.networkManager.Status

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val viewModel: HomeFragmentViewModel by viewModels()
    private lateinit var adapter: DiscoverAdapter

    override fun getLayout() = R.layout.fragment_home

    override fun init() {
        adapter = DiscoverAdapter.build(binding.rv) {
            findNavController().navigate(
                HomeFragmentDirections.actionNavigationHomeToDetailFragment(
                    it
                )
            )
        }

        viewModel.discoverLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                    Log.e("TAG", "LOADING: ")
                }
                Status.SUCCESS -> {
                    binding.progress.visibility = View.GONE
                    adapter.addItem(it.data!!.results)
                    Log.e("TAG", "SUCCESS: " + it.data)
                }
                Status.ERROR -> {
                    binding.progress.visibility = View.GONE
                    Log.e("TAG", "ERROR: " + it.errorBody)
                }
                Status.FILED -> {
                    binding.progress.visibility = View.GONE
                    Log.e("TAG", "FILED: ")
                }
            }
        }
    }
}