package com.example.projetoprogramacaoavancada

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.provider.BaseColumns

class TabelaLocalViagem(db : SQLiteDatabase): TabelaBD(db, NOME) {
    override fun cria() {
    db.execSQL("CREATE TABLE $nome(${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "$LOCAL_ID INTEGER NOT NULL, " +
            "FOREIGN KEY($INFOVIAGEM_ID) REFERENCES ${Tabela_Info_Viagem_Bilhete.NOME}(${BaseColumns._ID})ON DELETE RESTRICT," +
            "FOREIGN KEY($LOCAL_ID) REFERENCES ${TabelaLocal.NOME}(${BaseColumns._ID})ON DELETE RESTRICT)")
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
        queryBuilder.tables = "$NOME INNER JOIN ${Tabela_Info_Viagem_Bilhete.NOME} ON ${Tabela_Info_Viagem_Bilhete.CAMPO_ID} = $INFOVIAGEM_ID"
        queryBuilder.tables = "$NOME INNER JOIN ${TabelaLocal.NOME} ON ${TabelaLocal.LOCAL_ID} = $LOCAL_ID"

        return queryBuilder.query(db, columns, selection, selectionArgs, groupBy, having, orderBy)
    }
    companion object{
        const val NOME = "LocalViagem"

        const val CAMPO_ID = "$NOME.${BaseColumns._ID}"
        const val INFOVIAGEM_ID = "infoViagemId"
        const val LOCAL_ID = "travelId"

        val TODAS_COLUNAS = arrayOf(CAMPO_ID, INFOVIAGEM_ID, LOCAL_ID)
    }
}