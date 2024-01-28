package jp.aqua.randomuser.domain.randomuser.model.components

import com.squareup.moshi.Json

data class DateAndAge(
    @field:Json(name = DATE)
    val date: String,
    @field:Json(name = AGE)
    val age: Int
) {
    companion object {
        const val DATE = "date"
        const val AGE = "age"
    }
}