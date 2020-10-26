package com.osama.chatbotbasicapp.utils

import android.util.Log
import com.osama.chatbotbasicapp.utils.Constants.OPEN_GOOGLE
import com.osama.chatbotbasicapp.utils.Constants.OPEN_SEARCH
import java.util.*

object BotResponse {

    fun basicsResponse(message: String): String {
        message.toLowerCase(Locale.getDefault())
        val randomInt = (0..2).random()

        return when {
            message == "Hello" -> {
                when (randomInt) {
                    0 -> "Hello nice to hear from you"
                    1 -> "Bonjour"
                    2 -> "Hello world!!!"
                    else -> "Error"
                }
            }

            message == "Salam" -> {
                "Waalaykom a salam :)"
            }

            message == "How are you ?" || message == "How are you" -> {
                when (randomInt) {
                    0, 1 -> "Fine thank you"
                    2 -> "Fine thank you"
                    else -> "Error"
                }
            }

            message.contains("time") && message.contains("?") -> {
                "The time is \n".plus(Time.getCurrentTime())
            }

            message.contains("open") && message.contains("google") -> {
                OPEN_GOOGLE
            }

            message.contains("search") -> {
                OPEN_SEARCH
            }

            message.contains("solve") -> {
                val equation: String = message.substringAfter("solve")

                return try {
                    SolveMathEquation.solveEquation(equation).toString()
                } catch (e: Exception) {
                    Log.d("BotResponse", "basicsResponse: ${e.message}")
                    "Error, I can't solve this"
                }
            }

            message.contains("flip") && message.contains("coin") -> {
                return when ((0..1).random()) {
                    0 -> "heads"
                    1 -> "nails"
                    else -> "Error"
                }
            }

            else -> {
                "Sorry I am a little dummy I can't respond to this message :'("
            }
        }

    }

}