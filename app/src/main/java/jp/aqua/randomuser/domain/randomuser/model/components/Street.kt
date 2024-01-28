package jp.aqua.randomuser.domain.randomuser.model.components

import com.squareup.moshi.Json

data class Street(
    @field:Json(name = NUMBER)
    val number: Int,
    @field:Json(name = NAME)
    val name: String
) {
    companion object {
        const val NUMBER = "number"
        const val NAME = "name"
    }
}