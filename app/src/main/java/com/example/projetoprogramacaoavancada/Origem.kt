package com.example.projetoprogramacaoavancada

import android.content.ContentValues

class Origem(
    var id : Long,
    val local : String
) {
    fun toContentValues() : ContentValues{
        val valoresOrigem = ContentValues()
        valoresOrigem.put(TabelaOrigem.ID_ORIGEM, id)
        valoresOrigem.put(TabelaOrigem.LOCAL_ORIGEM, local)
        return valoresOrigem
    }
}