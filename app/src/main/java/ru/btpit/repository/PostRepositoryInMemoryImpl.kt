package ru.btpit.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.btpit.dto.Post

class PostRepositoryInMemoryImpl : PostRepository {
    private var nextId = 1L
    private var posts = listOf(
        Post(
            id = 1,
            author = "БТПИТ. Техникум профессий будущего!",
            content = "Привет, это БТПИТ! Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста, присоединяйтесь → https://btpit36.ru",
            published = "19 февраля в 15:31",
            likedByMe = false
        ),
        Post(
            id = 2,
            author = "БТПИТ. Техниум профессий будущего!",
            content = " Приглашение на дизайн-лекторий → https://www.youtube.com/watch?v=WhWc3b3KhnY",
            published = "28 февраля в 11:01",
            likedByMe = false
        ),
        Post(
            id = 3,
            author = "БТПИТ. Техникум профессий будущего!",
            content = "Вот и наступил первый день весны!",
            published = "1 марта в 12:22",
            likedByMe = false
        )
    ).reversed()

    private val data = MutableLiveData(posts)
    override fun getAll(): LiveData<List<Post>> = data

    override fun save(post: Post) {
        if (post.id == 0L) {
            // TODO: remove hardcoded author & published
            posts = listOf(
                post.copy(
                    id = nextId++,
                    author = "Я",
                    likedByMe = false,
                    published = "сейчас"
                )
            ) + posts
            data.value = posts
            return
        }

        posts = posts.map {
            if (it.id != post.id) it else it.copy(content = post.content)
        }
        data.value = posts
    }
    override fun likeById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(
                likedByMe = !it.likedByMe,
                likes = if (it.likedByMe) it.likes - 1 else it.likes + 1
            )
        }
        data.value = posts
    }

    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        data.value = posts
    }
}