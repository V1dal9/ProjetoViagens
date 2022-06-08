package com.example.projetoprogramacaoavancada

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class Tabela_Passageiro(db: SQLiteDatabase): TabelaBD(db, NOME) {
    override fun criar() {
        db.execSQL("CREATE TABLE $nome(${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$GENERO TEXT NOT NULL, " +
                "$NOME TEXT NOT NULL, " +
                "$IDADE INTEGER NOT NULL,I" +
                "$ID_PASSAGEIRO INTEGER NOT NULL)")
    }
    companion object{
        const val NOME = "passageiro"
        const val GENERO = "genero"
        const val IDADE = "idade"
        const val ID_PASSAGEIRO = "passageiro_ID"
    }
}