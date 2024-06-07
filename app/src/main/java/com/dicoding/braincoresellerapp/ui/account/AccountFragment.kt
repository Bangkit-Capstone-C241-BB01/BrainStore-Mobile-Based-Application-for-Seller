package com.dicoding.braincoresellerapp.ui.account

import android.app.AlertDialog
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.dicoding.braincoresellerapp.R
import com.dicoding.braincoresellerapp.data.modal.Result
import com.dicoding.braincoresellerapp.data.response.account.SellerResponse
import com.dicoding.braincoresellerapp.databinding.FragmentAccountBinding
import com.dicoding.braincoresellerapp.utils.FileUtils
import com.dicoding.braincoresellerapp.utils.ViewModelFactory
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody


class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!
    private var currentImageUri: Uri? = null
    private var isEditMode = false
    private val accountViewModel: AccountViewModel by viewModels {
        ViewModelFactory(requireContext())
    }

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
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_accountFragment_to_settingFragment)
        }
        setEditTextsEnabled(false)
        arguments?.getParcelable<SellerResponse>("SELLER_DATA")?.let {
            displayStoreData(it)
        }
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
            saveStoreData()
        }
    }

    private fun setEditTextsEnabled(enabled: Boolean) {
        binding.inputStoreDesc.isEnabled = enabled
        binding.inputStoreAddress.isEnabled = enabled
    }

    private fun displayStoreData(seller: SellerResponse) {
        binding.inputStoreDesc.setText(seller.storeDesc)
        binding.inputStoreAddress.setText(seller.storeLocation)
        Glide.with(this)
            .load(seller.storeImg)
            .into(binding.imageProfile)
    }

    private fun saveStoreData() {
        val imgUri = currentImageUri ?: return
        val imgFile = FileUtils.getFileFromUri(requireContext(), imgUri) ?: return
        val imgRequestBody = imgFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val imgMultipart = MultipartBody.Part.createFormData("img_store", imgFile.name, imgRequestBody)

        val storeDesc = binding.inputStoreDesc.text.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val storeLocation = binding.inputStoreAddress.text.toString().toRequestBody("text/plain".toMediaTypeOrNull())

        accountViewModel.UpdateStoreData(imgMultipart, storeDesc, storeLocation).observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Success -> {
                    showLoading(false)
                    showAlertDialog("Success", "Success Update Data")
                }
                is Result.Error -> {
                    showLoading(false)
                    showAlertDialog("Error", "Failed Update Data")
                }
                is Result -> {
                    showLoading(true)
                }
            }
        }

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