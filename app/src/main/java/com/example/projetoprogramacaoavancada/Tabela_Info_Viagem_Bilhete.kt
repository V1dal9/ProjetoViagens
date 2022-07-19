package com.example.projetoprogramacaoavancada

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.provider.BaseColumns

class Tabela_Info_Viagem_Bilhete (db: SQLiteDatabase): TabelaBD(db, NOME){
    override fun cria(){
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$DATA_INICIO TEXT NOT NULL, " +
                "$DATA_FIM TEXT NOT NULL, " +
                "$LOCAL_ORIGEM TEXT NOT NULL, " +
                "$LOCAL_DESTINO TEXT NOT NULL, " +
                "$PASSAGEIRO_ID INTEGER NOT NULL, " +
                "$TIPO_MALA TEXT NOT NULL, " +
                "${CLASS_VIAGEM} TEXT NOT NULL, " +
                "FOREIGN KEY(${PASSAGEIRO_ID}) " +
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
        const val NOME = "InfoBilhete"
        const val DATA_INICIO = "dataInicio"
        const val DATA_FIM = "dataFim"
        const val LOCAL_ORIGEM = "localEmbarque"
        const val LOCAL_DESTINO = "localDesembarque"
        const val CAMPO_ID = "$NOME.${BaseColumns._ID}"
        const val TIPO_MALA = "TipoMala"
        const val CLASS_VIAGEM = "ClassViagem"
        const val PASSAGEIRO_ID = "infoPassageiroID"

        val TODAS_COLUNAS = arrayOf(DATA_INICIO, DATA_FIM, LOCAL_ORIGEM, LOCAL_DESTINO, CAMPO_ID, PASSAGEIRO_ID)
    }
}