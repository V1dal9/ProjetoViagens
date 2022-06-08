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
        valores.put(Tabela_Info_Viagem_Bilhete.NOME, nome)
        valores.put(Tabela_Info_Viagem_Bilhete.CAMPO_ID, id)
        valores.put(Tabela_Info_Viagem_Bilhete.LOCAL_DESEMBARQUE, localDesembarque)
        valores.put(Tabela_Info_Viagem_Bilhete.LOCAL_EMBARQUE, localEmbarque)
        valores.put(Tabela_Info_Viagem_Bilhete.DATA_FIM, dataFim)
        valores.put(Tabela_Info_Viagem_Bilhete.DATA_INICIO, dataInicio)
        return valores
    }
}
