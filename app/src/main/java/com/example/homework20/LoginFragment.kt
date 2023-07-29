package com.example.homework20
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.BaseFragment
import com.example.homework20.databinding.FragmentLoginBinding
import com.example.homework20.models.Login


class LoginFragment
    : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private lateinit var loginAu:Login


    override fun onAttach(context: Context) {
        super.onAttach(context)

        loginAu = Login(requireContext())


        if (loginAu.isLoggedIn()){
            val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment2()
            navController.navigate(action)
        }


    }

    override fun setUp() {
        callBack()
    }

    fun callBack (){

        binding.loginBt.setOnClickListener {
            if (checkFields()){

                if (loginAu.userExist(binding.phoneNumberEt.toString() ,binding.phoneNumberEt.toString())){
                    loginAu.addUser(binding.phoneNumberEt.toString() , binding.phoneNumberEt.toString())
                    val action = LoginFragmentDirections.actionLoginFragmentToGetStartFragment3()
                    navController.navigate(action)
                }else{

                    "you're number or password is wrong".toast(requireContext())
                }
            }
        }

        binding.signUpTv.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToSingUpFragment2()
            navController.navigate(action)
        }


    }

     fun checkFields() : Boolean {
        var phone = binding.phoneNumberEt.text.toString()
        var password = binding.passwordEt.text.toString()

        if (phone.isEmpty() || phone.length < 11){
            "PLS Enter Valued Phone Number".toast(requireContext())
            return false
        }
        else if (password.length < 8){
            "PLS Enter 8 char of password".toast(requireContext())
            return false
        }

        return true
    }





}