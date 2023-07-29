package com.example.homework20.ui
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback

import com.example.homework20.adapters.VacationAdapter
import com.example.homework20.dataBase.DataManager
import com.example.homework20.databinding.FragmentHomeBinding
import com.example.homework20.listener.VacationOnClickListener
import com.example.homework20.models.VacationModel

class HomeFragment
    : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate), VacationOnClickListener{


    override fun setUp (){
      setUpRecycle()
      onBackPressed()
    }

    fun onBackPressed () {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
    }

    fun setUpRecycle (){
        val adapter = VacationAdapter(DataManager.getData() , this)
        binding.recycle.adapter = adapter
    }

    override fun onItemClick(CurrentItem: VacationModel) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(CurrentItem)
        navController.navigate(action)
    }


}