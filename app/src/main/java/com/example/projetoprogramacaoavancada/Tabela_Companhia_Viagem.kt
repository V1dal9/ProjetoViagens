package com.example.projetoprogramacaoavancada

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class Tabela_Companhia_Viagem(db: SQLiteDatabase): TabelaBD(db, NOME) {
    override fun criar() {
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} ON DELETE RESTRICT INTEGER PRIMARY KEY AUTOINCREMENT, FOREIGN KEY($ID_COMPANHIA) REFERENCE ${Tabela_Companhia_Viagem} )")
    }
    companion object{
        const val NOME = "companhia"
        const val ID_COMPANHIA = "id"
    }
}