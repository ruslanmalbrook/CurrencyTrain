package com.currency.traintest.presentation.login

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.ViewModel


class LoginViewModel(private val context: Context) : ViewModel() {
    private var sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun isUserExist(name: String): Boolean {
        return (sharedPreferences.getString(name, "") ?: "") != ""
    }

    fun checkUserPassword(name: String, password: String): Boolean {
        return (sharedPreferences.getString(name, "") ?: "") == password
    }

    fun addNewUser(name: String, password: String) {
        sharedPreferences.edit().putString(name, password).apply()
        sharedPreferences.edit().putBoolean("entered", true).apply()
    }

    fun isValidEmail(target: CharSequence?): Boolean {
        return if (TextUtils.isEmpty(target)) {
            false
        } else {
            Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }
    }

    fun isUserEntered(): Boolean {
        return sharedPreferences.getBoolean("entered", false)
    }
}