package com.jortega.dietaapp.dieta

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.jortega.dietaapp.R

class DietViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private var cvDiet: CardView = view.findViewById(R.id.cv_diet)
    private var tvType: TextView = view.findViewById(R.id.tv_type)
    private var tvFood: TextView = view.findViewById(R.id.tv_food)
    private var tvDescription: TextView = view.findViewById(R.id.tv_description)

    fun render(diet: Diet, setDiet: (Int) -> Unit, markIsDone: (Int) -> Unit) {
        tvType.text = diet.type
        tvFood.text = diet.food
        tvDescription.text = diet.description

        cvDiet.setOnLongClickListener {
            setDiet(layoutPosition)
            true
        }
    }
}