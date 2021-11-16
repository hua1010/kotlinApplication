package com.toptech.warkeapplication.ui.activity.db

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.toptech.warkeapplication.Constant
import com.toptech.warkeapplication.ui.activity.db.dao.UserDao
import com.toptech.warkeapplication.ui.activity.db.data.User
import com.toptech.warkeapplication.utils.AppPrefsUtils

@Database(entities = [User::class],version = 2,exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun userDao():UserDao

    companion object{
        @Volatile
        private var instance:AppDataBase? = null

        fun getInstace(context: Context):AppDataBase{
            return instance ?: synchronized(this){
                instance ?: buildDataBase(context).also {
                    instance = it
                }
            }
        }

        private fun buildDataBase(context: Context):AppDataBase{
            return Room.databaseBuilder(context, AppDataBase::class.java, "database")
                .build()
        }
    }
}