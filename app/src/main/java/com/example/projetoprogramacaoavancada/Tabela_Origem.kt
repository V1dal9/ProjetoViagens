package com.example.projetoprogramacaoavancada

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class Tabela_Origem(db:SQLiteDatabase):TabelaBD(db, NOME_ORIGEM) {
    override fun criar() {
        db.execSQL("CREATE TABLE $nome(${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$NOME_ORIGEM TEXT NOT NULL, " +
                "$LOCAL_ORIGEM TEXT NOT NULL, " +
                "FOREIGN KEY($ID_ORIGEM) REFERENCES ${Tabela_Origem})")
    }
    companion object{
        const val ID_ORIGEM = "id"
        const val NOME_ORIGEM = "nome"
        const val LOCAL_ORIGEM = "local"
    }
}