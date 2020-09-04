package com.example.androidbestpractice.view.ui.search

import android.text.Editable
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidbestpractice.R
import com.example.androidbestpractice.adapter.DiscoverAdapter
import com.example.androidbestpractice.base.BaseFragment
import com.example.androidbestpractice.databinding.FragmentSearchBinding
import com.example.androidbestpractice.di.networkManager.Status
import com.example.androidbestpractice.view.ui.home.HomeFragmentDirections


class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    private val viewModel: SearchFragmentViewModel by viewModels()
    override fun getLayout() = R.layout.fragment_search
    private lateinit var adapter: DiscoverAdapter

    override fun init() {
        binding.viewModel = viewModel
        adapter = DiscoverAdapter.build(binding.rv) {
            findNavController().navigate(
                SearchFragmentDirections.actionNavigationSearchToDetailFragment(
                    it
                )
            )
        }

        viewModel.searchLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                    Log.e("TAG", "LOADING: ")
                }
                Status.SUCCESS -> {
                    binding.progress.visibility = View.GONE
                    adapter.updateItem(it.data!!.results)
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

        binding.queryText.addTextChangedListener {
            if (it!!.isEmpty()) {
                binding.remove.visibility = View.INVISIBLE
            } else {
                binding.remove.visibility = View.VISIBLE
            }
            if (it.length >= 3) {
                viewModel.search()
            }
        }

        binding.remove.setOnClickListener {
            binding.queryText.text = Editable.Factory.getInstance().newEditable("")
            adapter.updateItem(arrayListOf())
        }
    }
}