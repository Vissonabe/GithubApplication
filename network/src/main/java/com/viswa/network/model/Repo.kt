package com.viswa.network.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "repos")
data class Repo(@PrimaryKey @field:Json(name = "id") val id: Long,
                @field:Json(name = "name") val name: String? = null,
                @field:Json(name = "full_name") val fullName: String,
                @field:Json(name = "description") val description: String? = null,
                @field:Json(name = "stargazers_count") val stars: Int,
                @field:Json(name = "forks_count") val forks: Int,
                @field:Json(name = "html_url") val url: String? = null,
                @field:Json(name = "language") val language: String? = null,
                @field:Json(name = "clone_url") val cloneUrl: String? = null)