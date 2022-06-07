package com.example.projetoprogramacaoavancada

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class Tabela_Destino(db : SQLiteDatabase): TabelaBD(db, NOME_DESTINO) {
    override fun criar() {
    db.execSQL("CREATE TABLE $nome(${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "$NOME_DESTINO TEXT NOT NULL, " +
            "$LOCAL_DESTINO TEXT NOT NULL, " +
            "$TRAVEL_ID NOT NULL, " +
            "FOREIGN KEY($TRAVEL_ID) REFERENCES ${Tabela_Info_Viagem.NOME}(${BaseColumns._ID})" +
            ")")
    }
    companion object{
        const val NOME_DESTINO = "Destino"
        const val ID_DESTINO = "id"
        const val LOCAL_DESTINO = "local"
        const val TRAVEL_ID = "travel_id"
    }
}