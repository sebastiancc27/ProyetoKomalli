package com.example.komalli

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.komalli.databinding.ActivityComprobanteBinding

class ComprobanteActivity : AppCompatActivity() {
    private lateinit var binding : ActivityComprobanteBinding
    private var id = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComprobanteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        id = intent.getStringExtra("id")!!
      //  Toast.makeText(this@ComprobanteActivity, "ID : ${id}", Toast.LENGTH_SHORT).show()
        binding.tvIdCompra.setText(id)
        binding.btnSeguirComprandoComprobante.setOnClickListener {
            val intent = Intent(this@ComprobanteActivity, HistorialComprasActivity::class.java)
            startActivity(intent)
        }
    }
}