package com.capg.insurance.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.capg.insurance.R
import com.capg.insurance.data.model.ProductModel
import com.google.android.material.card.MaterialCardView

class GridViewAdapter(recyclerDataArrayList: ArrayList<ProductModel>, mcontext: Context, tag : String) :
    RecyclerView.Adapter<GridViewAdapter.RecyclerViewHolder>(){

    private val insuranceListModel: ArrayList<ProductModel> = recyclerDataArrayList
    private val mcontext: Context = mcontext
    val tag = tag

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {

        val view: View = if(tag == "show_list")
            LayoutInflater.from(parent.context).inflate(R.layout.insurance_list, parent, false)
        else
        {
            Log.e("_Adapter", "Setting details view")
            LayoutInflater.from(parent.context).inflate(R.layout.insurance_details_list, parent, false)
        }

        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        val listItem: ProductModel = insuranceListModel[position]


//        holder.insuranceImg.setImageResource(listItem.image)
        if(tag == "show_list"){
//            holder.insuranceName.text = listItem.insuranceCategoryName


            holder.insuranceCard.setOnClickListener(View.OnClickListener {
                Log.e("_ADAPTER", "Position : $position")
                it.findNavController().navigate(R.id.action_HomeFragment_to_NewFormFragment)
            })

        }
        else{

            holder.insuranceCard.setOnClickListener(View.OnClickListener {
                Log.e("_ADAPTER_Details", "Position : $position")
              //  var intent = Intent(mcontext, SecondActivity::class.java )
              //  mcontext.startActivity(intent)
            })
        }
    }

    override fun getItemCount(): Int {
        // this method returns the size of recyclerview
        return insuranceListModel.size
    }

    // View Holder Class to handle Recycler View.
   inner class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var insuranceName: TextView
        private lateinit var insuranceImg: ImageView
        lateinit var  insuranceCard : MaterialCardView

        init {

            if(tag == "show_list"){
                insuranceImg = itemView.findViewById(R.id.insuranceImage)
                insuranceName = itemView.findViewById(R.id.insuranceName)
                insuranceCard = itemView.findViewById(R.id.insurance_card)
            }
            else{
                insuranceImg = itemView.findViewById(R.id.iv_bank_logo)
                insuranceCard = itemView.findViewById(R.id.insurance_details)
            }

        }
    }
}
