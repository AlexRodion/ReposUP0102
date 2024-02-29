package ru.btpit.repository

import androidx.lifecycle.LiveData
import ru.btpit.dto.Post

interface PostRepository {
    fun get(): LiveData<Post>
    fun like()
    fun share()

}