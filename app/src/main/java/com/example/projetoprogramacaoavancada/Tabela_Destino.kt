package com.example.projetoprogramacaoavancada

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class Tabela_Destino(db : SQLiteDatabase): TabelaBD(db, NOME_DESTINO) {
    override fun criar() {
    db.execSQL("CREATE TABLE $nome(${BaseColumns._ID} ON DELETE RESTRICT INTEGER PRIMARY KEY AUTOINCREMENT, $NOME_DESTINO TEXT NOT NULL, $LOCAL_DESTINO TEXT NOT NULL, FOREIGN KEY($ID_DESTINO) REFERENCES ${Tabela_Destino})")
    }
    companion object{
        const val NOME_DESTINO = "nome"
        const val ID_DESTINO = "id"
        const val LOCAL_DESTINO = "destino"
    }
}