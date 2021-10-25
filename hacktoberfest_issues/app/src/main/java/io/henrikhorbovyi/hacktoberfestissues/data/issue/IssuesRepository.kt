package io.henrikhorbovyi.hacktoberfestissues.data.issue

import io.henrikhorbovyi.hacktoberfestissues.ui.entity.SearchFilter
import io.henrikhorbovyi.hacktoberfestissues.ui.entity.issues.IssuesResponse
import io.henrikhorbovyi.hacktoberfestissues.ui.entity.toQuery

class IssuesRepository(
    private val service: IssueService
) {

    suspend fun fetch(searchFilter: SearchFilter): IssuesResponse {
        return service.fetch(
            searchFilter.toQuery(),
            searchFilter.sort,
            searchFilter.order
        )
    }
}