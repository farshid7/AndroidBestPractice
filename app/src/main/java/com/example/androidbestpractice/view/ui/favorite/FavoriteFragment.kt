package com.example.androidbestpractice.view.ui.favorite

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidbestpractice.R
import com.example.androidbestpractice.adapter.DiscoverAdapter
import com.example.androidbestpractice.base.BaseFragment
import com.example.androidbestpractice.databinding.FragmentFavoriteBinding

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {
    private lateinit var adapter: DiscoverAdapter
    private val viewModel: FavoriteFragmentViewModel by viewModels()
    override fun getLayout() = R.layout.fragment_favorite

    override fun init() {
        adapter = DiscoverAdapter.build(binding.rv) {
            findNavController().navigate(
                FavoriteFragmentDirections.actionNavigationFavoriteToDetailFragment(
                    it
                )
            )
        }

//        viewModel.discoverLiveData.observe(viewLifecycleOwner) {
//            when (it.status) {
//                Status.LOADING -> {
//                    binding.progress.visibility = View.VISIBLE
//                    Log.e("TAG", "LOADING: ")
//                }
//                Status.SUCCESS -> {
//                    binding.progress.visibility = View.GONE
//                    adapter.addItem(it.data!!.results)
//                    Log.e("TAG", "SUCCESS: " + it.data)
//                }
//                Status.ERROR -> {
//                    binding.progress.visibility = View.GONE
//                    Log.e("TAG", "ERROR: " + it.errorBody)
//                }
//                Status.FILED -> {
//                    binding.progress.visibility = View.GONE
//                    Log.e("TAG", "FILED: ")
//                }
//            }
//        }

    }

}