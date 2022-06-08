package com.example.projetoprogramacaoavancada

import android.provider.BaseColumns
import android.database.sqlite.SQLiteDatabase


class TabelaListaViagem (db:SQLiteDatabase):TabelaBD(db, NOME) {
    override fun criar() {
        db.execSQL("CREATE TABLE $nome(${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$NOME TEXT, " +
                "$ROUPA TEXT, " +
                "$CALCADO TEXT, " +
                "$ACESSORIO TEXT, " +
                "$ELETRONICO TEXT, " +
                "$HIGIENE TEXT, " +
                "$PASSAGEIRO_ID INTEGER, " +
                "FOREIGN KEY ($PASSAGEIRO_ID) " +
                "REFERENCES ${Tabela_Passageiro.NOME}(${BaseColumns._ID}))")
    }
    companion object{
        const val ID_LISTA = "id"
        const val NOME = "nome"
        const val ROUPA = "roupas"
        const val CALCADO = "calçados"
        const val ACESSORIO = "acessórios"
        const val ELETRONICO = "utencilios informaticos"
        const val HIGIENE = "produtos higienicos"
        const val PASSAGEIRO_ID = "passageiroID"
    }
}