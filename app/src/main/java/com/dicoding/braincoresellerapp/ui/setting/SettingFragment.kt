package com.dicoding.braincoresellerapp.ui.setting

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.dicoding.braincoresellerapp.R
import com.dicoding.braincoresellerapp.data.modal.Result
import com.dicoding.braincoresellerapp.databinding.FragmentSettingBinding
import com.dicoding.braincoresellerapp.ui.login.LoginActivity
import com.dicoding.braincoresellerapp.utils.ViewModelFactory
import kotlinx.coroutines.launch

class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SettingViewModel by viewModels {
        ViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getSellerAccount()

        binding.logoutBtn.setOnClickListener{
            logOut()
        }

        binding.account.setOnClickListener{
            findNavController().navigate(R.id.action_settingFragment_to_accountFragment)
        }
    }

    private fun getSellerAccount(){
        viewModel.getSellerAccount().observe(viewLifecycleOwner){result ->
            when (result) {
                is Result.Loading -> {
                    //loading state
                }
                is Result.Success -> {
                    val seller = result.data
                    binding.tvUsername.text = seller.storeName
                    binding.tvEmail.text = seller.userName
                    Glide.with(this)
                        .load(seller.storeImg)
                        .into(binding.imgUser)
                }
                is Result.Error -> {
                    //state error
                }
            }
        }
    }

    private fun logOut() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.logout()
            navigateToLogin()
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(requireContext(), LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}