package com.example.komalli

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.komalli.Modelo.ComentariosDB
import com.example.komalli.Poko.Comentario
import com.example.komalli.databinding.ActivityComentariosBinding

class ComentariosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityComentariosBinding
    private lateinit var comentariosDB: ComentariosDB

    private var correo = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityComentariosBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        comentariosDB = ComentariosDB(this@ComentariosActivity)
        correo=intent.getStringExtra("correo")!!
       // Toast.makeText(this@ComentariosActivity, "CORREO DEL USUARIO : ${correo}", Toast.LENGTH_SHORT).show()
        binding.btnAgregarComentario.setOnClickListener {
            validarCampos()
        }
        binding.btnRegresarComentarios.setOnClickListener{
            val intent = Intent(this@ComentariosActivity, PlatilloActivity::class.java)
            startActivity(intent)
        }
    }
    private fun validarCampos(){
        if(binding.etComentario.text.isEmpty()){
            binding.etComentario.error=getString(R.string.error_comentario)
        }else{
            val comentario = Comentario(null, correo, binding.etComentario.text.toString())
            val resultado = comentariosDB.agregarComentario(comentario)
            if(resultado>0){
                Toast.makeText(this@ComentariosActivity, "COMENTARIO INGRESADO CORRECTAMENTE", Toast.LENGTH_SHORT).show()
                binding.etComentario.text.clear()
            }else{
                Toast.makeText(this@ComentariosActivity, "ERROR AL AGREGAR COMENTARIO", Toast.LENGTH_SHORT).show()
            }
            }
        }
}