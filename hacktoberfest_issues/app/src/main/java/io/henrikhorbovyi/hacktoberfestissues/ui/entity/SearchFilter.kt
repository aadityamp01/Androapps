package io.henrikhorbovyi.hacktoberfestissues.ui.entity

data class SearchFilter(
    val label: String = "hacktoberfest",
    val state: String = "open",
    val sort: String = "created", // created, updated, comments.
    val order: String = "desc", // asc or desc.
)
// e.g. label:hacktoberfest+state:open&sort=created&order=desc

fun SearchFilter.toQuery(): String {
    return "label%3A$label+state%3A$state+"
}