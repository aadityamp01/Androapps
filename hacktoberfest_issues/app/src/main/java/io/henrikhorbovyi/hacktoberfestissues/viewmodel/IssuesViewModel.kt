package io.henrikhorbovyi.hacktoberfestissues.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.henrikhorbovyi.hacktoberfestissues.data.issue.IssuesRepository
import io.henrikhorbovyi.hacktoberfestissues.ui.entity.SearchFilter
import io.henrikhorbovyi.hacktoberfestissues.ui.entity.issues.IssuesResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class IssuesViewModel(
    private val issuesRepository: IssuesRepository
) : ViewModel() {

    private val issues: MutableStateFlow<IssueViewState> =
        MutableStateFlow(IssueViewState.Loading)
    val issuesFlow: StateFlow<IssueViewState>
        get() = issues

    fun fetchIssues(filter: SearchFilter = SearchFilter()) {
        issues.value = IssueViewState.Loading

        viewModelScope.launch {
            try {
                val fetchedIssues = issuesRepository.fetch(filter)
                issues.value = IssueViewState.Success(fetchedIssues)
            } catch (e: Exception) {
                issues.value = IssueViewState.Failure(e)
            }
        }
    }
}

sealed class IssueViewState {
    object Loading : IssueViewState()
    data class Success(val issuesResponse: IssuesResponse) : IssueViewState()
    data class Failure(val throwable: Throwable) : IssueViewState()
}

fun IssueViewState.handle(
    onLoading: () -> Unit,
    onSuccess: (IssuesResponse) -> Unit,
    onFailure: (Throwable?) -> Unit
) {
    return when (this) {
        is IssueViewState.Loading -> onLoading()
        is IssueViewState.Success -> onSuccess(issuesResponse)
        is IssueViewState.Failure -> onFailure(throwable)
    }
}
