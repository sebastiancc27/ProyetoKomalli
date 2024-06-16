package com.example.komalli.Modelo

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.komalli.Poko.Comentario

class ComentariosDB(context : Context) : SQLiteOpenHelper(context, NOMBRE_DB, null, VERSIONDB) {
    companion object{
        private const val NOMBRE_DB = "Komalli"
        private const val VERSIONDB=1
        private const val NOMBRE_TABLA="Comentarios"
        private const val COLUMN_CORREO="Correo"
        private const val COLUMN_COMENTARIO="Comentario"
        private const val COLUMN_IDCOMENTARIO = "ID"
    }
    override fun onCreate(p0: SQLiteDatabase?) {
        var query = "CREATE TABLE $NOMBRE_TABLA($COLUMN_IDCOMENTARIO INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_CORREO VARCHAR(40), $COLUMN_COMENTARIO VARCHAR(200) )"
        p0?.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
    fun crearTabla(){
        val db = writableDatabase
        var valor = db.execSQL("CREATE TABLE IF NOT EXISTS $NOMBRE_TABLA($COLUMN_IDCOMENTARIO INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_CORREO VARCHAR(40), $COLUMN_COMENTARIO VARCHAR(200) )")
    }
    fun agregarComentario(comentario : Comentario): Long{
        val db = writableDatabase
        val contenValue = ContentValues()
        contenValue.put(COLUMN_IDCOMENTARIO,comentario.id)
        contenValue.put(COLUMN_CORREO,comentario.usuario)
        contenValue.put(COLUMN_COMENTARIO, comentario.comentario)
        val respuesta= db.insert(NOMBRE_TABLA,null,contenValue)
        return respuesta
    }
    @SuppressLint("Range")
    fun obtenerComentarios() : ArrayList<Comentario>{
        val db=readableDatabase
        val comentarios = arrayListOf<Comentario>()
        val cursor : Cursor = db.query(NOMBRE_TABLA,null,null,null,null,null,null)
        while(cursor.moveToNext()){
            val idComentario = cursor.getInt(cursor.getColumnIndex(COLUMN_IDCOMENTARIO))
            val correo = cursor.getString(cursor.getColumnIndex(COLUMN_CORREO))
            val comentarioUsuario = cursor.getString(cursor.getColumnIndex(COLUMN_COMENTARIO))
            val comentario = Comentario(idComentario,correo,comentarioUsuario)
            comentarios.add(comentario)
        }
        return comentarios
        }

}