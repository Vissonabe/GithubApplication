package com.viswa.network.model

import com.squareup.moshi.Json

data class RepoSearchResponse(
    @Json(name = "total_count") val total: Int = 0,
    @Json(name = "items") val items: List<Repo> = emptyList(),
    val nextPage: Int? = null
)