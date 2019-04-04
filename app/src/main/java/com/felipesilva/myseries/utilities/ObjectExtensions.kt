package com.felipesilva.myseries.utilities

import java.text.SimpleDateFormat
import java.util.*

fun String.formatSummary() : String{
    val re = Regex("<.*?>")
    return this.replace(re, "")
}

fun Date.formatDate() : String {
    val format = SimpleDateFormat("dd/MM/yyyy")
    return format.format(this)
}

fun List<String>.formatGenres() : String {
    val formattedGenres = StringBuilder()

    if (this.isNotEmpty()) {
        this.forEachIndexed{ index, genre ->
            if(index != (this.size - 1))
                formattedGenres.append("$genre, ")
            else
                formattedGenres.append(genre)
        }
    } else
        formattedGenres.append("Not assigned")

    return formattedGenres.toString()
}