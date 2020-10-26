package com.osama.chatbotbasicapp.utils

import java.text.SimpleDateFormat
import java.util.*

object Time {

    fun getCurrentTime(): String {
        val currentTime = System.currentTimeMillis()
        val simpleDateFormat = SimpleDateFormat("HH:mm", Locale.US)
        return simpleDateFormat.format(Date(currentTime)).toString()
    }

}