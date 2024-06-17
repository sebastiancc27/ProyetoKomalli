
package com.example.komalli

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.komalli.Modelo.UsuariosDB
import com.example.komalli.Poko.Usuario
import com.example.komalli.databinding.ActivityRegistroBinding

class RegistroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroBinding
    private lateinit var usuariosDB: UsuariosDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRegistroBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        usuariosDB= UsuariosDB(this)

        binding.btnRegistrarme.setOnClickListener {
            val nombre = binding.etNombre.text.toString()
            val apellido = binding.etApellido.text.toString()
            val localidad = binding.etLocalidad.text.toString()
            val correo = binding.etCorreo.text.toString()
            val contrasena = binding.etContrasena.text.toString()
            val telefono = binding.etTelefono.text.toString()

            if(validadCampos(nombre, apellido, localidad,correo,contrasena,telefono )==true){
                val usuario = Usuario(nombre, apellido,correo,telefono,localidad,contrasena)
                agregarUsuario(usuario)
            }
        }
        binding.homeBtnRegistro.setOnClickListener{
            val registroInicioActivity = Intent(this@RegistroActivity, RegistroInicioSActivity::class.java)
            startActivity(registroInicioActivity)
        }
    }

     fun validadCampos(nombre : String,  apellido: String,  localidad : String, correo : String, contrasena : String, telefono : String) : Boolean{
        var valido = false
         if((nombre.isNotEmpty() && apellido.isNotEmpty()) && (correo.isNotEmpty() && contrasena.isNotEmpty()) && (localidad.isNotEmpty() && telefono.isNotEmpty())){
             valido=true
         }else{
             if(nombre.isEmpty()){
                 binding.etNombre.error=getString(R.string.error_et_nombre)
             }
             if(apellido.isEmpty()){
                 binding.etApellido.error=getString(R.string.error_et_apellido)
             }
             if(localidad.isEmpty()){
                 binding.etLocalidad.error=getString(R.string.error_et_localidad)
             }
             if(correo.isEmpty()){
                 binding.etCorreo.error=getString(R.string.error_et_correo)
             }
             if(contrasena.isEmpty()){
                 binding.etContrasena.error=getString(R.string.error_et_contrasena)
             }
             if(telefono.isEmpty()){
                 binding.etContrasena.error=getString(R.string.error_et_telefono)
             }
         }
        return valido
    }
    private fun agregarUsuario (usuario : Usuario){
        val resultadoInseracion =usuariosDB.agregarUsuarios(usuario)
        var msj =""
        if(resultadoInseracion>0){
            msj="USUARIO AGREGADO CORRECTAMENTE"
            binding.etNombre.setText("")
            binding.etApellido.setText("")
            binding.etCorreo.setText("")
            binding.etLocalidad.setText("")
            binding.etContrasena.setText("")
            binding.etTelefono.setText("")
        }else{
            msj="ERROR AL AGREGAR USUARIO"
        }
        Toast.makeText(this@RegistroActivity, msj, Toast.LENGTH_LONG).show()
    }



}