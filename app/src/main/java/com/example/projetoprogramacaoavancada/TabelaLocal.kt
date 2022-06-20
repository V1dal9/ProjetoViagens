package com.example.projetoprogramacaoavancada

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaLocal (db: SQLiteDatabase):TabelaBD(db, NOME_LOCAL) {
    override fun cria() {
        db.execSQL("CREATE TABLE $nome(${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$NOME_LOCAL TEXT NOT NULL, " +
                "$LOCAL_ORIGEM TEXT NOT NULL, " +
                "$LOCAL_DESTINO TEXT NOT NULL, " +
                "$LOCAL_ID INTEGER NOT NULL) ")
    }
    companion object{
        const val LOCAL_ID = "id"
        const val NOME_LOCAL = "local"
        const val LOCAL_ORIGEM = "origem"
        const val LOCAL_DESTINO = "destino"
    }
}