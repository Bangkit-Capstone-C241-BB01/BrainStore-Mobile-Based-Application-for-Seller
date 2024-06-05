package com.dicoding.braincoresellerapp.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.dicoding.braincoresellerapp.R
import com.dicoding.braincoresellerapp.databinding.FragmentDetailBinding
import java.text.NumberFormat
import java.util.Locale

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("StringFormatMatches")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { bundle ->
            binding.productTitle.text = bundle.getString("PRODUCT_NAME")
            val price = bundle.getString("PRODUCT_PRICE")
            val formattedPrice = formatRupiah(price?.toDoubleOrNull() ?: 0.0)
            binding.priceTag.text = formattedPrice
            binding.productRating.text = bundle.getString("PRODUCT_RATE")
            val stock = bundle.getInt("PRODUCT_STOCK")
            binding.productStock.text = getString(R.string.stock_format, stock)
            binding.spesifikasiName.text = bundle.getString("PRODUCT_SPEC")
            binding.descriptionProduct.text = bundle.getString("PRODUCT_DESC")
            Glide.with(this)
                .load(bundle.getString("PRODUCT_IMG"))
                .centerCrop()
                .into(binding.imageView2)
        }
    }

    private fun formatRupiah(number: Double): String {
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        formatRupiah.maximumFractionDigits = 0
        return formatRupiah.format(number)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}