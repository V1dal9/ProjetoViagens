package com.example.projetoprogramacaoavancada

import android.content.ContentValues

class Companhia_Viagem(
    var id : Long,
    var nome : String
) {
    fun toContenteValues() : ContentValues{
        val valores = ContentValues()
        valores.put(Tabela_Companhia_Viagem.NOME, nome)
        valores.put(Tabela_Companhia_Viagem.ID_COMPANHIA, id)
        return valores
    }

}
