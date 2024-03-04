package com.example.movilbox.ui.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.movilbox.databinding.ItemProductBinding
import com.example.movilbox.domain.model.ProductList
import java.text.DecimalFormat

class ProductAdapter(
    private val products: List<ProductList>,
    private val onItemClick: ((data: ProductList) -> Unit)? = null,
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val item = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(item)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val movement = products[position]
        holder.bind(movement)
    }

    override fun getItemCount() = products.size

    @SuppressLint("SetTextI18n")
    inner class ProductViewHolder(private val item: ItemProductBinding) :
        RecyclerView.ViewHolder(item.root) {

        private val format = DecimalFormat("#,###")
        fun bind(product: ProductList) {

            val valueFormat: String = format.format(product.price)

            item.lblPriceProduct.text = "$ $valueFormat"
            item.lblTitleProduct.text = product.title
            item.lblRatingProduct.text = product.rating.toString()
            item.lblStateProduct.isVisible = product.stock == 0
            item.imgItemProduct.load(product.thumbnail) {
                crossfade(true)
                transformations(CircleCropTransformation())
            }
            item.lyItemProduct.setOnClickListener {
                onItemClick?.invoke(product)
            }
        }
    }
}
