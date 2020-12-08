package com.android.developerjobs.api

import com.android.developerjobs.model.Comment
import com.android.developerjobs.model.Post
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://jsonplaceholder.typicode.com"

interface PostApiService {
    companion object Factory {
        fun create(): PostApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(PostApiService::class.java);
        }
    }

    @GET("/posts")
    fun getPosts(): Observable<List<Post>>

    @GET("/comments")
    fun getComments(@Query("postId") postId: Int): Observable<List<Comment>>

}