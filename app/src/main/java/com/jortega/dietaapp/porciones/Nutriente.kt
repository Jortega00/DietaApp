package com.jortega.dietaapp.porciones

import java.io.Serializable

data class Nutriente(
    val type: String,
    var total: Double = 0.0,
    var current: Double = 0.0): Serializable {
}