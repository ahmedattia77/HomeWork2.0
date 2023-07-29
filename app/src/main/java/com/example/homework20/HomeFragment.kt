package com.example.homework20
import androidx.activity.addCallback
import com.example.BaseFragment
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
        activity?.onBackPressedDispatcher?.addCallback(this) {

        }
    }

    fun setUpRecycle (){
        val adapter = VacationAdapter(DataManager.getData() , this)
        binding.recycle.adapter = adapter
    }

    override fun onItemClick(CurrentItem: VacationModel) {
        var action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(CurrentItem)

        navController.navigate(action)
    }


}