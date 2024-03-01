package ru.btpit.activity

import android.os.Bundle
import android.widget.Toast
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.launch
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.btpit.R
import ru.btpit.adapter.OnInteractionListener
import ru.btpit.adapter.PostsAdapter
import ru.btpit.databinding.ActivityMainBinding
import ru.btpit.dto.Post
import ru.btpit.util.AndroidUtils
import ru.btpit.viewmodel.PostViewModel


class MainActivity : AppCompatActivity() {
    @SuppressLint("ApplySharedPref")
>>>>>>> 2a673b0103991e0d817263bacb5ed763b14d0347
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
<<<<<<< HEAD

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
            =======
            val post = Post(
                id = 1,
                author = "БТПИТ. Техникум профессий будущего!",
                content = "Привет, это БТПИТ! Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста, присоединяйтесь → https://btpit36.ru",
                published = "19 февраля в 15:31",
                likedByMe = false,
                sharedByMe = false
            )
            run
            {
                val preferences = getPreferences(Context.MODE_PRIVATE)
                preferences.edit().apply {
                    putString("key", "value") // putX
                    commit()
                }
            }

            val viewModel: ru.btpit.viewmodel.PostViewModel by viewModels()

            val adapter = PostsAdapter(object : OnInteractionListener {
                override fun onEdit(post: ru.btpit.dto.Post) {
                    viewModel.edit(post)
                }

                override fun onLike(post: ru.btpit.dto.Post) {
                    viewModel.likeById(post.id)
                }

                override fun onRemove(post: ru.btpit.dto.Post) {
                    viewModel.removeById(post.id)
                }

                override fun onShare(post: ru.btpit.dto.Post) {
                    val intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, post.content)
                        type = "text/plain"
                    }

                    val shareIntent =
                        Intent.createChooser(intent, getString(R.string.chooser_share_post))
                    startActivity(shareIntent)
                }
                >>>>>>> 2a673b0103991e0d817263bacb5ed763b14d0347
            })
            binding.list.adapter = adapter
            viewModel.data .observe(this)
            {
                posts ->
                adapter.submitList(posts)
            }

            viewModel.edited.observe(this)
            {
                post ->
                if (post.id == 0L) {
                    return@observe
                }
                with(binding.content) {
                    requestFocus()
                    setText(post.content)
                }
            }

            binding.save.setOnClickListener
            {
                with(binding.content) {
                    if (text.isNullOrBlank()) {
                        Toast.makeText(
                            this@MainActivity,
                            context.getString(R.string.error_empty_content),
                            Toast.LENGTH_SHORT
                        ).show()
                        return@setOnClickListener
                    }

                    viewModel.changeContent(text.toString())
                    viewModel.save()

                    setText("")
                    clearFocus()
                    AndroidUtils.hideKeyboard(this)
                }
                ====== =
                val newPostLauncher = registerForActivityResult(NewPostResultContract()) { result ->
                    result ?: return@registerForActivityResult
                    viewModel.changeContent(result)
                    viewModel.save()
                }

                binding.fab.setOnClickListener {
                    newPostLauncher.launch()
                }
            }
        }

