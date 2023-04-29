package com.jortega.dietaapp.dieta

data class Diet(val type: String,
                var food: String? = null,
                var description: String? = null,
                var isDone: Boolean = false)
