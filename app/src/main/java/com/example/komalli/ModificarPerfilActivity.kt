package com.example.komalli

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.komalli.Modelo.UsuariosDB
import com.example.komalli.Poko.Usuario
import com.example.komalli.databinding.ActivityModificarPerfilBinding

class ModificarPerfilActivity : AppCompatActivity() {
    private lateinit var binding : ActivityModificarPerfilBinding
    private lateinit var usuariosDB: UsuariosDB
    lateinit var usuario : Usuario
    private lateinit var correo : String
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityModificarPerfilBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        correo = intent.getStringExtra("correo")!!
      //  Toast.makeText(this@ModificarPerfilActivity, "${correo}", Toast.LENGTH_LONG).show()
        usuariosDB = UsuariosDB(this@ModificarPerfilActivity)
        binding.btnRegresar.setOnClickListener {
            val intent = Intent(this@ModificarPerfilActivity, PlatilloActivity::class.java)
            startActivity(intent)
        }
        binding.btnModificarDatos.setOnClickListener {
            if(validarCampos()){
                val telefono = binding.etTelefonoPerfil.text.toString()
                val localidad = binding.etLocalidadPerfil.text.toString()
                val resultado = usuariosDB.actualizarDatosUsuario(correo,telefono,localidad)
                if(resultado>0){
                    Toast.makeText(this@ModificarPerfilActivity, "DATOS ACTUALIZADOS", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@ModificarPerfilActivity, PlatilloActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this@ModificarPerfilActivity, "ERROR AL ACTUALIZAR LOS DATOS", Toast.LENGTH_LONG).show()

                }
            }
        }
        cargarDatosUsuario()

    }

    fun validarCampos() : Boolean{
        var validado =false
        if(binding.etLocalidadPerfil.text.isEmpty() || binding.etTelefonoPerfil.text.isEmpty()){
            binding.etTelefonoPerfil.error=getString(R.string.error_modificar_datos)
            binding.etLocalidadPerfil.error=getString(R.string.error_modificar_datos)
        }else{
            validado=true
        }
        return validado
    }

    fun cargarDatosUsuario(){
        val usuario = usuariosDB.obtenerDatosUsuario(correo)
        binding.tvNombreUsuario.setText(usuario.nombre)
        binding.etLocalidadPerfil.setText(usuario.localidad)
        binding.etTelefonoPerfil.setText(usuario.telefono)
    }
}