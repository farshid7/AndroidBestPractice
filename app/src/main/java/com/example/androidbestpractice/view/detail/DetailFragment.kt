package com.example.androidbestpractice.view.detail

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateViewModelFactory
import com.example.androidbestpractice.R
import com.example.androidbestpractice.base.BaseFragment
import com.example.androidbestpractice.databinding.FragmentDetailBinding
import com.example.androidbestpractice.di.networkManager.Status

class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    override fun getLayout() = R.layout.fragment_detail
    private val viewModel: DetailFragmentViewModel by viewModels {
        SavedStateViewModelFactory(
            requireActivity().application,
            this,
            arguments ?: Bundle()
        )
    }
    private var exists = false

    override fun init() {
        binding.dataResult = DetailFragmentArgs.fromBundle(requireArguments()).data
        binding.back.setOnClickListener {
            finish()
        }

        viewModel.isExistsLiveData.observe(viewLifecycleOwner) {
            if (it.status == Status.SUCCESS) {
                exists = it.data!!
                if (exists) {
                    binding.fab.setImageResource(R.drawable.ic_baseline_favorite_24)
                } else {
                    binding.fab.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                }
                binding.fab.setOnClickListener {
                    if (exists) {
                        viewModel.remove()
                        exists = false
                        binding.fab.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                        Toast.makeText(
                            requireContext(),
                            "removed from favorite",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        viewModel.insert()
                        exists = true
                        binding.fab.setImageResource(R.drawable.ic_baseline_favorite_24)
                        Toast.makeText(
                            requireContext(),
                            "added from favorite",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }


}