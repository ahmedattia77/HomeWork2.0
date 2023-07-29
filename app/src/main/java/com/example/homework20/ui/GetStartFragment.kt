package com.example.homework20.ui

import com.example.homework20.databinding.FragmentGetStartBinding


class GetStartFragment :
    BaseFragment<FragmentGetStartBinding>(FragmentGetStartBinding::inflate) {


    override fun setUp() {
        callBack()
    }

    fun callBack () {
        binding.exploreBt.setOnClickListener{
            navController.navigate(GetStartFragmentDirections.actionGetStartFragmentToHomeFragment()) }
    }



}