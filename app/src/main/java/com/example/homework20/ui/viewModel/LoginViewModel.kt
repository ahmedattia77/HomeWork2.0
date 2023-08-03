package com.example.homework20.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework20.models.LoginResponse
import com.example.homework20.repository.AuthenticationRepo
import com.example.homework20.ui.getError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class LoginViewModel :ViewModel(){
    private val _loginLiveData = MutableLiveData<LoginResponse>()
    val loginLiveData: LiveData<LoginResponse> = _loginLiveData

    private val _loginErrorLiveData = MutableLiveData<String>()
    val loginErrorLiveData: LiveData<String> = _loginErrorLiveData


    fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val res = AuthenticationRepo().login(email, password)
                if (res.isSuccessful)
                    _loginLiveData.postValue(res.body())
                else
                    _loginErrorLiveData.postValue(res.errorBody()?.getError().toString())
            } catch (e: IOException) {
                e.printStackTrace()
                _loginErrorLiveData.postValue(e.message.toString())
            } catch (e: HttpException) {
                e.printStackTrace()
                _loginErrorLiveData.postValue(e.response()?.errorBody()?.getError().toString())
            } catch (e: Exception) {
                e.printStackTrace()
                _loginErrorLiveData.postValue(e.message.toString())
            }
        }
    }


}