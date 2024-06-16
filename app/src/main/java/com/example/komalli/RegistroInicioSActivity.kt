package com.example.komalli

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.R
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.komalli.databinding.ActivityRegistroInicioSactivityBinding

class RegistroInicioSActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroInicioSactivityBinding
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityRegistroInicioSactivityBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        binding.iniciarSesionBtn.setOnClickListener {
        val ventanaInicioSesion = Intent(this@RegistroInicioSActivity, IniciarSesionActivity::class.java)
            startActivity(ventanaInicioSesion)
        }
        binding.registrarmeBtn.setOnClickListener {
            val ventanaRegistro = Intent(this@RegistroInicioSActivity, RegistroActivity::class.java)
            startActivity(ventanaRegistro)
        }
    }


}