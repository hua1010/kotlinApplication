package com.toptech.warkeapplication.ui.activity.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.toptech.warkeapplication.ui.activity.db.RepositortProvider
import com.toptech.warkeapplication.ui.activity.db.repository.UserRepository

object CustomViewModelProvider {

    fun providerLoginModle(context: Context): ViewModelProvider.NewInstanceFactory{
        val repository:UserRepository = RepositortProvider.UserRepositort(context)
        return object :ViewModelProvider.NewInstanceFactory() {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return LoginModel(repository) as T
            }
        }
    }

    fun providerRegisterModle(context: Context):ViewModelProvider.NewInstanceFactory{
        val repository:UserRepository = RepositortProvider.UserRepositort(context)
        return object : ViewModelProvider.NewInstanceFactory(){
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return RegisterModel(repository) as T
            }
        }
    }
}