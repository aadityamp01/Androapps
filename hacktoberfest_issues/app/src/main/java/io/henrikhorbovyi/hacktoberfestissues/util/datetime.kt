package io.henrikhorbovyi.hacktoberfestissues.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

const val ISO6801 = "yyyy-MM-dd\'T\'HH:mm:ss\'Z\'"
const val HUMAN_FORMAT = "MM/dd/yyyy \'at\' HH:mm"

fun String.formatAsDate(patternInput: String, patternOutput: String): String {
    val dateInput = SimpleDateFormat(patternInput, Locale("en"))
    val dateOutput = SimpleDateFormat(patternOutput, Locale("en"))
    return try {
        val date = dateInput.parse(this) ?: Date()
        dateOutput.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
        "-"
    }
}
/*
* Sample:
* "2021-10-24T14:32:12Z".formatAsDate(ISO6801, HUMAN_FORMAT)
* */

