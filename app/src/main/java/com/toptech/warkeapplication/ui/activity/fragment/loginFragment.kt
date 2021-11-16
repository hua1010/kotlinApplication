package com.toptech.warkeapplication.ui.activity.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.toptech.warkeapplication.Constant
import com.toptech.warkeapplication.databinding.LoginFragmentBinding
import com.toptech.warkeapplication.ui.activity.MainActivity
import com.toptech.warkeapplication.ui.activity.viewmodel.CustomViewModelProvider
import com.toptech.warkeapplication.ui.activity.viewmodel.LoginModel
import com.toptech.warkeapplication.utils.AppPrefsUtils

class loginFragment : Fragment() {

    lateinit var binding:LoginFragmentBinding

    private val loginModel:LoginModel by viewModels{
        CustomViewModelProvider.providerLoginModle(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = LoginFragmentBinding.inflate(inflater,container,false)

        onSubscribeUi(binding)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun onSubscribeUi(binding: LoginFragmentBinding){
        binding.model = loginModel
        binding.activity = activity

        binding.lifecycleOwner = this

        binding.btnLogin.setOnClickListener {
            loginModel.login()?.observe(viewLifecycleOwner, Observer{
                    user -> user?.let {
                    AppPrefsUtils.putLong(Constant.SP_USER_ID, it.id)
                    AppPrefsUtils.putString(Constant.SP_USER_NAME, it.account)

                    val intent = Intent(context, MainActivity::class.java)
                    requireContext().startActivity(intent)
                    Toast.makeText(context, "登录成功", Toast.LENGTH_LONG).show()
                }
            })
        }
        arguments?.getString(Constant.ARGS_NAME)?.apply {
            loginModel.n.value = this
        }
    }

}