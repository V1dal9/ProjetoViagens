package com.example.projetoprogramacaoavancada

import android.content.ContentValues

class Viagem (
    var id : Long,
    val nome : String,
    val dataInicio : Long,
    val dataFim : Long,
    val localEmbarque : String,
    val localDesembarque : String
){

    fun toContentValues(): ContentValues{
        val valores = ContentValues()
        valores.put(Tabela_Info_Viagem.NOME, nome)
        valores.put(Tabela_Info_Viagem.CAMPO_ID, id)
        valores.put(Tabela_Info_Viagem.LOCAL_DESEMBARQUE, localDesembarque)
        valores.put(Tabela_Info_Viagem.LOCAL_EMBARQUE, localEmbarque)
        valores.put(Tabela_Info_Viagem.DATA_FIM, dataFim)
        valores.put(Tabela_Info_Viagem.DATA_INICIO, dataInicio)
        return valores
    }
}
