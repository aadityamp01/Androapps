package io.henrikhorbovyi.hacktoberfestissues.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.henrikhorbovyi.hacktoberfestissues.ui.entity.issues.Issue

@Composable
fun IssueView(issue: Issue, onClicked: (Issue) -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable { onClicked(issue) },
        elevation = 8.dp
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                issue.title,
                style = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                issue.repositoryName(),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )
            )

            Spacer(modifier = Modifier.padding(top = 8.dp))
            Text(
                issue.toString(),
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            )
        }
    }
}


@Composable
@Preview("IssueViewPreview", showBackground = true, showSystemUi = true)
fun IssueViewPreview() {
    IssueView(issue = Issue.mock)
}