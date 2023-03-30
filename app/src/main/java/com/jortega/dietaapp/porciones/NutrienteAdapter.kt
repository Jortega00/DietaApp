package com.jortega.porciones

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jortega.dietaapp.R

class NutrienteAdapter(private var nuts: List<Nutriente>,
                       private val dialog: (Int) -> Unit,
                       private val sub: (Int) -> Unit,
                       private val plus: (Int) -> Unit,):
    RecyclerView.Adapter<NutrienteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NutrienteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_nutriente, parent, false)
        return NutrienteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NutrienteViewHolder, position: Int) {
        holder.render(nuts[position], dialog, sub, plus)
        holder.itemView
    }

    override fun getItemCount() = nuts.size
}