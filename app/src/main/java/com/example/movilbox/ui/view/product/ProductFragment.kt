package com.example.movilbox.ui.view.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.movilbox.R
import com.example.movilbox.databinding.FragmentProductBinding
import com.example.movilbox.domain.model.ProductList
import com.example.movilbox.ui.view.product.adapter.ProductAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProductViewModel by viewModels()
    private lateinit var adapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentProductBinding.inflate(inflater, container, false)

        configureBinding()
        configureSearch()
        configureListProduct()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getProduct()
    }

    private fun configureBinding() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.rvProduct.isNestedScrollingEnabled = true
    }

    private fun configureListProduct() {
        viewModel.product.observe(viewLifecycleOwner) { movements ->
            if (movements != null) {
                adapter = ProductAdapter(movements, ::onItemClickAdd)
                binding.adapter = adapter
            }
        }
    }

    private fun onItemClickAdd(product: ProductList) {
        val bundle = bundleOf("id" to product.id)
        view?.findNavController()?.navigate(R.id.action_categoryFragment_to_detailProductFragment, bundle)
    }

    private fun configureSearch() {
        binding.etSearchProduct.doAfterTextChanged {
            viewModel.searchProduct(it.toString())
        }
    }
}