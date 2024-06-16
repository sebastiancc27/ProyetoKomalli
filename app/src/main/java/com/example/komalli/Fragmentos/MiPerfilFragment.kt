package com.example.komalli.Fragmentos

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.komalli.HistorialComprasActivity
import com.example.komalli.IniciarSesionActivity
import com.example.komalli.Modelo.UsuariosDB
import com.example.komalli.ModificarPerfilActivity
import com.example.komalli.R
import com.example.komalli.RegistroInicioSActivity


class MiPerfilFragment : Fragment() {
    private lateinit var correo : String
    private lateinit var usuariosDB: UsuariosDB

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mi_perfil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        correo = arguments?.getString("correo")!!
        usuariosDB = UsuariosDB(requireContext())

        val usuario = usuariosDB.obtenerDatosUsuario(correo)

        Toast.makeText(requireContext(), "${correo}", Toast.LENGTH_LONG).show()
        val btnModificarPerfil : Button = view.findViewById(R.id.btn_modificar_datos)
        val btnHistorialCompras : Button = view.findViewById(R.id.btn_historial_compras)
        val btnCerrarSesion : Button = view.findViewById(R.id.btn_cerrar_sesion)
        val btnEliminarPerfil : Button = view.findViewById(R.id.btn_eliminar_perfil)

        val tv_nombre_usuario : TextView = view.findViewById(R.id.tv_nombre_usuario_perfil)
        val tv_correo_usario : TextView = view.findViewById(R.id.tv_correo_usuario_mi_perfil)
        val tv_telefono_usuario : TextView = view.findViewById(R.id.tv_tel_usuario_mi_perfil)
        val tv_localidad_usario : TextView = view.findViewById(R.id.tv_localidad_usuario_mi_perfil)

        tv_nombre_usuario.setText(usuario.nombre)
        tv_correo_usario.setText(usuario.correo)
        tv_telefono_usuario.setText(usuario.telefono)
        tv_localidad_usario.setText(usuario.localidad)

        btnModificarPerfil.setOnClickListener {
            val intent = Intent(context, ModificarPerfilActivity::class.java)
            intent.putExtra("correo",correo)
            startActivity(intent)
        }
        btnHistorialCompras.setOnClickListener {
            val intent = Intent(context, HistorialComprasActivity::class.java)
            intent.putExtra("correo",correo)
            startActivity(intent)
        }
        btnCerrarSesion.setOnClickListener {
            val intent = Intent(activity, IniciarSesionActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
        btnEliminarPerfil.setOnClickListener {
            val resultado = usuariosDB.eliminarPerfil(correo)
            if(resultado>0){
                Toast.makeText(requireContext(), "Usuario eliminado correctamente ", Toast.LENGTH_SHORT).show()
                val intent = Intent(requireContext(), RegistroInicioSActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }else{
                Toast.makeText(requireContext(), "Error al eliminar el usuario ", Toast.LENGTH_SHORT).show()
            }

        }

    }
}