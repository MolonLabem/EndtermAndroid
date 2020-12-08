package com.android.developerjobs.model

data class Comment(
    val postId: Int,
    val id: Int,
    val email: String,
    val name: String,
    val body: String
)