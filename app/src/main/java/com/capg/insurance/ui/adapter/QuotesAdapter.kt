package com.capg.insurance.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capg.insurance.data.model.QuotesModel
import com.capg.insurance.databinding.InsuranceDetailsListBinding

class QuotesAdapter : RecyclerView.Adapter<QuotesAdapter.ProductViewHolder>() {

    var products = mutableListOf<QuotesModel>()

    fun setProductList(products: List<QuotesModel>) {
        this.products = products.toMutableList()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = InsuranceDetailsListBinding.inflate(inflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val prod = products[position]
        holder.binding.bankName.text = prod.name
        holder.binding.claimAmount.text = prod.claim
        holder.binding.priceButton.text = prod.price
//        Glide.with(holder.itemView.context).load(movie.imageUrl).into(holder.binding.imageview)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    inner class ProductViewHolder(val binding: InsuranceDetailsListBinding) : RecyclerView.ViewHolder(binding.root)

}