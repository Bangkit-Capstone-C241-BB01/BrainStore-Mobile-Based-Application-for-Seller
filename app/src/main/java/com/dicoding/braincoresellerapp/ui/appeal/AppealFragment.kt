package com.dicoding.braincoresellerapp.ui.appeal

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.dicoding.braincoresellerapp.R
import com.dicoding.braincoresellerapp.data.modal.Result
import com.dicoding.braincoresellerapp.data.response.appeal.AppealRequest
import com.dicoding.braincoresellerapp.databinding.FragmentAppealBinding
import com.dicoding.braincoresellerapp.utils.ViewModelFactory

class AppealFragment : Fragment() {

    private var productId: Int? = null
    private var _binding: FragmentAppealBinding? = null
    private val binding get() = _binding!!
    private val appealViewModel: AppealViewModel by viewModels {
        ViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAppealBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener { findNavController().navigate(R.id.action_appealFragment_to_homeFragment) }
        arguments?.let {
            productId = it.getInt("PRODUCT_ID")
        }
        binding.submitAppeal.setOnClickListener {
            val appealDesc = binding.inputAppealDes.text.toString().trim()
            if (productId != null && appealDesc.isNotEmpty()) {
                submitAppeal(productId!!, appealDesc)
            } else {
                Toast.makeText(context, "Please fill out the appeal description", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun submitAppeal(productId: Int, appealDesc: String){
        val appealRequest = AppealRequest(product_id = productId, appeal_desc = appealDesc)
        appealViewModel.appeal(appealRequest).observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Result.Loading -> {
                    showLoading(true)
                }
                is Result.Success -> {
                    showLoading(false)
                    showAlertDialog("Success", "Appeal submitted successfully")
                    findNavController().navigate(R.id.action_appealFragment_to_homeFragment)
                }
                is Result.Error -> {
                    showLoading(false)
                    showAlertDialog("Error", "Failed to submit appeal")
                }
            }
        })
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.isVisible = state
    }

    private fun showAlertDialog(title: String, message: String) {
        AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}