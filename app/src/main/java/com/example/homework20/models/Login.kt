package com.example.homework20.models

import android.content.Context
import android.content.SharedPreferences

class Login {

    lateinit var preferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var context:Context
    var PRIVATEMODE : Int = 0


    constructor(context: Context){
        this.context = context
        preferences = context.getSharedPreferences(PREV_NAME , PRIVATEMODE)
        editor = preferences.edit()
    }

    companion object{
        val PREV_NAME : String = "login_preference"
        val IS_LOGIN : String = "isLoggedIn"
        val KEY_NUMBER:String = "number"
        val KEY_PASSWORD:String = "password"
    }

    fun addUser (number:String , password:String){

            editor.putBoolean(IS_LOGIN , false)
            editor.putString(KEY_NUMBER , number)
            editor.putString(KEY_PASSWORD , password)
            editor.commit()

    }

    fun isLoggedIn () :Boolean {
        val number = preferences.getString(KEY_NUMBER , null)
        if (number != null) return true
        return false
    }

    fun userExist(number:String , password:String) : Boolean{
        if (number == preferences.getString(KEY_NUMBER , null)
            && password == preferences.getString(KEY_PASSWORD , null))
                return true
        return false
    }



}