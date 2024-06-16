package com.example.komalli

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.komalli.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        binding.logoSplasScreen.alpha=0f
        binding.logoSplasScreen.animate().setDuration(2500).alpha(1f).withEndAction {
            val intentLogin = Intent(this@MainActivity, RegistroInicioSActivity::class.java)
            startActivity(intentLogin)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}