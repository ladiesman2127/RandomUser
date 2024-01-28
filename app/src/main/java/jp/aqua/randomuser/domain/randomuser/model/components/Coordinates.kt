package jp.aqua.randomuser.domain.randomuser.model.components

import com.squareup.moshi.Json

data class Coordinates(
    @field:Json(name = LATITUDE)
    val latitude: String,
    @field:Json(name = LONGITUDE)
    val longitude: String,
) {
    companion object {
        const val LATITUDE = "latitude"
        const val LONGITUDE = "longitude"
    }
}