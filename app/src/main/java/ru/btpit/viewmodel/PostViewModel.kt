package ru.btpit.viewmodel

import androidx.lifecycle.ViewModel
import ru.btpit.repository.PostRepository
import ru.btpit.repository.PostRepositoryInMemoryImpl

class PostViewModel : ViewModel() {

    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.get()
    fun like() = repository.like()
    fun share() = repository.share()
}