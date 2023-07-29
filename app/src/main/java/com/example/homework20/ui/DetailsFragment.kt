package com.example.homework20.ui

import com.example.homework20.databinding.FragmentDetailsBinding
import com.example.homework20.models.VacationModel


class DetailsFragment :
    BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {



    override fun setUp() {
        setData()
        callBack()
    }

    fun callBack () {
        binding.back.setOnClickListener {
            val action = DetailsFragmentDirections.actionDetailsFragmentToHomeFragment()
            navController.navigate(action)
        }
    }

    fun setData (){
        val obj = arguments?.getSerializable("currentItem" ) as VacationModel
        binding.rev.text = obj.review
        binding.title.text = obj.title
        binding.image.setImageResource(obj.image)
    }
    

}