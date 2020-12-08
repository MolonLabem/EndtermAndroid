package com.android.developerjobs.repository

import com.android.developerjobs.api.PostApiService

class JobRepository(private val apiService: PostApiService) {

    fun getJobs() = apiService.getPosts()

    fun getComments(id: Int) = apiService.getComments(id)
}