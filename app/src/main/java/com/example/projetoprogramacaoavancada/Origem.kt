package com.example.projetoprogramacaoavancada

import android.content.ContentValues

class Origem(
    var id : Long,
    var nome : String,
    val local : String
) {
    fun toContentValues() : ContentValues{
        val valoresOrigem = ContentValues()
        valoresOrigem.put(TabelaOrigem.ID_ORIGEM, id)
        valoresOrigem.put(TabelaOrigem.NOME_ORIGEM, nome)
        valoresOrigem.put(TabelaOrigem.LOCAL_ORIGEM, local)
        return valoresOrigem
    }
}