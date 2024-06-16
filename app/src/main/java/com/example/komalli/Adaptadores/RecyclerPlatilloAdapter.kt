package com.example.komalli.Adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.komalli.Interfaces.ListenerRecyclePlatillo
import com.example.komalli.Poko.Platillo
import com.example.komalli.R

class RecyclerPlatilloAdapter( var alimentos : ArrayList<Platillo>, val listenerRecyecle : ListenerRecyclePlatillo) : RecyclerView.Adapter<RecyclerPlatilloAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nombrePlatillo : TextView = itemView.findViewById(R.id.nombre_platillo)
        val precioPlatillo : TextView = itemView.findViewById(R.id.precio_platillo)
        val itemRecycle : LinearLayout = itemView.findViewById(R.id.item_platillo)
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
           holder.nombrePlatillo.text=platillo.nombre
           holder.precioPlatillo.text="$ ${platillo.precio.toString()}.00"
            holder.itemRecycle.setOnClickListener{
                    listenerRecyecle.clickPlatillo(position)
            }
    }
}