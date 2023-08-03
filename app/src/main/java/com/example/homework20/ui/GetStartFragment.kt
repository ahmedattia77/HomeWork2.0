package com.example.homework20.ui

import android.content.Intent
import androidx.navigation.ActivityNavigator
import com.example.homework20.databinding.FragmentGetStartBinding


class GetStartFragment :
    BaseFragment<FragmentGetStartBinding>(FragmentGetStartBinding::inflate) {


    override fun setUp() {
        callBack()
    }

    fun callBack () {
        binding.exploreBt.setOnClickListener{
            val action = GetStartFragmentDirections.actionGetStartFragmentToHomeFragment()
            val extras = ActivityNavigator.Extras.Builder()
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                .build()
            navController.navigate(action , extras)
        }
    }



}