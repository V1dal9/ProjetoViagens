package com.example.projetoprogramacaoavancada

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

abstract  class TabelaBD(val db: SQLiteDatabase, val nome: String) {
    abstract fun criar()

    fun insert(values: ContentValues) =
        db.insert(
            nome,
            null,
            values
        )
    fun update(values: ContentValues, whereClaus: String, whereArgs: Array<String>) =
        db.update(nome, values, whereClaus, whereArgs)

    fun delete(values: String, whereClaus: Array<String>) =
        db.delete(nome, values, whereClaus)

    fun query(
        columns: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        groupBy: String?,
        having: String?,
        orderBy: String?
    ) = db.query(nome, columns, selection, selectionArgs, groupBy, having, orderBy)
}