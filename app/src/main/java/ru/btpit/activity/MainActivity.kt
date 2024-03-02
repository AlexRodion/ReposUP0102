package ru.btpit.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.launch
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import ru.btpit.R
import ru.btpit.adapter.OnInteractionListener
import ru.btpit.adapter.PostsAdapter
import ru.btpit.databinding.ActivityMainBinding
import ru.btpit.dto.Post
import ru.btpit.viewmodel.PostViewModel


class MainActivity : AppCompatActivity() {
    @SuppressLint("ApplySharedPref")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        run {
            val preferences = getPreferences(Context.MODE_PRIVATE)
            preferences.edit().apply {
                putString("key", "Error") // putX
                commit() // commit - синхронно, apply - асинхронно
            }
        }

       run {
            getPreferences(Context.MODE_PRIVATE)
                .getString("key", "no value")?.let {
                    Snackbar.make(binding.root, it, BaseTransientBottomBar.LENGTH_INDEFINITE)
                        .show()
                }
        }



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



