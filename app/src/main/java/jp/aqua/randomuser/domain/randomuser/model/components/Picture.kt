package jp.aqua.randomuser.domain.randomuser.model.components

import com.squareup.moshi.Json

data class Picture(
    @field:Json(name = LARGE)
    val large: String,
    @field:Json(name = MEDIUM)
    val medium: String,
    @field:Json(name = THUMBNAIL)
    val thumbnail: String
) {
    companion object {
        const val LARGE = "large"
        const val MEDIUM = "medium"
        const val THUMBNAIL = "thumbnail"
    }
}