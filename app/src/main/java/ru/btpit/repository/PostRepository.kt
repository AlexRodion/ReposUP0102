package ru.btpit.repository

import androidx.lifecycle.LiveData
import ru.btpit.dto.Post

interface PostRepository {
    fun getAll(): LiveData<List<Post>>
    fun likeById(id: Long)
    fun save(post: Post)
    fun getAll(): LiveData<List<ru.btpit.dto.Post>>
    fun likeById(id: Long)
    fun save(post: ru.btpit.dto.Post)
    fun removeById(id: Long)
}