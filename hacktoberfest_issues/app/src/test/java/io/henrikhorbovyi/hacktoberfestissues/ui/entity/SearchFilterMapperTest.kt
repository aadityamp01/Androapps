package io.henrikhorbovyi.hacktoberfestissues.ui.entity

import org.junit.Test

class SearchFilterMapperTest {

    private val defaultExpectedMap: String = "label:hacktoberfest+state:open"


    @Test
    fun `maps properly a default search filter object`() {
        val mappedSearchFilter = SearchFilter().toQuery()

        assert(mappedSearchFilter == defaultExpectedMap)
    }
}