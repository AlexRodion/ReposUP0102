package ru.netology.btpit.viewmodel

import androidx.lifecycle.ViewModel
import ru.netology.btpit.repository.PostRepository
import ru.netology.btpit.repository.PostRepositoryInMemoryImpl

class PostViewModel : ViewModel() {

    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.get()
    fun like() = repository.like()
    fun share() = repository.share()
}