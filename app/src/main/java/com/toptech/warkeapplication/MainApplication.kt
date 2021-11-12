package com.toptech.warkeapplication

import android.app.Application
import android.content.Context
import com.tencent.mmkv.MMKV
import com.tencent.mmkv.MMKVLogLevel

class MainApplication :Application() {

    companion object{
        lateinit var mContext:Context
    }

    override fun onCreate() {
        super.onCreate()
        mContext = this

        //MMKV初始化
        val root = mContext.filesDir.absolutePath + "/mmkv"
        MMKV.initialize(this,root,MMKVLogLevel.LevelInfo)
    }

}