package com.example.komalli.Fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.komalli.Adaptadores.RecyclerPlatilloAdapter
import com.example.komalli.Interfaces.ListenerRecyclePlatillo
import com.example.komalli.Poko.Platillo
import com.example.komalli.R


class InicioFragment : Fragment() , ListenerRecyclePlatillo{
    private lateinit var adapter : RecyclerPlatilloAdapter
    private lateinit var recyclePlatillos : RecyclerView
    private lateinit var platillos : ArrayList<Platillo>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_inicio, container, false)
        recyclePlatillos = view.findViewById(R.id.recycle_platillo)
        inicializarRecycle()
        return view
    }

    private fun inicializarRecycle(){
        platillos = arrayListOf<Platillo>()
        val platillo1 = Platillo("Hamburguesa",22.00,4)
        val platillo2 = Platillo("Sandwich",32.00,4)
        val platillo3 = Platillo("Sopa",32.00,4)
        val platillo4 = Platillo("Caldo de Pollo",112.00,4)


        platillos.add(platillo1)
        platillos.add(platillo2)
        platillos.add(platillo3)
        platillos.add(platillo4)
        adapter= RecyclerPlatilloAdapter(platillos,this)
        recyclePlatillos.layoutManager = GridLayoutManager(context,2)
        recyclePlatillos.setHasFixedSize(true)
        recyclePlatillos.adapter=adapter
    }

    override fun clickPlatillo(position: Int) {
       //Toast.makeText(context, "POSICION ${platillos[position].nombre}", Toast.LENGTH_LONG).show()
        val fragmentoB = ProductoFragment()
        var nombrePlatillo=platillos[position].nombre
        var precioPlatillo=platillos[position].precio
        val bundle = Bundle()
        bundle.putString("nombrePlatillo", nombrePlatillo)
        bundle.putDouble("precioPlatillo",precioPlatillo)
        fragmentoB.arguments=bundle
      //  activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.frame_container, ProductoFragment()) ?.addToBackStack(null)?.commit()
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.frame_container, fragmentoB)
            ?.addToBackStack(null)
            ?.commit()
    }

}