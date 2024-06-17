package com.example.komalli.Fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.komalli.Adaptadores.RecyclerPlatilloAdapter
import com.example.komalli.Interfaces.ListenerRecyclePlatillo
import com.example.komalli.Modelo.PlatilloDB
import com.example.komalli.Poko.Platillo
import com.example.komalli.R


class InicioFragment : Fragment() , ListenerRecyclePlatillo{
    private lateinit var adapter : RecyclerPlatilloAdapter
    private lateinit var recyclePlatillos : RecyclerView
    private lateinit var platillos : ArrayList<Platillo>
    private lateinit var db : PlatilloDB
    private var usuario =""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_inicio, container, false)
        recyclePlatillos = view.findViewById(R.id.recycle_platillo)
        recyclePlatillos.layoutManager = GridLayoutManager(requireContext(),2)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usuario = arguments?.getString("correo")!!

        platillos= arrayListOf<Platillo>()

        db= PlatilloDB(requireContext())
        platillos=db.obtenerPlatillos()
        adapter = RecyclerPlatilloAdapter(platillos, requireContext(), this)
        recyclePlatillos.setHasFixedSize(true)
        recyclePlatillos.adapter=adapter

    }

    override fun clickPlatillo(position: Int) {
        val fragmentoB = ProductoFragment()
        var nombrePlatillo=platillos[position].nombre
        var precioPlatillo=platillos[position].precio
        var urlImagen = platillos[position].urlImagen
        var existenciasPlatillo = platillos[position].cantidad

        val bundle = Bundle()
        bundle.putString("nombrePlatillo", nombrePlatillo)
        bundle.putDouble("precioPlatillo",precioPlatillo)
        bundle.putInt("existenciasPlatillo",existenciasPlatillo)
        bundle.putString("correo", usuario)
        bundle.putString("urlImagen", urlImagen)
        fragmentoB.arguments=bundle
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.frame_container, fragmentoB)
            ?.addToBackStack(null)
            ?.commit()
    }

}