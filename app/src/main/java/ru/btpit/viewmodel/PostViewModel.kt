package ru.btpit.viewmodel

<<<<<<< HEAD
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.btpit.dto.Post
import ru.btpit.repository.PostRepository
import ru.btpit.repository.PostRepositoryInMemoryImpl

private val empty = Post(
=======
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.btpit.repository.PostRepositoryFileImpl
import ru.btpit.repository.PostRepository
import ru.btpit.dto.Post

private val empty = ru.btpit.dto.Post(
>>>>>>> 2a673b0103991e0d817263bacb5ed763b14d0347
    id = 0,
    content = "",
    author = "",
    likedByMe = false,
<<<<<<< HEAD
    published = ""
)

class PostViewModel : ViewModel() {
    // упрощённый вариант
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    val edited = MutableLiveData(empty)
=======
    likes = 0,
    published = "",
    sharedByMe = false
)

class PostViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ru.btpit.repository.PostRepository = PostRepositoryFileImpl(application)
    val data = repository.getAll()
    val edited = MutableLiveData(ru.btpit.viewmodel.empty)
>>>>>>> 2a673b0103991e0d817263bacb5ed763b14d0347

    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
    }

    fun edit(post: Post) {
        edited.value = post
    }

    fun changeContent(content: String) {
        val text = content.trim()
        if (edited.value?.content == text) {
            return
        }
        edited.value = edited.value?.copy(content = text)
    }

    fun likeById(id: Long) = repository.likeById(id)
    fun removeById(id: Long) = repository.removeById(id)
}
