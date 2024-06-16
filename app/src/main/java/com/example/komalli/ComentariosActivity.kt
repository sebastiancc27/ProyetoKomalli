package com.example.komalli

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.komalli.databinding.ActivityComentariosBinding

class ComentariosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityComentariosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityComentariosBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}