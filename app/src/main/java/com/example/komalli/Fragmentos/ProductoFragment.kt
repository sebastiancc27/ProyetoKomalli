package com.example.komalli.Fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.komalli.R

class ProductoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_producto, container, false)
        //Toast.makeText(context,"NOMBRE PRODUCTO ${nombreProducto}",Toast.LENGTH_LONG).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val  nombreProducto = arguments?.getString("nombrePlatillo").toString()
        val precioProducto = arguments?.getDouble("precioPlatillo")
        var precioTotal : Double = 0.0
        var contador : Int = 1
        view.findViewById<TextView>(R.id.tv_nombre_producto).text = nombreProducto
        view.findViewById<TextView>(R.id.tv_total_pagar).text=precioProducto.toString()

        view.findViewById<TextView>(R.id.tv_num_seleccionado_producto).text=contador.toString()
        //AÑADIENDO EL LISTENER AL BOTON DE AGREAGAR CANTIDA DE PRODUCTO
        view.findViewById<Button>(R.id.btn_agregar_cantidad_producto).setOnClickListener {
            val maxPedido = view.findViewById<TextView>(R.id.tv_num_existencias_producto)
            if(contador<maxPedido.text.toString().toInt()){
                contador++
                if (precioProducto != null) {
                    precioTotal = precioProducto * contador
                }
                view.findViewById<TextView>(R.id.tv_num_seleccionado_producto).text=contador.toString()
                view.findViewById<TextView>(R.id.tv_total_pagar).text=precioTotal.toString()
            }

        }

        view.findViewById<Button>(R.id.btn_eliminar_cantidad_producto).setOnClickListener {
            if(contador>1){
                contador--
                if (precioProducto != null) {
                    precioTotal = precioProducto * contador
                }
                view.findViewById<TextView>(R.id.tv_num_seleccionado_producto).text=contador.toString()
                view.findViewById<TextView>(R.id.tv_total_pagar).text=precioTotal.toString()

            }

        }

    }

}