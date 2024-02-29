package ru.btpit.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.btpit.dto.Post

class PostRepositoryInMemoryImpl : PostRepository {
    private var post = Post(
        id = 1,
        author = "БТПИТ. Техникум профессий будущего!",
        content = "Привет, это БТПИТ! Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста, присоединяйтесь → https://btpit36.ru",
        published = "19 февраля в 15:31",
        likedByMe = false,
        sharedByMe = false
    )
    private val data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data
    override fun like() {
        post = post.copy(likedByMe = !post.likedByMe)
        data.value = post
    }
    override fun share() {
        post = post.copy(sharedByMe = !post.sharedByMe)
        data.value = post
    }
}