package ru.btpit.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import ru.btpit.R

class MainActivityPlain : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<MaterialButton>(R.id.like).setOnClickListener {
            if (it !is MaterialButton) {
                return@setOnClickListener
           }
            it.setIconResource(R.drawable.ic_liked_24)
       }

    }
}