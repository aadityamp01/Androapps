package io.henrikhorbovyi.hacktoberfestissues.di

import io.henrikhorbovyi.hacktoberfestissues.data.issue.IssueService
import io.henrikhorbovyi.hacktoberfestissues.data.issue.IssuesRepository
import io.henrikhorbovyi.hacktoberfestissues.data.remote.ServiceBuilder
import io.henrikhorbovyi.hacktoberfestissues.viewmodel.IssuesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    viewModel { IssuesViewModel(get()) }

    factory { IssuesRepository(get()) }

    factory { ServiceBuilder<IssueService>("https://api.github.com/") }
}