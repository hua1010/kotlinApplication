package com.toptech.warkeapplication.ui.activity

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.toptech.warkeapplication.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)

        setContentView(R.layout.activity_login)

    }
}