package com.osama.chatbotbasicapp.utils

import android.util.Log

object SolveMathEquation {

    fun solveEquation(equation: String): Int {
        equation.trim()
        Log.d("SolveMathEquation", "solveEquation: $equation")
        return when {
            equation.contains("+") -> {
                val splits = equation.split("+")
                splits[0].toInt() + splits[1].toInt()
            }

            equation.contains("*") -> {
                val splits = equation.split("*")
                splits[0].toInt() * splits[1].toInt()
            }

            equation.contains("/") -> {
                val splits = equation.split("/")
                splits[0].toInt() / splits[1].toInt()
            }

            equation.contains("-") -> {
                val splits = equation.split("-")
                splits[0].toInt() - splits[1].toInt()
            }

            else -> {
                0
            }
        }
    }

}