package ru.netology.nmedia.repository

import androidx.lifecycle.ViewModel

class PostViewModel: ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.get()
    fun eyes() = repository.eyes()
    fun share() = repository.share()
    fun heart() = repository.heart()
}
