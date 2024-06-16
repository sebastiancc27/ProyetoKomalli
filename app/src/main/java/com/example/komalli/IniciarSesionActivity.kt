package com.example.komalli

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.komalli.Modelo.UsuariosDB
import com.example.komalli.databinding.ActivityIniciarSesionBinding

class IniciarSesionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIniciarSesionBinding
    private lateinit var usuariosDB: UsuariosDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityIniciarSesionBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        usuariosDB = UsuariosDB(this@IniciarSesionActivity)

        binding.homeBtn.setOnClickListener{
            val registroIncioActivity = Intent(this@IniciarSesionActivity, RegistroInicioSActivity::class.java)
            startActivity(registroIncioActivity)
        }
        binding.btnIniciarSesion.setOnClickListener {
            val correo = binding.etCorreo.text.toString()
            val contrasena = binding.etContrasena.text.toString()
            if(datosValidos(correo,contrasena)==true){
                if(usuariosDB.validadUsuario(correo,contrasena)==true){
                    Toast.makeText(this@IniciarSesionActivity, "DATOS VALIDADOS", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@IniciarSesionActivity, PlatilloActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this@IniciarSesionActivity, "DATOS INVALIDOS", Toast.LENGTH_LONG).show()
                }
            }
        }

    }
    private fun datosValidos( correo : String, contrasena : String) : Boolean{
        var esValido =  true
        if(correo.isEmpty()){
           binding.etCorreo.error="Campo de correo requerido"
           esValido=false
        }

        if(contrasena.isEmpty()){
            binding.etContrasena.error="Campo de correo requerido"
            esValido=false
        }

        return esValido
    }

}