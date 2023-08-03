package com.example.homework20.ui
import android.content.Context
import android.view.View
import androidx.fragment.app.viewModels
import com.example.homework20.R
import com.example.homework20.databinding.FragmentLoginBinding
import com.example.homework20.models.Login
import com.example.homework20.ui.viewModel.LoginViewModel
import java.util.regex.Pattern


class LoginFragment
    : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private lateinit var loginAu:Login
    private val viewModel :LoginViewModel by viewModels()
    private lateinit var email: String
    private lateinit var password : String

    override fun onAttach(context: Context) {
        super.onAttach(context)

        loginAu = Login(requireContext())


        if (loginAu.isLoggedIn()){
            val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment2()
            navController.navigate(action)
        }
    }

     override fun setUp() {
        observer()
        callBack()
    }

    private fun observer () {
        viewModel.loginLiveData.observe(viewLifecycleOwner){
            it.message.toast(requireContext())
            if (!loginAu.userExist(binding.emailEt.toString() ,binding.passwordEt.toString())) {
                loginAu.addUser(binding.emailEt.toString() , binding.passwordEt.toString())
                val action = LoginFragmentDirections.actionLoginFragmentToGetStartFragment3()
                navController.navigate(action)
                buttonAni(false)
                hideUiWed(false)

            }
        }

        viewModel.loginErrorLiveData.observe(viewLifecycleOwner){
            it.toast(requireContext())
            buttonAni(false)
            hideUiWed(false)
        }
    }

    private fun callBack (){

        binding.loginBt.setOnClickListener {
            if (checkFields()){
                email = binding.emailEt.text.toString()
                password = binding.passwordEt.text.toString()
                viewModel.login(email , password)
                buttonAni(true)
                hideUiWed(true)
            }
        }

        binding.signUpTv.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToSingUpFragment2()
            navController.navigate(action)
        }

    }

     private fun checkFields() : Boolean {
        var email = binding.emailEt.text.toString()
        var password = binding.passwordEt.text.toString()

         val emailPatter = Pattern.compile(
             "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+"
         )

         if (!emailPatter.matcher(email).matches()){
             "PLS Enter valid Email".toast(requireContext())
             return false
         }

        else if (password.length < 8){
            "PLS Enter 8 char of password".toast(requireContext())
            return false
        }

        return true
    }

    private fun buttonAni(turn:Boolean){
        if (turn)
            binding.loginBt.startAnimation()
        else{
            binding.loginBt.revertAnimation{
                binding.loginBt.setBackgroundResource(R.drawable.rounded_background_bt)
            }
        }
    }

    fun hideUiWed (turn:Boolean){
        if (turn)
            binding.dontHaveLin.visibility = View.GONE
        else
            binding.dontHaveLin.visibility = View.VISIBLE
    }

}