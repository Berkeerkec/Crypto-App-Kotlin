package com.berkesoft.retrofitkotlin.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.berkesoft.retrofitkotlin.databinding.RecyclerRowBinding
import com.berkesoft.retrofitkotlin.model.CyrptoModel

class CyrptoAdapter (val cyrptoModels : ArrayList<CyrptoModel>) : RecyclerView.Adapter<CyrptoAdapter.CyrptoHolder>() {
    private val colors : ArrayList<String> = arrayListOf("#1B4F72","#E74C3C","#239B56","#F1C40F","#48C9B0","#AF7AC5","#D35400","#2C3E50")
class CyrptoHolder(val binding : RecyclerRowBinding) : ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CyrptoHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CyrptoHolder(binding)
    }

    override fun onBindViewHolder(holder: CyrptoHolder, position: Int) {
        holder.binding.nameText.text = cyrptoModels.get(position).currency
        holder.binding.priceText.text = cyrptoModels.get(position).price
        holder.itemView.setBackgroundColor(Color.parseColor(colors[position%8]))
    }

    override fun getItemCount(): Int {
        return cyrptoModels.size
    }

}