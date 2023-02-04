package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import ru.netology.nmedia.dto.Post

interface PostRepository {
    fun getAll(): LiveData<List<Post>>
    fun share(id: Long)
    fun heart(id: Long)
    fun eyes(id: Long)
    fun save(post: Post)
    fun removeById(id: Long)
    fun likeById(id: Long)
}
