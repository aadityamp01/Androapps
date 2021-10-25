package io.henrikhorbovyi.hacktoberfestissues.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import io.henrikhorbovyi.hacktoberfestissues.ui.components.EmptyStateView
import io.henrikhorbovyi.hacktoberfestissues.ui.components.IssueView
import io.henrikhorbovyi.hacktoberfestissues.ui.entity.issues.Issue
import io.henrikhorbovyi.hacktoberfestissues.viewmodel.IssueViewState
import io.henrikhorbovyi.hacktoberfestissues.viewmodel.IssuesViewModel
import io.henrikhorbovyi.hacktoberfestissues.viewmodel.handle

@Composable
fun IssuesScreen(issuesViewModel: IssuesViewModel, onIssueClicked: (Issue) -> Unit) {

    val issueViewState: IssueViewState by issuesViewModel.issuesFlow.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(true)
    issuesViewModel.fetchIssues()

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = { issuesViewModel.fetchIssues() },
        indicator = { state, trigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = trigger,
                scale = true,
                contentColor = MaterialTheme.colors.primary,
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            LazyColumn(
                content = {
                    item { Header() }
                    issueViewState.handle(
                        onLoading = { swipeRefreshState.isRefreshing = true },
                        onSuccess = { response ->
                            swipeRefreshState.isRefreshing = false
                            items(response.issues) {
                                IssueView(issue = it, onClicked = onIssueClicked)
                            }
                        },
                        onFailure = { throwable ->
                            swipeRefreshState.isRefreshing = false
                            item {
                                Column(
                                    modifier = Modifier.fillParentMaxWidth().padding(top = 100.dp),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    EmptyStateView(
                                        throwable,
                                        onRetry = { issuesViewModel.fetchIssues() })
                                }
                            }
                        }
                    )
                }
            )
        }
    }
}

@Composable
fun Header() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 32.dp,
                bottom = 16.dp,
                start = 16.dp,
                end = 16.dp
            )
    ) {
        Text(
            text = "Hacktoberfest",
            style = TextStyle(
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = Color.DarkGray
            )
        )
        Text(
            text = "Issues",
            style = TextStyle(
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = Color.DarkGray
            )
        )
    }
}

@Composable
@Preview(
    name = "IssuesScreenPreview",
    showSystemUi = true,
    showBackground = true
)
fun IssuesScreenPreview() {
    // IssuesScreen(issuesViewModel = ..., onIssueClicked = ...)
}