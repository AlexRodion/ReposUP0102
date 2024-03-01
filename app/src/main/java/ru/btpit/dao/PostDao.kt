package ru.btpit.dao

import ru.btpit.dto.Post
interface PostDao {
    fun getAll(): List<Post>
    fun save(post: Post): Post
    fun likeById(id: Long)
    fun removeById(id: Long)
}