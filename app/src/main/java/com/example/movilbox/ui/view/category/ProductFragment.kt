package com.example.movilbox.ui.view.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.movilbox.databinding.FragmentProductBinding
import com.example.movilbox.domain.model.ProductList
import com.example.movilbox.ui.view.adapter.ProductAdapter
import com.example.movilbox.ui.view.viewmodel.ProductViewModel
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

    private fun onItemClickAdd(movement: ProductList) {
        //val bundle = bundleOf("productId" to movement.id)
        //view?.findNavController()?.navigate(R.id.action_mainFragment_to_detailProductFragment, bundle)
    }
}