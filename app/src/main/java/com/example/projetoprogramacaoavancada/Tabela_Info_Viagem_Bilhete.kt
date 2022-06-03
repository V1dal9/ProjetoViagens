package com.example.projetoprogramacaoavancada

import android.database.sqlite.SQLiteClosable
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class Tabela_Info_Viagem(db: SQLiteDatabase): TabelaBD(db, NOME) {
    override fun criar(){
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} ON DELETE RESTRICT INTEGER PRIMATY KEY AUTOINCREMENT, $DATA_INICIO DATE NOT NULL, $DATA_FIM DATE NOT NULL, $LOCAL_EMBARQUE TEXT NOT NULL, $LOCAL_DESEMBARQUE TEXT NOT NULL, $CAMPO_ID INTEGRE NOT NULL, FOREIGN KEY($CAMPO_ID) REFERENCES ${Tabela_Info_Viagem})")
    }
    companion object{
        const val NOME = "viagem"
        const val DATA_INICIO = "dataInicio"
        const val DATA_FIM = "dataFim"
        const val LOCAL_EMBARQUE = "localEmbarque"
        const val LOCAL_DESEMBARQUE = "localDesembarque"
        const val CAMPO_ID = "infoViagemId"
    }

}


