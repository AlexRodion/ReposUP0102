package ru.netology.btpit.repository

import androidx.lifecycle.LiveData
import ru.netology.btpit.dto.Post

interface PostRepository {
    fun get(): LiveData<Post>
    fun like()
    fun share()

}