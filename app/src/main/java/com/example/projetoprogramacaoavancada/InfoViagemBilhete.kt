package com.example.projetoprogramacaoavancada

import android.content.ContentValues

class InfoViagemBilhete(
    var id : Long = -1,
    val nome : String,
    var dataInicio : Long,
    var dataFim : Long,
    var localOrigem : String,
    var localDestino : String,
    var tipoMala : String,
    var classViagem : String,
) {
    fun toContentValues(): ContentValues {
        val valores = ContentValues()
        valores.put(Tabela_Info_Viagem_Bilhete.NOME, nome)
        valores.put(Tabela_Info_Viagem_Bilhete.LOCAL_ORIGEM, localOrigem)
        valores.put(Tabela_Info_Viagem_Bilhete.LOCAL_DESTINO, localDestino)
        valores.put(Tabela_Info_Viagem_Bilhete.DATA_FIM, dataFim)
        valores.put(Tabela_Info_Viagem_Bilhete.DATA_INICIO, dataInicio)
        valores.put(Tabela_Info_Viagem_Bilhete.TIPO_MALA , tipoMala)
        valores.put(Tabela_Info_Viagem_Bilhete.CLASS_VIAGEM , classViagem)
        return valores
    }
}