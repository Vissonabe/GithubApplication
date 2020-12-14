package com.viswa.network.service

import com.viswa.network.model.Repo
import com.viswa.network.model.RepoSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IGithubService {
    @GET("orgs/{org}/repos")
    suspend fun getRepos(@Path("org") org : String) : List<Repo>

    @GET("search/repositories?sort=stars")
    suspend fun searchRepos(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): RepoSearchResponse
}