package com.toptech.warkeapplication.ui.activity.viewmodel

import android.text.Editable
import android.text.TextWatcher
import android.util.JsonReader
import androidx.lifecycle.*
import androidx.work.ListenableWorker
import com.toptech.warkeapplication.MainApplication
import com.toptech.warkeapplication.ui.activity.db.data.User
import com.toptech.warkeapplication.ui.activity.db.repository.UserRepository
import com.toptech.warkeapplication.ui.activity.listener.SimpleWatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginModel constructor(
    private val repository: UserRepository
) : ViewModel() {

    val n = MutableLiveData("")
    val p = MutableLiveData("")
    val enable = MutableLiveData(false)

    /*val n = ObservableField<String>("")
    val p = ObservableField<String>("")*/
    //lateinit var lifecycleOwner: LifecycleOwner

    /**
     * 用户名改变回调的函数
     */
    fun onNameChanged(s: CharSequence) {
        //n.set(s.toString())
        n.value = s.toString()
        judgeEnable()
    }

    private fun judgeEnable(){
        enable.value = n.value!!.isNotEmpty() && p.value!!.isNotEmpty()
    }

    /**
     * 密码改变的回调函数
     */
    fun onPwdChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        //p.set(s.toString())
        p.value = s.toString()
        judgeEnable()
    }

    // SimpleWatcher 是简化了的TextWatcher
    val nameWatcher = object : SimpleWatcher() {
        override fun afterTextChanged(s: Editable?) {
            super.afterTextChanged(s)

            n.value = s.toString()
            //n.set(s.toString())
            judgeEnable()
        }
    }

    val pwdWatcher = object : SimpleWatcher() {
        override fun afterTextChanged(s: Editable?) {
            super.afterTextChanged(s)
            //p.set(s.toString())
            p.value = s.toString()
            judgeEnable()
        }
    }

    fun login(): LiveData<User?>? {
        val pwd = p.value!!
        val account = n.value!!
        //val pwd = p.get()!!
        //val account = n.get()!!
        return repository.login(account, pwd)
    }


}