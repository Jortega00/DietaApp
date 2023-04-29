package com.jortega.dietaapp.dieta

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.jortega.dietaapp.R


class DietFragment : Fragment(R.layout.fragment_diet) {

    private val BREAKFAST = "Desayuno"
    private val MEAL = "Comida"
    private val DINNER = "Cena"
    private val SNACK = "Colación"

    private val dietList = listOf(
        Diet(BREAKFAST),
        Diet(SNACK),
        Diet(MEAL),
        Diet(SNACK),
        Diet(DINNER)
    )

    private lateinit var dietAdapter: DietAdapter
    private lateinit var rvDiet: RecyclerView

    /*
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_diet, container, false)
    }
    */

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()
        initUI()
        //menu()
    }

    private fun initComponents() {
        rvDiet = requireView().findViewById(R.id.rv_diet)
    }

    private fun initUI() {
        dietAdapter = DietAdapter(dietList,
            { setDiet(it) },
            { markIsDone(it) })
        rvDiet.layoutManager = LinearLayoutManager(context)
        rvDiet.adapter = dietAdapter
    }

    //Setear dieta
    private fun setDiet(position: Int) {
        val diet = dietList[position]

        val dialog = context?.let {
            Dialog(it).also { dialog ->
                dialog.setContentView(R.layout.item_alert_diet)
            }
        }

        val tvType: TextView = dialog!!.findViewById(R.id.tv_diet_type)
        val etFood: TextInputEditText = dialog.findViewById(R.id.et_food)
        val etDescription: TextInputEditText = dialog.findViewById(R.id.et_description)
        val btnSave: Button = dialog.findViewById(R.id.btn_save_diet)

        tvType.text = diet.type

        btnSave.setOnClickListener {
            diet.food = etFood.text.toString()
            diet.description = etDescription.text.toString()
            dietAdapter.notifyItemChanged(position)
            dialog.hide()
        }

        dialog.show()
    }

    private fun markIsDone(position: Int) {
        dietList[position].isDone = !dietList[position].isDone
        dietAdapter.notifyItemChanged(position)
    }

    private fun menu() {
    }
}