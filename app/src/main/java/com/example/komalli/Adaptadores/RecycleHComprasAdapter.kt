package com.example.komalli.Adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.komalli.Interfaces.ListenerRecycleHCompras
import com.example.komalli.Poko.Compra
import com.example.komalli.R

class RecycleHComprasAdapter(var lista : ArrayList<Compra>, var listener : ListenerRecycleHCompras) : RecyclerView.Adapter<RecycleHComprasAdapter.MyViewHolder>() {

    class MyViewHolder ( itemView : View) : RecyclerView.ViewHolder(itemView){
        var nombreProducto  : TextView = itemView.findViewById(R.id.tv_nombre_platillo_rv_item)
        var totalPagado : TextView = itemView.findViewById(R.id.tv_precio_platillo_rv_item)
        var itemRecycle : CardView = itemView.findViewById(R.id.item_compras)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_hcompras, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val compra = lista[position]
        holder.nombreProducto.text=compra.nombreProducto
        holder.totalPagado.text= compra.totalPagado.toString()
        holder.itemRecycle.setOnClickListener{
            listener.clickCompra(position)
        }
    }
}