package br.com.harlan.avaliabus.util

import java.text.SimpleDateFormat
import java.util.*

object CurrentDateTime {
    val currentDate: String
        get() {
            val currentDateTime: String
            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
            val date = Calendar.getInstance().time
            currentDateTime = simpleDateFormat.format(date)
            return currentDateTime
        }
    val currentTime: String
        get() {
            val currentDateTime: String
            val simpleDateFormat = SimpleDateFormat("HH:mm")
            val date = Calendar.getInstance().time
            currentDateTime = simpleDateFormat.format(date)
            return currentDateTime
        }
}