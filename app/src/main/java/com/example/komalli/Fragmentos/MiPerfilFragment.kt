package com.example.komalli.Fragmentos

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.komalli.HistorialComprasActivity
import com.example.komalli.IniciarSesionActivity
import com.example.komalli.ModificarPerfilActivity
import com.example.komalli.R
import com.example.komalli.RegistroInicioSActivity


class MiPerfilFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mi_perfil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnModificarPerfil : Button = view.findViewById(R.id.btn_modificar_datos)
        val btnHistorialCompras : Button = view.findViewById(R.id.btn_historial_compras)
        val btnCerrarSesion : Button = view.findViewById(R.id.btn_cerrar_sesion)

        btnModificarPerfil.setOnClickListener {
            val intent = Intent(context, ModificarPerfilActivity::class.java)
            startActivity(intent)
        }
        btnHistorialCompras.setOnClickListener {
            val intent = Intent(context, HistorialComprasActivity::class.java)
            startActivity(intent)
        }
        btnCerrarSesion.setOnClickListener {
            val intent = Intent(activity, IniciarSesionActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

    }
}