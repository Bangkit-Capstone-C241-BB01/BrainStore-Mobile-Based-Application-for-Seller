package com.dicoding.braincoresellerapp.ui.account

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.dicoding.braincoresellerapp.R
import com.dicoding.braincoresellerapp.databinding.FragmentAccountBinding


class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!
    private var currentImageUri: Uri? = null
    private var isEditMode = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.changeImageBtn.setOnClickListener{startGallery()}
        binding.btnEditData.setOnClickListener{toggleEditMode()}
        setEditTextsEnabled(false)
    }

    private fun startGallery() {
        launchGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val launchGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ){uri: Uri? ->
        if ( uri != null){
            currentImageUri = uri
            showImage()
        } else {
            Log.d("Photo picker", "No media selected")
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.imageProfile.setImageURI(it)
        }
    }

    private fun toggleEditMode() {
        isEditMode = !isEditMode
        setEditTextsEnabled(isEditMode)
        if (isEditMode) {
            binding.btnEditData.text = getString(R.string.save_data)
        } else {
            binding.btnEditData.text = getString(R.string.edit_data)
            // Handle data submission here if necessary
        }
    }

    private fun setEditTextsEnabled(enabled: Boolean) {
        binding.inputSellerName.isEnabled = enabled
        binding.inputStoreName.isEnabled = enabled
        binding.inputStoreEmail.isEnabled = enabled
        binding.inputStorePhone.isEnabled = enabled
        binding.inputStoreAddress.isEnabled = enabled
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}