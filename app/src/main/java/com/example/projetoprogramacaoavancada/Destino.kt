package com.example.projetoprogramacaoavancada

import android.content.ContentValues

class Destino(
    var id : Long,
    val local : String
) {
    fun toContentValues() : ContentValues {
        val valoresDestino = ContentValues()
        valoresDestino.put(Tabela_Destino.ID_DESTINO, id)
        valoresDestino.put(Tabela_Destino.LOCAL_DESTINO, local)
        return valoresDestino
    }
}