package com.example.projetoprogramacaoavancada

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class Tabela_Origem(db:SQLiteDatabase):TabelaBD(db, NOME_ORIGEM) {
    override fun criar() {
        db.execSQL("CREATE TABLE $nome(${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$NOME_ORIGEM TEXT NOT NULL, " +
                "$LOCAL_ORIGEM TEXT NOT NULL, " +
                "$TRAVELO_ID INTEGER NOT NULL" +
                "FOREIGN KEY($TRAVELO_ID) " +
                "REFERENCES ${Tabela_Info_Viagem_Bilhete.NOME}(${BaseColumns._ID}))")
    }
    companion object{
        const val ID_ORIGEM = "id"
        const val NOME_ORIGEM = "Origem"
        const val LOCAL_ORIGEM = "local"
        const val TRAVELO_ID = "travel_id"
    }
}