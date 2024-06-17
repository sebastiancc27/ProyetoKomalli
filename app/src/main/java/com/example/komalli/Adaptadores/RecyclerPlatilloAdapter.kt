package com.example.komalli.Adaptadores

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.komalli.Interfaces.ListenerRecyclePlatillo
import com.example.komalli.Poko.Platillo
import com.example.komalli.R
import com.squareup.picasso.Picasso

class RecyclerPlatilloAdapter( var alimentos : ArrayList<Platillo>,val context: android.content.Context, val listenerRecyecle : ListenerRecyclePlatillo) : RecyclerView.Adapter<RecyclerPlatilloAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nombrePlatillo : TextView = itemView.findViewById(R.id.nombre_platillo)
        val precioPlatillo : TextView = itemView.findViewById(R.id.precio_platillo)
        val itemRecycle : LinearLayout = itemView.findViewById(R.id.item_platillo)
        val imagen : ImageView = itemView.findViewById(R.id.imgv_platillo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycle_platillo, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return alimentos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val platillo = alimentos[position]
        val imageUrl =platillo.urlImagen
        holder.nombrePlatillo.text=platillo.nombre
        holder.precioPlatillo.text="$ ${platillo.precio.toString()}0"
        holder.itemRecycle.setOnClickListener{
            listenerRecyecle.clickPlatillo(position)
        }
        Picasso.get().load(imageUrl).into(holder.imagen)
    }
}