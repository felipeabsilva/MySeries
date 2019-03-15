package com.felipesilva.myseries.data

data class Shows(
    val show: Show
)

data class Show(
    val name: String,
    val genres: MutableList<String>,
    val image: Image?
)

data class Image(
    val medium: String,
    val original: String
)
