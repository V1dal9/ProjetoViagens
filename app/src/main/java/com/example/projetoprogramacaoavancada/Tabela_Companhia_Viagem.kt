package com.example.projetoprogramacaoavancada

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class Tabela_Companhia_Viagem(db: SQLiteDatabase): TabelaBD(db, NOME) {
    override fun criar() {
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$PASSAGEIRO_ID INTEGER NOT NULL, "+
                "FOREIGN KEY($PASSAGEIRO_ID) REFERENCES ${Tabela_Passageiro.NOME}(${BaseColumns._ID}))")
    }
    companion object{
        const val NOME = "CompanhiaViagem"
        const val ID_COMPANHIA = "id"
        const val PASSAGEIRO_ID = "passageiroID"
    }
}