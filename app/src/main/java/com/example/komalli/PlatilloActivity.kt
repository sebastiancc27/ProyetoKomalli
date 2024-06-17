package com.example.komalli

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.komalli.Fragmentos.ComentariosFragment
import com.example.komalli.Fragmentos.InicioFragment
import com.example.komalli.Fragmentos.MiPerfilFragment
import com.example.komalli.databinding.ActivityPlatilloBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class PlatilloActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlatilloBinding
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var correo : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlatilloBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        correo = intent.getStringExtra("correo")!!
        //Toast.makeText(this@PlatilloActivity, "${correo}", Toast.LENGTH_LONG).show()
        bottomNavigationView = binding.bottomNavigation
        replaceFragment(InicioFragment())
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.bottom_home->{
                    replaceFragment(InicioFragment())
                    true
                }
                R.id.bottom_comentarios->{
                    replaceFragment(ComentariosFragment())

                    true
                }
                R.id.bottom_perfil->{
                    // Toast.makeText(this@PlatilloActivity, "Perfil",Toast.LENGTH_LONG).show()
                    replaceFragment(MiPerfilFragment())

                    true
                }

                else -> {
                    false
                }
            }
        }
    }
    private fun replaceFragment(fragmento : Fragment){
            val bundle = Bundle()
            bundle.putString("correo", correo)
            fragmento.arguments=bundle
            supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragmento).addToBackStack(null).commit()
    }
}