package com.example.komalli

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.komalli.Adaptadores.RecycleHComprasAdapter
import com.example.komalli.Interfaces.ListenerRecycleHCompras
import com.example.komalli.Modelo.CompraDB
import com.example.komalli.Poko.Compra
import com.example.komalli.databinding.ActivityHistorialComprasBinding

class HistorialComprasActivity : AppCompatActivity(), ListenerRecycleHCompras {
    private lateinit var binding: ActivityHistorialComprasBinding
    private lateinit var compraBD: CompraDB
    private lateinit var recycleview : RecyclerView
    private lateinit var adapter : RecycleHComprasAdapter
    private lateinit var arrayCompras : ArrayList<Compra>
    private var correo = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityHistorialComprasBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        correo = intent.getStringExtra("correo")!!
        compraBD= CompraDB(this@HistorialComprasActivity)
        recycleview=binding.recycleHistorialCompras
        recycleview.layoutManager= LinearLayoutManager(this@HistorialComprasActivity)
        arrayCompras = arrayListOf<Compra>()
        arrayCompras= compraBD.obtenerComprasUsuario(correo)
        adapter = RecycleHComprasAdapter(arrayCompras, this)
        recycleview.adapter=adapter

        binding.btnRegresarHc.setOnClickListener {
            val intent = Intent(this@HistorialComprasActivity, PlatilloActivity::class.java)
            startActivity(intent)
        }
    }

    override fun clickCompra(position: Int) {
        Toast.makeText(this@HistorialComprasActivity, "NOMBRE PRODUCTO: ${arrayCompras[position].nombreProducto}", Toast.LENGTH_SHORT).show()
        val idCompra = arrayCompras[position].id.toString()
        val intent = Intent(this@HistorialComprasActivity, ComprobanteActivity::class.java)
        intent.putExtra("id",idCompra)
        startActivity(intent)
    }
}