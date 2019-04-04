package com.felipesilva.myseries.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Shows(
    @SerializedName("show") val show: Show
)

data class Show(
    @SerializedName("id") val id: Int,
    @SerializedName("image") val image: Image?,
    @SerializedName("name") val name: String,
    @SerializedName("genres") val genres: List<String>,
    @SerializedName("summary") val summary: String,
    @SerializedName("premiered") val premiered: Date

)

data class Image(
    @SerializedName("medium") val medium: String,
    @SerializedName("original") val original: String
)