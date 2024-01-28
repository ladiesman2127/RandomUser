package jp.aqua.randomuser.domain.randomuser.model.components

import com.squareup.moshi.Json

data class Info(
    @field:Json(name = SEED)
    val seed: String,
    @field:Json(name = RESULTS)
    val results: Int,
    @field:Json(name = PAGE)
    val page: Int,
    @field:Json(name = VERSION)
    val version: String
) {
    companion object {
        const val SEED = "seed"
        const val RESULTS = "results"
        const val PAGE = "page"
        const val VERSION = "version"
    }
}



















