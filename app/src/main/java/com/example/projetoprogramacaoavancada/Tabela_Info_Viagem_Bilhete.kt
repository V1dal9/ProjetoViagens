package com.example.projetoprogramacaoavancada

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class Tabela_Info_Viagem_Bilhete (db: SQLiteDatabase): TabelaBD(db, Tabela_Info_Viagem_Bilhete.NOME){
    override fun criar(){
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${Tabela_Info_Viagem_Bilhete.DATA_INICIO} LONG NOT NULL, " +
                "${Tabela_Info_Viagem_Bilhete.DATA_FIM} LONG NOT NULL, " +
                "${Tabela_Info_Viagem_Bilhete.LOCAL_EMBARQUE} TEXT NOT NULL, " +
                "${Tabela_Info_Viagem_Bilhete.LOCAL_DESEMBARQUE} TEXT NOT NULL, " +
                "${Tabela_Info_Viagem_Bilhete.PASSAGEIRO_ID} INTEGER NOT NULL, " +
                "${Tabela_Info_Viagem_Bilhete.CAMPO_ID} INTEGRE NOT NULL, " +
                "FOREIGN KEY(${Tabela_Info_Viagem_Bilhete.PASSAGEIRO_ID}) " +
                "REFERENCES ${Tabela_Passageiro.NOME}(${BaseColumns._ID}))")
    }
    companion object{
        const val NOME = "viagem"
        const val DATA_INICIO = "dataInicio"
        const val DATA_FIM = "dataFim"
        const val LOCAL_EMBARQUE = "localEmbarque"
        const val LOCAL_DESEMBARQUE = "localDesembarque"
        const val CAMPO_ID = "infoViagemId"
        const val PASSAGEIRO_ID = "passageiroID"
    }
}