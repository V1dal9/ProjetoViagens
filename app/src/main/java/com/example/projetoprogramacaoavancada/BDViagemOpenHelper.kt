package com.example.projetoprogramacaoavancada

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BDViagemOpenHelper(context: Context?) : SQLiteOpenHelper(context, NOME, null, VERSAO) {
    override fun onCreate(db: SQLiteDatabase?) {
        requireNotNull(db)
        TabelaLocal(db).cria()
        TabelaListaViagem(db).cria()
        Tabela_Companhia_Viagem(db).cria()
        Tabela_Passageiro(db).cria()
        Tabela_Info_Viagem_Bilhete(db).cria()

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    companion object{
        const val NOME = "viagem.db"
        private const val VERSAO = 1
    }
}