package jp.aqua.randomuser.domain.randomuser.model.components

import com.squareup.moshi.Json

data class Name(
    @field:Json(name = TITLE)
    val title: String,
    @field:Json(name = FIRST)
    val first: String,
    @field:Json(name = LAST)
    val last: String,
) {
    companion object {
        const val TITLE = "title"
        const val FIRST = "first"
        const val LAST = "last"
    }
}