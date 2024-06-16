package com.example.komalli.Fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.komalli.R
import com.squareup.picasso.Picasso

class ProductoFragment : Fragment() {
    private var correo = ""
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
        //ASIGNANDO LAS EXISTENCIAS RECUPERADAS AL TEXTVIEW DE LAS EXISTENCIAS DEL FRAGMENTO
        val tv_existencias =view.findViewById<TextView>(R.id.tv_num_existencias_producto)
        val imagenPlatillo : ImageView = view.findViewById(R.id.imgv_platillo_producto)

        val  nombreProducto = arguments?.getString("nombrePlatillo").toString()
        val  urlImagen = arguments?.getString("urlImagen").toString()
        val precioProducto = arguments?.getDouble("precioPlatillo")
        val existenciaProducto = arguments?.getInt("existenciasPlatillo")
        correo = arguments?.getString("correo")!!
        Toast.makeText(requireContext(), "Correo Producto: ${correo}", Toast.LENGTH_SHORT).show()
        Picasso.get().load(urlImagen).into(imagenPlatillo)

        var precioTotal=precioProducto
        var contador=1
        var totalPiezas = 1

        tv_existencias.text=existenciaProducto.toString()


        view.findViewById<TextView>(R.id.tv_nombre_producto).text = nombreProducto
        view.findViewById<TextView>(R.id.tv_total_pagar).text=precioProducto.toString()

        view.findViewById<TextView>(R.id.tv_num_seleccionado_producto).text=contador.toString()
        //AÑADIENDO EL LISTENER AL BOTON DE AGREAGAR CANTIDA DE PRODUCTO

        view.findViewById<Button>(R.id.btn_agregar_cantidad_producto).setOnClickListener {
            val maxPedido = tv_existencias.text.toString().toInt()

            if(contador<maxPedido){
                contador++
                if (precioProducto != null) {
                    precioTotal = precioProducto * contador
                }
                view.findViewById<TextView>(R.id.tv_num_seleccionado_producto).text=contador.toString()
                view.findViewById<TextView>(R.id.tv_total_pagar).text=precioTotal.toString()
                totalPiezas = contador
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
                totalPiezas = contador
            }

        }

        view.findViewById<Button>(R.id.btn_pagar_producto).setOnClickListener {
            //val fragmentoA = ProductoFragment()
            val fragmentoB = CompraFragment()
            val bundle = Bundle()//EL BUNDLE NOS SIRVE EN ESTE CASO PARA PASAR DATOS DE UN FRAGMENTO A OTRO
            bundle.putString("nombrePlatillo", nombreProducto)
            precioTotal?.let { it1 -> bundle.putDouble("totalPago", it1) }
            bundle.putInt("totalPiezas", totalPiezas)
            bundle.putString("correo", correo)

            fragmentoB.arguments=bundle
            Toast.makeText(requireContext(), "totalPieza ${totalPiezas}", Toast.LENGTH_LONG).show()
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.frame_container, fragmentoB)?.addToBackStack(null)?.commit()
        }

    }

}