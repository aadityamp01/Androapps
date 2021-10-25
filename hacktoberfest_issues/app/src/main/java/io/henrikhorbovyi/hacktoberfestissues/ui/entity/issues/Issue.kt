package io.henrikhorbovyi.hacktoberfestissues.ui.entity.issues

import com.google.gson.annotations.SerializedName
import io.henrikhorbovyi.hacktoberfestissues.util.HUMAN_FORMAT
import io.henrikhorbovyi.hacktoberfestissues.util.ISO6801
import io.henrikhorbovyi.hacktoberfestissues.util.formatAsDate

data class Issue(
    val title: String,
    val labels: List<Label>,
    val state: String, // "open"
    val number: Int,

    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("created_at")
    val createdAt: String,
) {
    private fun formattedTime(): String {
        return createdAt.formatAsDate(ISO6801, HUMAN_FORMAT)
    }

    fun repositoryName(): String {
        return htmlUrl.split("/").slice(3..4).joinToString("/")
    }

    override fun toString(): String {
        return "#$number opened on ${formattedTime()}"
    }

    companion object {
        val mock = Issue(
            htmlUrl = "https://github.com/batterseapower/pinyin-toolkit/issues/132",
            labels = mutableListOf(),
            state = "open",
            title = "my issue",
            number = 1232,
            createdAt = ""
        )
    }
}
