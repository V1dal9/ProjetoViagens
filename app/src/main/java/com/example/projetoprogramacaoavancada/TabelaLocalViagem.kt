package com.example.projetoprogramacaoavancada

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaLocalViagem(db : SQLiteDatabase): TabelaBD(db, NOME_DESTINO) {
    override fun cria() {
    db.execSQL("CREATE TABLE $nome(${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "$LOCAL_ID INTEGER NOT NULL, " +
            "FOREIGN KEY($LOCAL_ID) REFERENCES ${Tabela_Info_Viagem_Bilhete.NOME}(${BaseColumns._ID})" +
            ")")
    }
    companion object{
        const val NOME_DESTINO = "Destino"
        const val LOCAL_ID = "travel_id"
    }
}