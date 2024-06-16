package com.example.komalli.Adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.komalli.Poko.Comentario
import com.example.komalli.R

class RecycleComentariosAdapter( var list: ArrayList<Comentario>) : RecyclerView.Adapter<RecycleComentariosAdapter.MyViewHolder>() {
    class MyViewHolder ( itemView: View) : RecyclerView.ViewHolder(itemView){
            var usuario : TextView = itemView.findViewById(R.id.tv_usuario_comentario)
            var comentario : TextView = itemView.findViewById(R.id.tv_contenido_comentario)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_recycle_comentarios, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var comentario = list[position]
        holder.usuario.text=comentario.usuario
        holder.comentario.text=comentario.comentario
    }
}