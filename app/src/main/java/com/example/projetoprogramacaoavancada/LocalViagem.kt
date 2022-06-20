package com.example.projetoprogramacaoavancada

import android.content.ContentValues

class LocalViagem(
    var idViagem : Long,
    val idLocal : Long
) {
    fun toContentValues() : ContentValues {
        val valoresLocal = ContentValues()
        valoresLocal.put(Tabela_Info_Viagem_Bilhete.CAMPO_ID, idViagem)
        valoresLocal.put(TabelaLocal.LOCAL_ID, idLocal)
        return valoresLocal
    }
}