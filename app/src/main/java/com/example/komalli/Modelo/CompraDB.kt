package com.example.komalli.Modelo

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.komalli.Poko.Compra

class CompraDB(context : Context) : SQLiteOpenHelper(context, NOMBRE_DB, null, VERSIONDB) {
    companion object{
        private const val NOMBRE_DB = "Komalli"
        private const val VERSIONDB=1
        private const val NOMBRE_TABLA="Compras"
        private const val COLUMN_NOMBREPRODUCTO = "NombreProducto"
        private const val COLUMN_TOTALPRODUCTO = "Total"
        private const val COLUMN_CORREO="Correo"
        private const val COLUMN_CANTIDADPRODUCTO="Cantidad"
        private const val COLUMN_IDCOMPRA = "ID"

    }

    override fun onCreate(p0: SQLiteDatabase?) {
        var query = "CREATE TABLE $NOMBRE_TABLA($COLUMN_IDCOMPRA INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_CORREO VARCHAR(40), $COLUMN_NOMBREPRODUCTO VARCHAR(40), $COLUMN_CANTIDADPRODUCTO INTEGER, $COLUMN_TOTALPRODUCTO INTEGER, foreign key($COLUMN_CORREO) references Usuarios(Correo), foreign key($COLUMN_NOMBREPRODUCTO) references Platillos(Nombre)) "
        p0?.execSQL(query)
    }
    fun crearTabla ( ){
        val db = writableDatabase
        var valor =db.execSQL( "CREATE TABLE IF NOT EXISTS $NOMBRE_TABLA($COLUMN_IDCOMPRA INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_CORREO VARCHAR(40), $COLUMN_NOMBREPRODUCTO VARCHAR(40), $COLUMN_CANTIDADPRODUCTO INTEGER, $COLUMN_TOTALPRODUCTO INTEGER, foreign key($COLUMN_CORREO) references Usuarios(Correo), foreign key($COLUMN_NOMBREPRODUCTO) references Platillos(Nombre))")
        return valor
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
    fun agregarCompar(compra : Compra) : Long{
        val db = writableDatabase
        val contenValue = ContentValues()
        contenValue.put(COLUMN_IDCOMPRA, compra.id)
        contenValue.put(COLUMN_CORREO, compra.correoUsuario)
        contenValue.put(COLUMN_NOMBREPRODUCTO, compra.nombreProducto)
        contenValue.put(COLUMN_CANTIDADPRODUCTO, compra.cantidadProducto)
        contenValue.put(COLUMN_TOTALPRODUCTO, compra.totalPagado)
        val resultado = db.insert(NOMBRE_TABLA,null, contenValue)
        db?.close()
        return resultado
    }

    @SuppressLint("Range")
    fun obtenerComprasUsuario(correo : String) : ArrayList<Compra>{
        val compras = arrayListOf<Compra>()
        val db = readableDatabase
        val cursor : Cursor = db.query(NOMBRE_TABLA,null,"$COLUMN_CORREO=?", arrayOf(correo),null,null,null)
        while(cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndex(COLUMN_IDCOMPRA))
            val correo = cursor.getString(cursor.getColumnIndex(COLUMN_CORREO))
            val nombreProducto = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBREPRODUCTO))
            val cantidad = cursor.getInt(cursor.getColumnIndex(COLUMN_CANTIDADPRODUCTO))
            val totalPagado = cursor.getDouble(cursor.getColumnIndex(COLUMN_TOTALPRODUCTO))
            val compra = Compra(id,correo, nombreProducto,cantidad,totalPagado)
            compras.add(compra)
        }
        db.close()
        return compras
    }
    @SuppressLint("Range")
    fun obtenerComprasOrderID() : ArrayList<Compra>{
        val compras = arrayListOf<Compra>()
        val db = readableDatabase
        val cursor : Cursor = db.query(NOMBRE_TABLA,null,null,null,null,null, COLUMN_IDCOMPRA)
        while(cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndex(COLUMN_IDCOMPRA))
            val correo = cursor.getString(cursor.getColumnIndex(COLUMN_CORREO))
            val nombreProducto = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBREPRODUCTO))
            val cantidad = cursor.getInt(cursor.getColumnIndex(COLUMN_CANTIDADPRODUCTO))
            val totalPagado = cursor.getDouble(cursor.getColumnIndex(COLUMN_TOTALPRODUCTO))
            val compra = Compra(id,correo, nombreProducto,cantidad,totalPagado)
            compras.add(compra)
        }
        return compras
    }
}