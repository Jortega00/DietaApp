package com.jortega.dietaapp.porciones

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jortega.dietaapp.MainActivity.Companion.nutList
import com.jortega.dietaapp.R

class PortionsFragment : Fragment(R.layout.fragment_portions) {

    /*
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_portions, container, false)
    }
    */

    private lateinit var nutAdapter: NutrienteAdapter
    private lateinit var rvNuts: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()
        initUI()
        menu()
    }

    private fun initComponents() {
        rvNuts = requireView().findViewById(R.id.rv_nuts)
    }

    private fun initUI() {
        nutAdapter = NutrienteAdapter(nutList,
            { setTotal(it) },
            { subCurrent(it) },
            { plusCurrent(it) })
        rvNuts.layoutManager = LinearLayoutManager(context)
        rvNuts.adapter = nutAdapter
    }

    //Mostrar dialogo para setear el total
    private fun setTotal(position: Int) {
        val nut = nutList[position]

        val dialog = context?.let {
            Dialog(it).also { dialog ->
                dialog.setContentView(R.layout.item_alert)
            }
        }

        val tvNutrienteAlert: TextView = dialog!!.findViewById(R.id.tv_nutriente_alert)
        val btnSubAlert: Button = dialog.findViewById(R.id.btn_sub_alert)
        val btnPlusAlert: Button = dialog.findViewById(R.id.btn_plus_alert)
        val btnSave: Button = dialog.findViewById(R.id.btn_guardar)
        val tvValueAlert: TextView = dialog.findViewById(R.id.tv_value_alert)
        var total = nut.total

        tvNutrienteAlert.text = nut.type
        tvValueAlert.text = "$total"

        btnSubAlert.isEnabled = total != 0.0

        btnSubAlert.setOnClickListener {
            total -= 0.5
            tvValueAlert.text = "$total"
            if (total == 0.0)
                btnSubAlert.isEnabled = false
        }
        btnPlusAlert.setOnClickListener {
            total += 0.5
            tvValueAlert.text = "$total"
            btnSubAlert.isEnabled = true
        }
        btnSave.setOnClickListener {
            updateNutTotal(total, position)
            dialog.hide()
        }

        dialog.show()
    }

    //Actualizar el total
    private fun updateNutTotal(value: Double, position: Int) {
        nutList[position].total = value
        nutAdapter.notifyDataSetChanged()
    }

    //Disminuir valor actual
    private fun subCurrent(position: Int) {
        var current = nutList[position].current
        current -= 0.5
        updateCurrentValue(current, position)
    }

    //Aumentar valor actual
    private fun plusCurrent(position: Int) {
        var current = nutList[position].current
        current += 0.5
        updateCurrentValue(current, position)
    }

    //Actualizar el valor actual
    private fun updateCurrentValue(currentValue: Double, position: Int) {
        nutList[position].current = currentValue
        nutAdapter.notifyDataSetChanged()
    }

    //Reiniciar valor del dia
    private fun restartCurrent() {
        val alertDialog = context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle("Reiniciar")
                .setMessage("Reiniciar valores")
                .setPositiveButton("Si") { _, _ ->
                    for ((i, nut) in nutList.withIndex()){
                        nut.current = 0.0
                        updateCurrentValue(0.0, i)
                    }
                }
                .setNegativeButton("No") { _, _ ->
                    //closeContextMenu()
                }
        }

        alertDialog!!.show()
    }

    //Reiniciar todo
    private fun restartAll() {
        val alertDialog = context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle("Borrar")
                .setMessage("Borrar todos los valores")
                .setPositiveButton("Si") { _, _ ->
                    for ((i, nut) in nutList.withIndex()){
                        nut.current = 0.0
                        nut.total = 0.0
                        updateCurrentValue(0.0, i)
                        updateNutTotal(0.0, i)
                    }
                }
                .setNegativeButton("No") { _, _ ->
                    //closeContextMenu()
                }
        }

        alertDialog!!.show()
    }

    //Menu
    private fun menu() {
        val topAppBar: MaterialToolbar = requireView().findViewById(R.id.topAppBar)
        topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.option_1 -> {
                    restartCurrent()
                    true
                }
                R.id.option_2 -> {
                    restartAll()
                    true
                }
                R.id.option_3 -> {
//                    Intent(this, ImageActivity::class.java).also {
//                        startActivity(it)
//                    }
                    Toast.makeText(context, "Imagen jaja", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }
}