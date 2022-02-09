package com.toptech.warkeapplication.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.toptech.warkeapplication.R
import com.toptech.warkeapplication.utils.AppPrefsUtils
import java.util.*

class LoginActivity : AppCompatActivity() {

    private val REQUEST_CODE_PERMISSIONS = 101
    private val KEY_PERMISSIONS_REQUEST_COUNT = "KEY_PERMISSIONS_REQUEST_COUNT"
    private val MAX_NUMBER_REQUEST_PERMISSIONS = 2

    private val permissions = Arrays.asList(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA
    )
    private var requestPermissionCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)

        setContentView(R.layout.activity_login)
        savedInstanceState?.let {
            requestPermissionCount = it.getInt(KEY_PERMISSIONS_REQUEST_COUNT, 0)
        }
        requestPermissions()
    }

    private fun requestPermissions(){
        if(!checkPermissions()){
            if(requestPermissionCount < MAX_NUMBER_REQUEST_PERMISSIONS){
                requestPermissionCount++
                ActivityCompat.requestPermissions(this,permissions.toTypedArray(),REQUEST_CODE_PERMISSIONS)
            }else{
                Toast.makeText(this, R.string.set_permissions_in_settings, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun checkPermissions() : Boolean{
        var hasPermission = true
        for (permisson in permissions){
            hasPermission = hasPermission and (ContextCompat.checkSelfPermission(this,permisson) == PackageManager.PERMISSION_GRANTED)
        }
        return hasPermission
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQUEST_CODE_PERMISSIONS){
            requestPermissions()
        }
    }
}