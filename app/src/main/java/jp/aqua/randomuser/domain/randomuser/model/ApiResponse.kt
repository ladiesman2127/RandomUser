package jp.aqua.randomuser.domain.randomuser.model

import com.squareup.moshi.Json
import jp.aqua.randomuser.domain.randomuser.model.components.Info
import jp.aqua.randomuser.domain.randomuser.model.components.UserData

data class ApiResponse(
    @field:Json(name = RESULTS)
    val results: List<UserData>,
    @field:Json(name = INFO)
    val info: Info
) {
    companion object {
        const val RESULTS = "results"
        const val INFO = "info"
    }
}