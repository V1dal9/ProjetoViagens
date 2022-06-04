package com.example.projetoprogramacaoavancada

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class Tabela_Lista_Viagem(db: SQLiteDatabase): TabelaBD(db, NOME_LISTA) {
    override fun criar() {
        db.execSQL("CREATE TABLE $nome(${BaseColumns._ID} ON DELETE RESTRICT INTEGER PRIMARY KEY AUTOINCREMENT, $NOME_LISTA TEXT, $ROUPA TEXT, $CALÇADO TEXT, $ACESSORIO TEXT, $ELETRONICO TEXT, $HIGIENE TEXT, FOREIGN KEY ($ID_LISTA) REFERENCE ${Tabela_Lista_Viagem})")
    }
    companion object{
        const val ID_LISTA = "id"
        const val NOME_LISTA = "nome"
        const val ROUPA = "roupas"
        const val CALÇADO = "calçados"
        const val ACESSORIO = "acessórios"
        const val ELETRONICO = "utencilios informaticos"
        const val HIGIENE = "produtos higienicos"
    }
}