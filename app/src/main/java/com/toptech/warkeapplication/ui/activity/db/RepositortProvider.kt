package com.toptech.warkeapplication.ui.activity.db

import android.content.Context
import com.toptech.warkeapplication.ui.activity.db.repository.UserRepository

object RepositortProvider {

    fun UserRepositort(context: Context):UserRepository{
        return UserRepository.getInstance(AppDataBase.getInstace(context).userDao())
    }
}