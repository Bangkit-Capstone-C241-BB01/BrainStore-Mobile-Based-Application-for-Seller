package com.dicoding.braincoresellerapp.ui.upload

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
import com.dicoding.braincoresellerapp.R
import com.dicoding.braincoresellerapp.data.modal.Result
import com.dicoding.braincoresellerapp.databinding.FragmentUploadBinding
import com.dicoding.braincoresellerapp.utils.FileUtils
import com.dicoding.braincoresellerapp.utils.ViewModelFactory
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class UploadFragment : Fragment() {

    private var _binding: FragmentUploadBinding? = null
    private val binding get() = _binding!!
    private var currentImageUri: Uri? = null
    private val uploadViewModel: UploadViewModel by viewModels {
        ViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUploadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.productPicture.setOnClickListener{startGallery()}
        binding.categoryTag.setOnClickListener{
            showCategoryDialog()
        }
        binding.btnUpload.setOnClickListener{uploadProduct()}
    }

    private fun showCategoryDialog() {
        val categories = resources.getStringArray(R.array.category_array)

        val builder = AlertDialog.Builder(requireContext())
        builder.setItems(categories) { _, which ->
            binding.category.text = categories[which]
        }
        builder.show()
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
            binding.productPicture.setImageURI(it)
        }
    }

    private fun uploadProduct(){
        val imgUri = currentImageUri ?: return
        val imgFile = FileUtils.getFileFromUri(requireContext(), imgUri) ?: return
        val imgRequestBody = imgFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val imgMultipart = MultipartBody.Part.createFormData("img_product", imgFile.name, imgRequestBody)

        val productName = binding.productNameInput.text.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val productPrice = binding.productPriceInput.text.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val productSpec = binding.productSpecificationsInput.text.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val productDesc = binding.productDescriptionInput.text.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val productStock = binding.productStockInput.text.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val productCategory = binding.category.text.toString().toRequestBody("text/plain".toMediaTypeOrNull())

        uploadViewModel.uploadProduct(
            imgMultipart, productName, productPrice, productSpec, productDesc, productStock, productCategory)
            .observe(viewLifecycleOwner){result ->
            when(result){
                is Result.Loading -> {
                    showLoading(true)
                }
                is Result.Success -> {
                    showLoading(false)
                    showAlertDialog("Success",  result.data.msg ?:"Product uploaded successfully.")
                    findNavController().navigate(R.id.action_uploadFragment_to_homeFragment)
                }
                is Result.Error -> {
                    showLoading(false)
                    showAlertDialog("Error", result.error )
                }
            }
        }
    }

    private fun showAlertDialog(title: String, message: String) {
        AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.isVisible = state
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}