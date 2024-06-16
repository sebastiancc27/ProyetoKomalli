package com.example.komalli.Modelo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.komalli.Poko.Usuario

class UsuariosDB(context : Context) : SQLiteOpenHelper(context, NOMBRE_DB, null, VERSIONDB){
    companion object{
        private const val NOMBRE_DB = "Komalli"
        private const val NOMBRE_TABLA="Usuarios"
        private const val VERSIONDB=1
        private const val COLUMN_NOMBRE="Nombre"
        private const val COLUMN_APELLIDO="Apellido"
        private const val COLUMN_CORREO="Correo"
        private const val COLUMN_TELEFONO="Telefono"
        private const val COLUMN_LOCALIDAD="Localidad"
        private const val COLUMN_CONTRASENA="Contrasena"

    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val CREATE_TABLE_USUARIOS="CREATE TABLE $NOMBRE_TABLA ($COLUMN_CORREO VARCHAR(40) PRIMARY KEY, $COLUMN_NOMBRE VARCHAR(30), $COLUMN_APELLIDO VARCHAR(40), $COLUMN_TELEFONO VARCHAR(30), $COLUMN_LOCALIDAD VARCHAR(30), $COLUMN_CONTRASENA VARCHAR(20))"
        p0?.execSQL(CREATE_TABLE_USUARIOS)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
    fun agregarUsuarios( usuario : Usuario) : Long{
        val db = writableDatabase
        val insertarUsuario = ContentValues()
        insertarUsuario.put(COLUMN_CORREO, usuario.correo)
        insertarUsuario.put(COLUMN_NOMBRE, usuario.nombre)
        insertarUsuario.put(COLUMN_APELLIDO,usuario.apellido)
        insertarUsuario.put(COLUMN_TELEFONO, usuario.telefono)
        insertarUsuario.put(COLUMN_LOCALIDAD, usuario.localidad)
        insertarUsuario.put(COLUMN_CONTRASENA, usuario.contrasena)
        val filasAfectadas = db.insert(NOMBRE_TABLA, null, insertarUsuario)
        db?.close()
        return filasAfectadas
    }

    fun validadUsuario( correo : String, contrasena: String) : Boolean{
        var validado = false
        val db = readableDatabase
        val validarQuery ="SELECT $COLUMN_CORREO, $COLUMN_CONTRASENA FROM $NOMBRE_TABLA WHERE $COLUMN_CORREO='${correo}' AND $COLUMN_CONTRASENA='${contrasena}'"
        val cursor = db.rawQuery(validarQuery,null)
        if(cursor.count>0){
            validado=true
        }
        return validado
    }

}