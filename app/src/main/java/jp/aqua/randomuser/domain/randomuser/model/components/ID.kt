package jp.aqua.randomuser.domain.randomuser.model.components

import com.squareup.moshi.Json

data class ID(
    @field:Json(name = NAME)
    val name: String,
    @field:Json(name = VALUE)
    val value: String
) {
    companion object {
        const val NAME = "name"
        const val VALUE = "value"
    }
}