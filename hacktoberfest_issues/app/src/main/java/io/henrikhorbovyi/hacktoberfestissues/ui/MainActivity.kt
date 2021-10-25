package io.henrikhorbovyi.hacktoberfestissues.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.henrikhorbovyi.hacktoberfestissues.ui.entity.issues.Issue
import io.henrikhorbovyi.hacktoberfestissues.ui.screen.IssuesScreen
import io.henrikhorbovyi.hacktoberfestissues.ui.theme.HacktoberfestIssuesTheme
import io.henrikhorbovyi.hacktoberfestissues.viewmodel.IssuesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : ComponentActivity() {

    private val issuesViewModel: IssuesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HacktoberfestIssuesTheme {

                val navController = rememberNavController()
                val initialScreen = "issues"

                NavHost(navController = navController, startDestination = initialScreen) {
                    composable("issues") {
                        IssuesScreen(issuesViewModel,
                        onIssueClicked = ::openIssueOnBrowser)
                    }
                }
            }
        }
    }

    private fun openIssueOnBrowser(issue: Issue) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(issue.htmlUrl)
        startActivity(intent)
    }
}
