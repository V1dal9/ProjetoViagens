package com.example.projetoprogramacaoavancada

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class Tabela_Passageiro(db: SQLiteDatabase): TabelaBD(db, NOME) {
    override fun criar() {
        db.execSQL("CREATE TABLE $nome(${BaseColumns._ID} ON DELETE RESTRICT INTEGER PRIMARY KEY AUTOINCREMENT, $GENERO TEXT NOT NULL, $IDADE INTEGER NOT NULL, FOREIGN KEY($ID_PASSAGEIRO) REFERENCES ${Tabela_Passageiro})")
    }
    companion object{
        const val NOME = "passageiro"
        const val GENERO = "genero"
        const val IDADE = "idade"
        const val ID_PASSAGEIRO = "id passageiro"
    }
}