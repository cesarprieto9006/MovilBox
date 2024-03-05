package com.example.movilbox.ui.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.movilbox.R
import com.example.movilbox.databinding.FragmentDetailProductBinding
import com.example.movilbox.ui.view.detail.adapter.ImageSlideAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailProductFragment : Fragment() {

    private var _binding: FragmentDetailProductBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<DetailProductViewModel>()

    private lateinit var viewPagerAdapter: ImageSlideAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentDetailProductBinding.inflate(inflater, container, false)

        configureBinding()
        configureProductDetail()
        configureOnclick()

        viewModel.getProductDetails(arguments?.getInt("id").toString())

        return binding.root
    }

    private fun configureOnclick() {
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun configureProductDetail() {
        viewModel.product.observe(viewLifecycleOwner) { productDetail ->
            if (productDetail != null) {
                binding.tvTitleProduct.text = productDetail.title
                binding.tvRatingProduct.text = productDetail.rating.toString()
                binding.tvDescriptionProduct.text = productDetail.description
                binding.tvPriceProduct.text = requireContext().getString(R.string.cost,productDetail.price.toString())
                binding.tvStockProduct.text = requireContext().getString(R.string.quantity,productDetail.stock.toString())
                binding.tvCategoryProduct.text = requireContext().getString(R.string.category,productDetail.category)
                binding.tvDiscountProduct.text = requireContext().getString(R.string.discount,productDetail.discountPercentage.toString())
                viewPagerAdapter = ImageSlideAdapter(requireContext(), productDetail.images)
                binding.viewpager.adapter = viewPagerAdapter
                binding.cIndicator.setViewPager(binding.viewpager)
            }
        }
    }

    private fun configureBinding() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

}
