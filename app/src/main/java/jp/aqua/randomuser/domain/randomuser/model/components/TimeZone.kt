package jp.aqua.randomuser.domain.randomuser.model.components

import com.squareup.moshi.Json

data class TimeZone(
    @field:Json(name = OFFSET)
    val offset: String,
    @field:Json(name = DESCRIPTION)
    val description: String
) {
    companion object {
        const val OFFSET = "offset"
        const val DESCRIPTION = "description"
    }
}