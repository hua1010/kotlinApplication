package com.toptech.warkeapplication.ui.activity.db.repository

import androidx.lifecycle.LiveData
import com.toptech.warkeapplication.ui.activity.db.dao.UserDao
import com.toptech.warkeapplication.ui.activity.db.data.User
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class UserRepository private constructor(private val userDao: UserDao) {

    /**
     * 获取用户信息
     */
    fun login(account:String, pwd:String):LiveData<User?>
        = userDao.login(account,pwd)

    suspend fun register(email:String, account:String, pwd:String):Long{
        return withContext(IO){
            userDao.insertUser(User(account,pwd,email,""))
        }
    }

    companion object{
        @Volatile
        private var instance : UserRepository? = null

        fun getInstance(userDao: UserDao):UserRepository =
            instance ?: synchronized(this){
                instance ?: UserRepository(userDao).also {
                    instance = it
                }
            }
    }
}