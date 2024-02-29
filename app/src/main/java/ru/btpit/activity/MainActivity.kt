package ru.btpit.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.btpit.R
import ru.btpit.databinding.ActivityMainBinding
import ru.btpit.dto.Post
import ru.btpit.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val post = Post(
            id = 1,
            author = "БТПИТ. Техникум профессий будущего!",
            content = "Привет, это БТПИТ! Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста, присоединяйтесь → https://btpit36.ru",
            published = "19 февраля в 15:31",
            likedByMe = false,
            sharedByMe = false
        )

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                like?.setOnClickListener{
                    Log.d("stuff", "like")
                    post.likedByMe = !post.likedByMe
                    like.setImageResource(
                        if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
                    )
                    if (post.likedByMe) post.likes++ else post.likes--
                    likeCount.text = post.likes.toString()
                }
                shareCount.text = post.share.toString()
                share.setOnClickListener{
                    Log.d("stuff", "share")
                    post.sharedByMe = !post.sharedByMe
                    if (post.sharedByMe) post.share++ else post.share--
                }
                if (post.sharedByMe){
                }
                shareCount.text = post.share.toString()

            }
        }
        binding.like.setOnClickListener {
            viewModel.like()
        }

        binding.share.setOnClickListener{
            viewModel.share()
        }
    }
}
