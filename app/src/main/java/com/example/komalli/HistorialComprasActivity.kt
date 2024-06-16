package com.example.komalli

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.komalli.databinding.ActivityHistorialComprasBinding

class HistorialComprasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistorialComprasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityHistorialComprasBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnRegresarHc.setOnClickListener {
            val intent = Intent(this@HistorialComprasActivity, PlatilloActivity::class.java)
            startActivity(intent)
        }
    }
}