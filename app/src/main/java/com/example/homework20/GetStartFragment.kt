package com.example.homework20
import com.example.BaseFragment
import com.example.homework20.databinding.FragmentGetStartBinding


class GetStartFragment :
    BaseFragment<FragmentGetStartBinding>(FragmentGetStartBinding::inflate) {


    override fun setUp() {
        "Login successfully".toast(requireContext())
        callBack()
    }

    fun callBack () {
        binding.exploreBt.setOnClickListener{
            navController.navigate(GetStartFragmentDirections.actionGetStartFragmentToHomeFragment()) }
    }



}