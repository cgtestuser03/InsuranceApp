package com.capg.insurance.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.capg.insurance.R
import com.capg.insurance.data.model.ProductModel
import com.capg.insurance.databinding.InsuranceListBinding

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    var products = mutableListOf<ProductModel>()

    fun setProductList(products: List<ProductModel>) {
        this.products = products.toMutableList()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = InsuranceListBinding.inflate(inflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val prod = products[position]
        holder.binding.insuranceName.text = prod.name
        holder.binding.insuranceDescription.text = prod.desc
//        Glide.with(holder.itemView.context).load(movie.imageUrl).into(holder.binding.imageview)

        holder.binding.insuranceCard.setOnClickListener(View.OnClickListener {
            Log.e("_ADAPTER", "Position : $position")
            it.findNavController().navigate(R.id.action_HomeFragment_to_NewFormFragment)
        })

    }

    override fun getItemCount(): Int {
        return products.size
    }

    inner class ProductViewHolder(val binding: InsuranceListBinding) : RecyclerView.ViewHolder(binding.root)

}

