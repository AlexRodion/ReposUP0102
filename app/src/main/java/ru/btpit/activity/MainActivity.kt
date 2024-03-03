package ru.btpit.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.launch
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import ru.btpit.R
import ru.btpit.adapter.OnInteractionListener
import ru.btpit.adapter.PostsAdapter
import ru.btpit.databinding.ActivityMainBinding
import ru.btpit.dto.Post
import ru.btpit.viewmodel.PostViewModel


class MainActivity : AppCompatActivity() {
    private var nextId = 1L
    private var posts = listOf(
        Post(
            id = nextId++,
            author = "БТПИТ. Техникум профессий будущего!",
            content = "Привет, это БТПИТ! Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста, присоединяйтесь → https://btpit36.ru",
            published = "19 февраля в 15:31",
            likedByMe = false
        ),
        Post(
            id = nextId++,
            author = "БТПИТ. Техниум профессий будущего!",
            content = " Приглашение на дизайн-лекторий → https://www.youtube.com/watch?v=WhWc3b3KhnY",
            published = "28 февраля в 11:01",
            likedByMe = false
        ),
        Post(
            id = nextId++,
            author = "БТПИТ. Техникум профессий будущего!",
            content = "Вот и наступил первый день весны!",
            published = "1 марта в 12:22",
            likedByMe = false
        )
    ).reversed()
    private val data = MutableLiveData(posts)
    fun getAll(): LiveData<List<Post>> = data
    @SuppressLint("ApplySharedPref")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()

            val adapter = PostsAdapter(object : OnInteractionListener {
                override fun onEdit(post: Post) {
                    viewModel.edit(post)
                }

                override fun onLike(post: Post) {
                    viewModel.likeById(post.id)
                }

                override fun onRemove(post: Post) {
                    viewModel.removeById(post.id)
                }

                override fun onShare(post: Post) {
                    val intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, post.content)
                        type = "text/plain"
                    }

                    val shareIntent =
                        Intent.createChooser(intent, getString(R.string.chooser_share_post))
                    startActivity(shareIntent)
                }

            })
            binding.list?.adapter = adapter
            viewModel.data .observe(this) { posts ->
                adapter.submitList(posts)
            }
        viewModel.edited.observe(this) { post ->
            if (post.id == 0L) {
                return@observe
            }
            val newPostLauncher = registerForActivityResult(NewPostResultContract()) { result ->
                result ?: return@registerForActivityResult
                viewModel.changeContent(result)
                viewModel.save()
            }
            with(binding.content) {
                this?.requestFocus()
                this?.setText(post.content)
            }
            binding.fab?.setOnClickListener {
                newPostLauncher.launch()
            }
        }

    }

}



