package com.example.projetoprogramacaoavancada

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class Tabela_Passageiro(db: SQLiteDatabase): TabelaBD(db, NOME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$GENERO TEXT NOT NULL, " +
                "$CAMPO_NOME TEXT NOT NULL, " +
                "$IDADE INTEGER NOT NULL)")
    }
    companion object{
        const val NOME = "passageiro"

        const val CAMPO_NOME = "nome"
        const val GENERO = "genero"
        const val IDADE = "idade"
        const val ID_PASSAGEIRO = "$NOME.${BaseColumns._ID}"

        val TODAS_COLUNAS = arrayOf(ID_PASSAGEIRO, CAMPO_NOME)
    }


}