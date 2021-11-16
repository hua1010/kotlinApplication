package com.toptech.warkeapplication.ui.activity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.toptech.warkeapplication.Constant
import com.toptech.warkeapplication.R
import com.toptech.warkeapplication.databinding.RegisterFragmentBinding
import com.toptech.warkeapplication.generated.callback.OnClickListener
import com.toptech.warkeapplication.ui.activity.viewmodel.CustomViewModelProvider
import com.toptech.warkeapplication.ui.activity.viewmodel.RegisterModel

class RegisterFragment :Fragment() {
    private val registerModel:RegisterModel by viewModels {
        CustomViewModelProvider.providerRegisterModle(requireContext())
    }

    private lateinit var _binding:RegisterFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = RegisterFragmentBinding.inflate(inflater,container,false)

        onSubscribeUi(_binding)
        return _binding.root
    }

    private fun onSubscribeUi(binding:RegisterFragmentBinding){
        binding.model = registerModel
        binding.activity = activity
        binding.lifecycleOwner = this

        val sageArgs:RegisterFragmentArgs by navArgs()
        val email = sageArgs.email
        binding.model?.mail?.value = email

        binding.btnRegister.setOnClickListener {
            registerModel.register()
            val bundle = Bundle()
            bundle.putString(Constant.ARGS_NAME,registerModel.n.value)
            findNavController().navigate(R.id.loginFragment, bundle, null)
        }
    }

}