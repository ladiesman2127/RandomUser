package jp.aqua.randomuser.domain.randomuser.model.components

import com.squareup.moshi.Json


data class Location(
    @field:Json(name = STREET)
    val street: Street,
    @field:Json(name = STATE)
    val state: String,
    @field:Json(name = CITY)
    val city: String,
    @field:Json(name = COUNTRY)
    val country: String,
    @field:Json(name = POSTCODE)
    val postcode: String,
    @field:Json(name = COORDINATES)
    val coordinates: Coordinates,
    @field:Json(name = TIME_ZONE)
    val timezone: TimeZone,
) {
    companion object {
        const val STREET = "street"
        const val STATE = "state"
        const val CITY = "city"
        const val COUNTRY = "country"
        const val POSTCODE = "postcode"
        const val COORDINATES = "coordinates"
        const val TIME_ZONE = "timezone"
    }
}