package com.felipesilva.myseries.data

import java.util.*

data class Shows(
    val show: Show
)

data class Show(
    val image: Image?,
    val name: String,
    val genres: MutableList<String>,
    val summary: String,
    val premiered: Date

)

data class Image(
    val medium: String,
    val original: String
)