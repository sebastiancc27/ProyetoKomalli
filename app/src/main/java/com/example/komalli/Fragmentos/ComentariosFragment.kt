package com.example.komalli.Fragmentos

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.komalli.Adaptadores.RecycleComentariosAdapter
import com.example.komalli.ComentariosActivity
import com.example.komalli.Modelo.ComentariosDB
import com.example.komalli.Poko.Comentario
import com.example.komalli.R

class ComentariosFragment : Fragment() {
    private lateinit var correo : String
    private lateinit var comentariosDB: ComentariosDB
    private lateinit var adapter : RecycleComentariosAdapter
    private lateinit var recycleview : RecyclerView
    private lateinit var comentarios : ArrayList<Comentario>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comentarios, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        correo = arguments?.getString("correo")!!
        comentariosDB = ComentariosDB(requireContext())
        comentarios = arrayListOf<Comentario>()

        comentarios = comentariosDB.obtenerComentarios()
        recycleview = view.findViewById(R.id.rv_comentarios)
        recycleview.layoutManager = LinearLayoutManager(requireContext())

        adapter = RecycleComentariosAdapter(comentarios)
        recycleview.adapter=adapter

        val btnAgregarCometario : ImageButton = view.findViewById(R.id.btn_agregar_comentario)
        //btnAgregarComentario  = view.findViewById(R.id.btn_agregar_comentario)
        btnAgregarCometario.setOnClickListener {
            val intent = Intent(context, ComentariosActivity::class.java)
            intent.putExtra("correo",correo)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        comentarios = comentariosDB.obtenerComentarios()
        recycleview.layoutManager = LinearLayoutManager(requireContext())

        adapter = RecycleComentariosAdapter(comentarios)
        recycleview.adapter=adapter
        }
}