package com.example.homework20


import android.os.Bundle
import android.widget.Toast
import com.example.BaseFragment
import com.example.homework20.databinding.FragmentSingUpBinding
import com.example.homework20.models.Login
import java.util.regex.Pattern


class SingUpFragment
    : BaseFragment<FragmentSingUpBinding> (FragmentSingUpBinding::inflate) {

    private lateinit var loginAu: Login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginAu = Login(requireContext())
    }

    override fun setUp() {
        callBack()
    }


    fun callBack (){

        binding.signBt.setOnClickListener {
            if (checkFields()){
                loginAu.addUser(binding.phoneNumberEt.toString() , binding.passwordEt.toString())
                navController.navigate(SingUpFragmentDirections.actionSingUpFragmentToGetStartFragment())
                Toast.makeText(requireContext() , "signed Up successfully" ,Toast.LENGTH_SHORT).show()
            }
        }

        binding.back.setOnClickListener {
            navController.navigate(SingUpFragmentDirections.actionSingUpFragmentToLoginFragment())
        }

        binding.loginTv.setOnClickListener {
            navController.navigate(SingUpFragmentDirections.actionSingUpFragmentToLoginFragment())
        }
    }

    private fun checkFields(): Boolean {
        var phone = binding.phoneNumberEt.text.toString()
        var city = binding.cityEt.text.toString()
        var email = binding.emailEt.text.toString()
        var password = binding.passwordEt.text.toString()

        if (phone.isEmpty() || phone.length < 11){
            "PLS Enter Valid Phone Number".toast(requireContext())
            return false
        }
        else if (password.length < 8){
            "PLS Enter 8 char of password".toast(requireContext())
            return false
        }

        else if (city.isEmpty()){
            "PLS Enter City".toast(requireContext())
            return false
        }

        val emailPatter = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+"
        )

        if (!emailPatter.matcher(email).matches()){
            "PLS Enter valid Email".toast(requireContext())
            return false
        }

        if (!binding.checkbox.isChecked){
                "Make sure you that agree with our policy".toast(requireContext())
            return false
        }

        "Welcome to our Application we're coming soon :)".toast(requireContext())

        return true
    }

}