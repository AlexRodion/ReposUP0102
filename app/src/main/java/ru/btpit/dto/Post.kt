package ru.btpit.dto

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likedByMe: Boolean,
    var share: Int = 0,
    var likes: Int = 0,
    var sharedByMe: Boolean
)

