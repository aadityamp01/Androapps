package io.henrikhorbovyi.hacktoberfestissues.data.issue

import io.henrikhorbovyi.hacktoberfestissues.ui.entity.issues.IssuesResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.QueryMap
import retrofit2.http.QueryName

interface IssueService {

    @Headers("accept: application/vnd.github.v3+json")
    @GET("search/issues")
    suspend fun fetch(
        @Query("q") query: String,
        @Query("sort") sort: String,
        @Query("order") order: String,
    ): IssuesResponse
}
