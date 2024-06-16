package com.example.komalli

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.komalli.Modelo.CompraDB
import com.example.komalli.Poko.Compra
import com.example.komalli.databinding.ActivityPagoBinding

class PagoActivity : AppCompatActivity() {
    private lateinit var comprasDB : CompraDB
    private lateinit var binding: ActivityPagoBinding
    private lateinit var compras : ArrayList<Compra>
    private var id = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPagoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        comprasDB = CompraDB(this@PagoActivity)
        compras = arrayListOf<Compra>()

        compras = comprasDB.obtenerComprasOrderID()

        val ultimaCompra = compras[compras.size-1]
        binding.tvIdCompra.text = ultimaCompra.id.toString()
        //Toast.makeText(this@PagoActivity, "ID ULTIMA COMPRA : ${ultimaCompra.id}, PRODUCTO : ${ultimaCompra.nombreProducto}", Toast.LENGTH_SHORT).show()
        binding.btnSeguirComprando.setOnClickListener {
            val intent = Intent(this@PagoActivity, PlatilloActivity::class.java)
            startActivity(intent)
        }
    }
}