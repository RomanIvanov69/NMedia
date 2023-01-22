package ru.netology.nmedia

data class Post(
    val id: Long = 0L,
    val author: String = "",
    val content: String = "",
    val published: String = "",
    var likedByMe: Boolean = false,
    var likeCount: Int = 0,
    var shareCount: Int = 0,
    var lookCount: Int = 0
)

