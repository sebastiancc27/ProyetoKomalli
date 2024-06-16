package com.example.komalli

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.komalli.databinding.ActivityModificarPerfilBinding

class ModificarPerfilActivity : AppCompatActivity() {
    private lateinit var binding : ActivityModificarPerfilBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityModificarPerfilBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.btnModificarDatos.setOnClickListener {
            val intent = Intent(this@ModificarPerfilActivity, PlatilloActivity::class.java)
            startActivity(intent)
        }
    }
}