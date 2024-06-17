package com.example.komalli.Modelo

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.komalli.Poko.Usuario

class UsuariosDB(context : Context) : SQLiteOpenHelper(context, NOMBRE_DB, null, VERSIONDB){

    companion object{
        private const val NOMBRE_DB = "Komalli"
        private const val NOMBRE_TABLA="Usuarios"
        private const val VERSIONDB=1
        //TUPLAS DE LA TABLA
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

    fun crearTabla(){
        val db = writableDatabase
        val valor= db.execSQL("CREATE TABLE IF NOT EXISTS $NOMBRE_TABLA ($COLUMN_CORREO VARCHAR(40) PRIMARY KEY, $COLUMN_NOMBRE VARCHAR(30), $COLUMN_APELLIDO VARCHAR(40), $COLUMN_TELEFONO VARCHAR(30), $COLUMN_LOCALIDAD VARCHAR(30), $COLUMN_CONTRASENA VARCHAR(20))")
    }

    fun agregarUsuarios( usuario : Usuario) : Long{
        val db = writableDatabase//MODIFICACIÓN, INSERCIÓN
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
        val db = readableDatabase//SELECCIÓN LECTURA
        val validarQuery ="SELECT $COLUMN_CORREO, $COLUMN_CONTRASENA FROM $NOMBRE_TABLA WHERE $COLUMN_CORREO='${correo}' AND $COLUMN_CONTRASENA='${contrasena}'"
        val cursor = db.rawQuery(validarQuery,null)
        if(cursor.count>0){
            validado=true
        }
        db.close()
        return validado
    }
    @SuppressLint("Range")
    fun obtenerDatosUsuario(correo : String) : Usuario{
        var db = readableDatabase
        var correoUsuario =""
        var nombre =""
        var apellido =""
        var localidad =""
        var telefono=""
        var contrasena =""
        lateinit var usuario : Usuario

        val resultadoConsulta  : Cursor =db.query(NOMBRE_TABLA,null,"$COLUMN_CORREO=?", arrayOf(correo),null,null,null)
        if(resultadoConsulta!=null){
            while (resultadoConsulta.moveToNext()){
                correoUsuario =resultadoConsulta.getString(resultadoConsulta.getColumnIndex(COLUMN_CORREO))
                nombre = resultadoConsulta.getString(resultadoConsulta.getColumnIndex(COLUMN_NOMBRE))
                apellido = resultadoConsulta.getString(resultadoConsulta.getColumnIndex(COLUMN_APELLIDO))
                localidad = resultadoConsulta.getString(resultadoConsulta.getColumnIndex(COLUMN_LOCALIDAD))
                telefono = resultadoConsulta.getString(resultadoConsulta.getColumnIndex(COLUMN_TELEFONO))
                contrasena=resultadoConsulta.getString(resultadoConsulta.getColumnIndex(COLUMN_CONTRASENA))
            }
            usuario = Usuario(nombre, apellido,correoUsuario,telefono,localidad,contrasena)
        }
        db.close()
        return usuario
    }
    fun eliminarPerfil(usuario : String) : Int{
        val db = writableDatabase
        val resultado = db.delete(NOMBRE_TABLA, "$COLUMN_CORREO=?", arrayOf(usuario))
        db.close()
        return resultado
    }
    fun actualizarDatosUsuario (correo : String ,telefono : String, localidad : String) : Int{
        val db = writableDatabase
        val datosActualizar = ContentValues()
        datosActualizar.put(COLUMN_TELEFONO, telefono)
        datosActualizar.put(COLUMN_LOCALIDAD, localidad)
        val resultado =db.update(NOMBRE_TABLA,datosActualizar,"$COLUMN_CORREO=?", arrayOf(correo))
        db.close()
        return resultado
    }

}