package com.jortega.dietaapp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jortega.dietaapp.dieta.DietFragment
import com.jortega.dietaapp.porciones.PortionsFragment
import com.jortega.dietaapp.porciones.Nutriente

class MainActivity : AppCompatActivity() {

    companion object {
        var nutList = listOf(
            Nutriente("Origen Animal"),
            Nutriente("Cereales"),
            Nutriente("Frutas"),
            Nutriente("Verduras"),
            Nutriente("Aceite c/Proteína"),
            Nutriente("Aceite s/Proteína"),
            Nutriente("Leguminosas"),
            Nutriente("Leche"),
            Nutriente("Azúcar"),
        )
    }

    private lateinit var sharedPreferences: SharedPreferences
    private val PREF_NAME = "ListaPorciones"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        initUI()
        bottomMenu()
    }

    override fun onPause() {
        super.onPause()
        saveList(nutList)
    }

    override fun onDestroy() {
        super.onDestroy()
        saveList(nutList)
    }

    //Iniciar UI
    private fun initUI() {
        nutList = getList()
    }

    //Cambiar fragment
    private fun bottomMenu() {
        val bottomMenu: BottomNavigationView = findViewById(R.id.bottom_menu)
        setCurrentFragment(PortionsFragment())
        bottomMenu.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.portions -> setCurrentFragment(PortionsFragment())
                R.id.diet -> setCurrentFragment(DietFragment())
            }
            true
        }
    }

    //Definir fragment actual
    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_avtivity, fragment)
            commit()
        }
    }

    //Guardar lista como Json
    private fun saveList(nutList: List<Nutriente>) {
        //val gson = Gson()
        val nutListJson = Gson().toJson(nutList)
        val editor = sharedPreferences.edit()
        editor.putString(PREF_NAME, nutListJson)
        editor.apply()
    }

    //Obtener lista Json
    private fun getList(): List<Nutriente> {
        val nutListJson = sharedPreferences.getString(PREF_NAME, null)
        //val gson = Gson()
        val nutListType = object : TypeToken<List<Nutriente>>() {}.type
        return Gson().fromJson(nutListJson, nutListType) ?: nutList
    }
}