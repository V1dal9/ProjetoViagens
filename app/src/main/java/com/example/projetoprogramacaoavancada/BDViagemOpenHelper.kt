package com.example.projetoprogramacaoavancada

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

abstract class BDViagemOpenHelper(context: Context?) : SQLiteOpenHelper(context, NOME, null, VERSAO) {
    override fun onCreate(db: SQLiteDatabase?) {
        requireNotNull(db)
        Tabela_Destino(db).criar()
        Tabela_Origem(db).criar()
        Tabela_Lista_Viagem(db).criar()
        Tabela_Companhia_Viagem(db).criar()
        Tabela_Passageiro(db).criar()
        Tabela_Info_Viagem(db).criar()
    }
    companion object{
        const val NOME = "viagem.db"
        private const val VERSAO = 1
    }
}