package com.example.projetoprogramacaoavancada

import android.content.ContentValues

class Destino(
    var id : Long,
    var nome : String,
    val local : String
) {
    fun toContentValues() : ContentValues {
        val valoresDestino = ContentValues()
        valoresDestino.put(Tabela_Origem.ID_ORIGEM, id)
        valoresDestino.put(Tabela_Origem.NOME_ORIGEM, nome)
        valoresDestino.put(Tabela_Origem.LOCAL_ORIGEM, local)
        return valoresDestino
    }
}