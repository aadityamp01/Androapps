package io.henrikhorbovyi.hacktoberfestissues.ui.entity.issues

import com.google.gson.annotations.SerializedName

data class IssuesResponse(
    @SerializedName("items")
    val issues: List<Issue> = mutableListOf(),
)