package com.toptech.warkeapplication.ui.activity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.toptech.warkeapplication.Constant
import com.toptech.warkeapplication.R
import com.toptech.warkeapplication.utils.AppPrefsUtils

class WelcomFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.welcome_fragment,container, false)
    }

    lateinit var btnLogin : Button
    lateinit var btnRegister : Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogin = view.findViewById<Button>(R.id.btn_login)
        btnRegister = view.findViewById(R.id.btn_register)

        btnLogin.setOnClickListener {
            val navOption  = navOptions {
                anim {
                    enter = R.anim.common_slide_in_left
                    exit = R.anim.common_slide_out_right
                    popEnter = R.anim.common_slide_in_right
                    popExit = R.anim.common_slide_out_left
                }
            }

            val name = AppPrefsUtils.getString(Constant.SP_USER_NAME)
            val bundle = Bundle()
            bundle.putString(Constant.ARGS_NAME,name)
            findNavController().navigate(R.id.login_fragment_nav,bundle,navOption)
        }

        btnRegister.setOnClickListener {
            val action = WelcomFragmentDirections
                .actionWelcomFragmentToRegisterFragment()

            findNavController().navigate(action)
        }
    }
}