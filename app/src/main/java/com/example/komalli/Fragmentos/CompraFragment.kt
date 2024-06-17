package com.example.komalli.Fragmentos

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.komalli.Modelo.CompraDB
import com.example.komalli.Modelo.PlatilloDB
import com.example.komalli.PagoActivity
import com.example.komalli.Poko.Compra
import com.example.komalli.Poko.Platillo
import com.example.komalli.R

class CompraFragment : Fragment() {

    private lateinit var dbCompras : CompraDB
    private lateinit var platilloDB: PlatilloDB
    private lateinit var arrayPlatillos : ArrayList<Platillo>

    private var correo = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compra, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dbCompras=CompraDB(requireContext())
        platilloDB = PlatilloDB(requireContext())

        correo = arguments?.getString("correo")!!
        arrayPlatillos = arrayListOf<Platillo>()
        arrayPlatillos = platilloDB.obtenerPlatillos()



        val nombreProducto = arguments?.getString("nombrePlatillo")
        val totalPiezas = arguments?.getInt("totalPiezas")
        val totalPago = arguments?.getDouble("totalPago")
        var existenciasPlatillo = 0
        //Toast.makeText(requireContext(), "Correo Inicio : ${correo}", Toast.LENGTH_SHORT).show()

        for (platillo in arrayPlatillos){
            if(platillo.nombre.equals(nombreProducto)){
                existenciasPlatillo = platillo.cantidad
            }
        }
        //Toast.makeText(requireContext(), "Platillo ${nombreProducto} Pago ${totalPago} totalPieza ${totalPiezas}", Toast.LENGTH_LONG).show()

        val tvNombreProducto : TextView = view.findViewById(R.id.tv_nombre_producto)
        val tvCantidadProducto : TextView = view.findViewById(R.id.tv_cantidad_producto)
        val tvTotalPago : TextView = view.findViewById(R.id.tv_total_pago)
        val btnRealizarPago : Button = view.findViewById(R.id.btn_realizar_pago)

        tvNombreProducto.text=nombreProducto
        tvCantidadProducto.text="${totalPiezas.toString()} piezas"
        tvTotalPago.text="$ ${totalPago.toString()} pesos"

        val compra = Compra(null,correo,nombreProducto!!,totalPiezas!!,totalPago!!)

        btnRealizarPago.setOnClickListener {
            val resultadoCompra = dbCompras.agregarCompar(compra)
            val existenciasPlatilloActualizadas = existenciasPlatillo - totalPiezas
            val cambioExistencias = platilloDB.actualizarExistenciaPlatillo(nombreProducto,existenciasPlatilloActualizadas)
            if(resultadoCompra>0 && cambioExistencias > 0){
                Toast.makeText(requireContext(), "Compra realizada", Toast.LENGTH_LONG).show()
                val intent = Intent(requireContext(), PagoActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(requireContext(), "Error al realizar la Compra ", Toast.LENGTH_LONG).show()
            }
        }
    }
}