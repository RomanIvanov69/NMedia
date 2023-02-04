package ru.netology.nmedia.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryInMemoryImpl

private val emptyPost = Post(
    id = 0,
    author = " ",
    published = " ",
    content = " ",
    likedByMe = false,
)

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    val edited = MutableLiveData(emptyPost)
    fun share(id: Long) = repository.share(id)
    fun eyes(id: Long) = repository.eyes(id)
    fun heart(id: Long) = repository.heart(id)
    fun removeById(id: Long) = repository.removeById(id)
    fun likeById(id: Long) {
        repository.likeById(id)
    }

    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = emptyPost
    }

    fun edit(post: Post) {
        edited.value = post
    }

    fun notEdit() {
        edited.value?.let {
            edited.value = emptyPost
        }
    }

    fun editContent(content: String) {
        val text = content.trim()
        if (edited.value?.content == text) {
            return
        }
        edited.value = edited.value?.copy(content = text)
    }
}
