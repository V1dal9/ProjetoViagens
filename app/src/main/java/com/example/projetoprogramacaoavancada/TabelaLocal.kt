package com.example.projetoprogramacaoavancada

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.provider.BaseColumns

class TabelaLocal (db: SQLiteDatabase):TabelaBD(db, NOME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $nome(${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$LOCAL_ORIGEM TEXT NOT NULL, " +
                "$LOCAL_DESTINO TEXT NOT NULL)")
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

        queryBuilder.tables = "$NOME INNER JOIN ${TabelaLocalViagem.NOME_DESTINO} ON ${TabelaLocalViagem.LOCAL_ID} = $LOCAL_ID"
        return queryBuilder.query(db, columns, selection, selectionArgs, groupBy, having, orderBy)
    }
    companion object{
        const val NOME = "local"

        const val LOCAL_ID = "id"
        const val LOCAL_ORIGEM = "origem"
        const val LOCAL_DESTINO = "destino"
        val TODAS_COLUNAS = arrayOf(LOCAL_ID, LOCAL_ORIGEM, LOCAL_DESTINO)
    }
}