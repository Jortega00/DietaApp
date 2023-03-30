package com.jortega.dietaapp.dieta

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.jortega.dietaapp.MainActivity
import com.jortega.dietaapp.R
import com.jortega.porciones.NutrienteAdapter


class DietFragment : Fragment(R.layout.fragment_diet) {

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
        menu()
    }

    private fun initComponents() {
    }

    private fun initUI() {
    }

    private fun menu() {
    }
}