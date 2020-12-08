package com.android.developerjobs.repository

import com.android.developerjobs.api.PostApiService

object JobRepositoryProvider {

    fun provideJobRepository(): JobRepository {
        return JobRepository(apiService = PostApiService.create())
    }
}