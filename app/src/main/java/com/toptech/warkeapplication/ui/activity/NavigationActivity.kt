package com.toptech.warkeapplication.ui.activity

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import com.toptech.warkeapplication.R

class NavigationActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        //侧滑控件
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        window.sharedElementsUseOverlay = false

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
    }

}