package com.example.projetoprogramacaoavancada

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.provider.BaseColumns

class Tabela_Companhia_Viagem(db: SQLiteDatabase): TabelaBD(db, NOME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$PASSAGEIRO_ID INTEGER NOT NULL, "+
                "$NOME INTEGER NOT NULL, "+
                "FOREIGN KEY($PASSAGEIRO_ID) " +
                "REFERENCES ${Tabela_Passageiro.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT)")
    }

    override fun query(
        columns: Array<String>,
        selection: String?,
        selectionArgs: Array<String>?,
        groupBy: String?,
        having: String?,
        orderBy: String?
    ): Cursor {
        val queryBuilder = SQLiteQueryBuilder()
        queryBuilder.tables = "$NOME INNER JOIN ${Tabela_Passageiro.NOME} ON ${Tabela_Passageiro.CAMPO_ID} = $PASSAGEIRO_ID"

        return queryBuilder.query(db, columns, selection, selectionArgs, groupBy, having, orderBy)

    }

    companion object{
        const val NOME = "CompanhiaViagem"
        const val ID_COMPANHIA = "$NOME.${BaseColumns._ID}"
        const val PASSAGEIRO_ID = "passageiroID"

        val TODAS_COLUNAS = arrayOf(NOME, ID_COMPANHIA, PASSAGEIRO_ID)
    }
}