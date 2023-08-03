package com.example.homework20.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework20.models.LoginResponse
import com.example.homework20.repository.AuthenticationRepo
import com.example.homework20.ui.getError
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class SingUpViewModel : ViewModel (){
    private val _signUpLiveData = MutableLiveData<LoginResponse>()
    val signUpLiveData: LiveData<LoginResponse> = _signUpLiveData

    private val _signUpErrorLiveData = MutableLiveData<String>()
    val signUpErrorLiveData: LiveData<String> = _signUpErrorLiveData

    fun singUp (name:String ,email: String , password: String) {
        viewModelScope.launch {
            try {
                val res = AuthenticationRepo().signUp(name ,email, password)
                if (res.isSuccessful)
                    _signUpLiveData.postValue(res.body())
                else
                    _signUpErrorLiveData.postValue(res.errorBody()?.getError().toString())
            } catch (e: IOException) {
                e.printStackTrace()
                _signUpErrorLiveData.postValue(e.message.toString())
            } catch (e: HttpException) {
                e.printStackTrace()
                _signUpErrorLiveData.postValue(e.response()?.errorBody()?.getError().toString())
            } catch (e: Exception) {
                e.printStackTrace()
                _signUpErrorLiveData.postValue(e.message.toString())
            }
        }
    }

}