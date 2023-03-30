package com.jortega.porciones

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.jortega.dietaapp.R

class NutrienteViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val cvNutriente: CardView = view.findViewById(R.id.cv_nutriente)
    private val cvCurrent: CardView = view.findViewById(R.id.cv_current)
    private val tvNutriente: TextView = view.findViewById(R.id.tv_nutriente)
    private val tvRest: TextView = view.findViewById(R.id.tv_rest)
    private val tvValue: TextView = view.findViewById(R.id.tv_value)
    private val btnSub: Button = view.findViewById(R.id.btn_sub)
    private val btnPlus: Button = view.findViewById(R.id.btn_plus)

    fun render(nut: Nutriente,
               dialog: (Int) -> Unit,
               sub: (Int) -> Unit,
               plus: (Int) -> Unit,) {

        changeColor(nut)

        tvNutriente.text = nut.type
        tvRest.text = "Restante: ${nut.total - nut.current}"
        tvValue.text = "${nut.current}"

        cvNutriente.setOnLongClickListener {
            dialog(layoutPosition)
            true
        }

        btnSub.isEnabled = nut.current != 0.0
        btnPlus.isEnabled = nut.total > 0.0

        btnSub.setOnClickListener {
            sub(layoutPosition)
            tvRest.text = "Restante: ${nut.total - nut.current}"
            tvValue.text = "${nut.current}"
        }
        btnPlus.setOnClickListener {
            plus(layoutPosition)
            tvRest.text = "Restante: ${nut.total - nut.current}"
            tvValue.text = "${nut.current}"
        }
    }

    private fun changeColor(nut: Nutriente) {
        var cardColor: Int
        var btnColor: Int

        if (nut.total == 0.0) {
            cardColor = R.color.card_disabled
            btnColor = R.color.card_disabled_btn
        }
        else {
            cardColor = R.color.card
            btnColor = R.color.card_btn
        }

        if (nut.total < nut.current) {
            cardColor = R.color.card_over_limit
            btnColor = R.color.card_over_limit_btn
        }

        cvNutriente.setCardBackgroundColor(ContextCompat.getColor(cvNutriente.context, cardColor))
        cvCurrent.setCardBackgroundColor(ContextCompat.getColor(cvCurrent.context, btnColor))
    }
}