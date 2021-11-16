package com.toptech.warkeapplication.ui.activity.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.toptech.warkeapplication.ui.activity.db.data.User

@Dao
interface UserDao {

    @Query("Select * from user where user_account = :account AND user_pwd = :pwd")
    fun login(account:String, pwd:String):LiveData<User?>

    @Insert
    fun insertUser(user:User):Long
}