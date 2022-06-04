package com.example.projetoprogramacaoavancada

import android.content.ContentValues

class Origem(
    var id : Long,
    var nome : String,
    val local : String
) {
    fun toContentValues() : ContentValues{
        val valoresOrigem = ContentValues()
        valoresOrigem.put(Tabela_Origem.ID_ORIGEM, id)
        valoresOrigem.put(Tabela_Origem.NOME_ORIGEM, nome)
        valoresOrigem.put(Tabela_Origem.LOCAL_ORIGEM, local)
        return valoresOrigem
    }
}