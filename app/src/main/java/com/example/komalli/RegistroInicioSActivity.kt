package com.example.komalli

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.R
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.komalli.Modelo.ComentariosDB
import com.example.komalli.Modelo.CompraDB
import com.example.komalli.Modelo.PlatilloDB
import com.example.komalli.Poko.Platillo
import com.example.komalli.databinding.ActivityRegistroInicioSactivityBinding

class RegistroInicioSActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroInicioSactivityBinding
    private lateinit var db : PlatilloDB
    private lateinit var dbCompras : CompraDB
    private lateinit var dbComentarios : ComentariosDB
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRegistroInicioSactivityBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        db= PlatilloDB(this@RegistroInicioSActivity)

        //SE CREA LA TABLA DE COMPRAS EN CASO DE NO EXISTIR
        dbCompras = CompraDB(this@RegistroInicioSActivity)
        dbCompras.crearTabla()
        //SE CREA LA TABLA DE COMANTARIOS EN CASO DE QUE NO EXISTA
        dbComentarios=ComentariosDB(this@RegistroInicioSActivity)
        dbComentarios.crearTabla()

        binding.iniciarSesionBtn.setOnClickListener {
            val ventanaInicioSesion = Intent(this@RegistroInicioSActivity, IniciarSesionActivity::class.java)
            cargarAlimentos()
            startActivity(ventanaInicioSesion)
        }
        binding.registrarmeBtn.setOnClickListener {
            val ventanaRegistro = Intent(this@RegistroInicioSActivity, RegistroActivity::class.java)
            startActivity(ventanaRegistro)
        }
    }
    private fun cargarAlimentos(){
        val platillo1=Platillo("Sandwich",30.0,40,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRznK1Kr1sJr_v77OflYXIhJHowzb5MX6IccQ&s")
        val platillo2=Platillo("Torta",20.0,40,"https://assets.tmecosys.com/image/upload/t_web767x639/img/recipe/ras/Assets/62FAD752-6853-4822-956D-C77F6F96D4E6/Derivates/92BABE34-B56B-42AC-A347-ACE3376EA303.jpg")
        val platillo3=Platillo("Empanadas",40.0,40,"https://assets.tmecosys.com/image/upload/t_web767x639/img/recipe/ras/Assets/7EBBBD3C-C2A8-4292-92BE-8504D03C1D0F/Derivates/E350B4D0-6217-456E-8778-337AA72843D6.jpg")
        val platillo4=Platillo("Pancho",30.0,40,"https://cdn2.cocinadelirante.com/sites/default/files/styles/gallerie/public/images/2023/02/receta-de-hot-dogs.jpg")
        val platillo5=Platillo("Hamburguesa",35.0,40,"https://www.unileverfoodsolutions.com.co/dam/global-ufs/mcos/NOLA/calcmenu/recipes/col-recipies/recetas-fruco-bbq/Hamburguesa-Texana-1200x709.jpg")
        val platillo6=Platillo("Chilaquiles",40.0,40,"https://www.lacostena.com.mx/media/thumbnail/uploads/Recipes/07_chilaquiles_rojos_con_huevo_estrellado-min.jpg.1920x800_q85_center.jpg")
        val platillo7=Platillo("Tacos",30.0,40,"https://mandolina.co/wp-content/uploads/2023/06/tacos-clasicos.png")
        val platillo8=Platillo("Enchiladas",35.0,40,"https://www.gourmet.cl/wp-content/uploads/2021/08/foto-istock-enchilada-tama%C3%B1o-web.jpg")
        val platillo9=Platillo("Pambazos",20.0,40,"https://cdn0.recetasgratis.net/es/posts/5/7/7/pambazos_de_mole_75775_orig.jpg")
        val platillo10=Platillo("HotCakes",35.0,40,"https://peopleenespanol.com/thmb/6R8iuETsWXmcVXjbQveyLOWZJiU=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/hot-cakes-esponjosos-estilo-americano.jpg-2000-e4f6d8f32b9d43f7abffb65ee93affb8.jpg")
        // db.eliminarTabla()
        db.crearTabla()
        val cargarPlatillo=db.agregarPlatillo(platillo1)

        }


}