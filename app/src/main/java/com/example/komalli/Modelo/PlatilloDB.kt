package com.example.komalli.Modelo

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.komalli.Poko.Platillo

class PlatilloDB(context: Context ) : SQLiteOpenHelper(context, NOMBRE_DB, null, VERSIONDB) {

    companion object{
        private const val NOMBRE_DB = "Komalli"
        private const val VERSIONDB=1

        private const val NOMBRE_TABLA="Platillos"

        private const val COLUMN_NOMBRE="Nombre"
        private const val COLUMN_PRECIO="Precio"
        private const val COLUMN_EXISTENCIAS="Existencias"
        private const val COLUMN_IMAGEN="Imagen"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val CREATE_TABLE_USUARIOS="CREATE TABLE $NOMBRE_TABLA ($COLUMN_NOMBRE VARCHAR(40) PRIMARY KEY, $COLUMN_PRECIO INTEGER, $COLUMN_EXISTENCIAS INTEGER, $COLUMN_IMAGEN VARCHAR(130))"
        p0?.execSQL(CREATE_TABLE_USUARIOS)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
    fun crearTabla(){
        val db = writableDatabase
        val valor= db.execSQL("CREATE TABLE IF NOT EXISTS $NOMBRE_TABLA ($COLUMN_NOMBRE VARCHAR(40) PRIMARY KEY, $COLUMN_PRECIO INTEGER, $COLUMN_EXISTENCIAS INTEGER, $COLUMN_IMAGEN VARCHAR(130))")
        return valor
    }

    fun agregarPlatillo ( platillo : Platillo) : Long{
        val db = writableDatabase
        val insertarPlatillo = ContentValues()
        insertarPlatillo.put(COLUMN_NOMBRE, platillo.nombre)
        insertarPlatillo.put(COLUMN_PRECIO, platillo.precio)
        insertarPlatillo.put(COLUMN_EXISTENCIAS, platillo.cantidad)
        insertarPlatillo.put(COLUMN_IMAGEN, platillo.urlImagen)

        val filasAfectadas = db.insert(NOMBRE_TABLA,null,insertarPlatillo)
        db?.close()
        return filasAfectadas
    }

    @SuppressLint("Range")
    fun obtenerPlatillos() : ArrayList<Platillo>{
        val db = readableDatabase
        var platillos = ArrayList<Platillo>()
        val cursor : Cursor = db.query(NOMBRE_TABLA, null, null,null,null,null,null)
        //select * from platillos
        if(cursor!=null){
            while (cursor.moveToNext()){
                val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE))
                val precio = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRECIO))
                val existencias = cursor.getInt(cursor.getColumnIndex(COLUMN_EXISTENCIAS))
                val urlImagen = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGEN))

                val platillo = Platillo(nombre, precio, existencias, urlImagen)

                platillos.add(platillo)
            }
            cursor.close()
        }
        db.close()
        return platillos
    }
    fun actualizarExistenciaPlatillo(nombre : String, cantidad : Int): Int{
        val db = writableDatabase
        val valoresActualizados = ContentValues()
        valoresActualizados.put(COLUMN_EXISTENCIAS, cantidad)
        val filasAfectadas = db.update(NOMBRE_TABLA,valoresActualizados,"$COLUMN_NOMBRE=?", arrayOf(nombre))
        db.close()
        return filasAfectadas
    }
    fun eliminarTabla(){
        val db = writableDatabase
        val query = "DROP TABLE $NOMBRE_TABLA"
        db.execSQL(query)
        db.close()
    }
}