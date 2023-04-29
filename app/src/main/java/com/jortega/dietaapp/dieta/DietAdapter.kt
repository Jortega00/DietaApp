package com.jortega.dietaapp.dieta

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jortega.dietaapp.R

class DietAdapter(private var dietList: List<Diet>,
                  private val setDiet: (Int) -> Unit,
                  private val markIsDone: (Int) -> Unit):
    RecyclerView.Adapter<DietViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DietViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_diet, parent, false)
        return DietViewHolder(view)
    }

    override fun onBindViewHolder(holder: DietViewHolder, position: Int) {
        holder.render(dietList[position], setDiet, markIsDone)
        holder.itemView
    }

    override fun getItemCount() = dietList.size
}