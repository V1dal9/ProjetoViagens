package com.example.projetoprogramacaoavancada

import android.content.ContentValues

class Local(
    var id : Long,
    val localOrigem : String,
    val localDestino : String
) {
    fun toContentValues() : ContentValues{
        val valoresOrigem = ContentValues()
        valoresOrigem.put(TabelaLocal.LOCAL_ID, id)
        valoresOrigem.put(TabelaLocal.LOCAL_ORIGEM, localOrigem)
        valoresOrigem.put(TabelaLocal.LOCAL_DESTINO, localDestino)
        return valoresOrigem
    }
}