package com.dicoding.braincoresellerapp.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.braincoresellerapp.R
import com.dicoding.braincoresellerapp.data.modal.Result
import com.dicoding.braincoresellerapp.databinding.FragmentHomeBinding
import com.dicoding.braincoresellerapp.utils.ViewModelFactory

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels {
        ViewModelFactory(requireContext())
    }
    private lateinit var adapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeProducts()
    }

    private fun setupRecyclerView(){
        adapter = ProductAdapter { product ->
            val bundle = Bundle().apply {
                putInt("PRODUCT_ID", product.productId!!)
                putString("PRODUCT_NAME", product.productName)
                putString("PRODUCT_PRICE", product.productPrice)
                putString("PRODUCT_RATE", product.productRate)
                putString("PRODUCT_IMG", product.productImg)
                putString("PRODUCT_DESC", product.productDesc)
                putString("PRODUCT_SPEC", product.productSpec)
                putInt("PRODUCT_STOCK", product.productStock!!)
                putString("PRODUCT_CATEGORY", product.productCategory)
            }
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
        }
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = adapter
    }

    private fun observeProducts(){
        homeViewModel.getSellerProduct().observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Result.Loading -> showLoading(true)
                is Result.Success -> {
                    showLoading(false)
                    adapter.submitList(result.data.products)
                }
                is Result.Error -> {
                    showLoading(false)
                }
            }
        })
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.isVisible = state
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}