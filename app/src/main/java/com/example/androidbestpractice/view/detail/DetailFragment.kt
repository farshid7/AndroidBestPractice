package com.example.androidbestpractice.view.detail

import com.example.androidbestpractice.R
import com.example.androidbestpractice.base.BaseFragment
import com.example.androidbestpractice.databinding.FragmentDetailBinding

class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    override fun getLayout() = R.layout.fragment_detail

    override fun init() {
        binding.dataResult=DetailFragmentArgs.fromBundle(requireArguments()).data
        binding.back.setOnClickListener {
            finish()
        }
    }


}