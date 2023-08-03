package com.example.homework20.ui
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.homework20.R
import com.example.homework20.databinding.FragmentSingUpBinding
import com.example.homework20.models.Login
import com.example.homework20.ui.viewModel.SingUpViewModel
import java.util.regex.Pattern


class SingUpFragment
    : BaseFragment<FragmentSingUpBinding>(FragmentSingUpBinding::inflate) {

    private lateinit var loginAu: Login
    private val viewModel :SingUpViewModel by viewModels()
    private lateinit var email :String
    private lateinit var password:String
    private lateinit var name :String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginAu = Login(requireContext())
    }

    override fun setUp() {
        observer()
        callBack()
    }

    private fun observer(){
        viewModel.signUpLiveData.observe(viewLifecycleOwner){
            it.message.toast(requireContext())
            loginAu.addUser(binding.emailEt.toString() , binding.passwordEt.toString())
            navController.navigate(SingUpFragmentDirections.actionSingUpFragmentToGetStartFragment())
            buttonAni(false)
            hideUiWed(false)
        }

        viewModel.signUpErrorLiveData.observe(viewLifecycleOwner){
            it.toast(requireContext())
            buttonAni(false)
            hideUiWed(false)
        }

    }

    private fun callBack (){

        binding.signBt.setOnClickListener {
            if (checkFields()){
                if (!loginAu.userExist(binding.emailEt.toString() ,binding.passwordEt.toString())){
                    email = binding.emailEt.text.toString()
                    password = binding.passwordEt.text.toString()
                    name = binding.nameEt.text.toString()
                    viewModel.singUp(name ,email , password)
                    buttonAni(true)
                    hideUiWed(true)
                }else{
                    "this user has been sing up already".toast(requireContext())
                }
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
        val phone = binding.nameEt.text.toString()
        val city = binding.cityEt.text.toString()
        val email = binding.emailEt.text.toString()
        val password = binding.passwordEt.text.toString()

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

    private fun buttonAni(turn:Boolean){
        if (turn)
            binding.signBt.startAnimation()
        else{
            binding.signBt.revertAnimation{
                binding.signBt.setBackgroundResource(R.drawable.rounded_background_bt)
            }
        }
    }

    fun hideUiWed (turn:Boolean){
        if (turn)
            binding.linAlready.visibility = View.GONE
        else
            binding.linAlready.visibility = View.VISIBLE
    }


}