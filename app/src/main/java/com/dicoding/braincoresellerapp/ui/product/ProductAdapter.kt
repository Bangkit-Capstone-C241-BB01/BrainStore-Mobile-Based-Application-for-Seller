package com.dicoding.braincoresellerapp.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.braincoresellerapp.R
import com.dicoding.braincoresellerapp.data.response.product.ProductsItem
import com.dicoding.braincoresellerapp.databinding.ItemProductBinding
import java.text.NumberFormat
import java.util.Locale

class ProductAdapter(private var listener: (ProductsItem) -> Unit) :
    ListAdapter<ProductsItem, ProductAdapter.ProductViewHolder>(DIFF_CALLBACK){

        class ProductViewHolder(private val binding: ItemProductBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bind(product: ProductsItem){
                    binding.productName.text = product.productName
                    val price = product.productPrice
                    val formattedPrice = formatRupiah(price?.toDoubleOrNull() ?: 0.0)
                    binding.productPrice.text = formattedPrice
                    binding.productRating.text = product.productRate
                    Glide.with(itemView.context)
                        .load(product.productImg)
                        .centerCrop()
                        .into(binding.imageProduct)

                    if (product.productAcceptance == true){
                        binding.overlay.visibility = View.GONE
                        binding.appeal.visibility = View.GONE
                    } else {
                        binding.overlay.visibility = View.VISIBLE
                        binding.appeal.visibility = View.VISIBLE
                    }
                    binding.appeal.setOnClickListener {
                        val bundle = Bundle()
                        bundle.putInt("PRODUCT_ID", product.productId!!)
                        itemView.findNavController().navigate(R.id.action_homeFragment_to_appealFragment, bundle)
                    }
                }

            private fun formatRupiah(number: Double): String {
                val localeID = Locale("in", "ID")
                val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
                formatRupiah.maximumFractionDigits = 0
                return formatRupiah.format(number)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)
        holder.itemView.setOnClickListener{listener(product)}
    }


    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ProductsItem>() {
            override fun areItemsTheSame(oldItem: ProductsItem, newItem: ProductsItem): Boolean {
                return oldItem.productId == newItem.productId
            }

            override fun areContentsTheSame(oldItem: ProductsItem, newItem: ProductsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}