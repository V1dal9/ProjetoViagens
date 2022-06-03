package com.example.projetoprogramacaoavancada

import android.content.ContentValues

class Passageiro(
    var id : Long,
    var nome : String,
    val genero : String,
    val idade : Long
) {
    fun toContentValues() : ContentValues{
        val valores = ContentValues()
        valores.put(Tabela_Passageiro.NOME, nome)
        valores.put(Tabela_Passageiro.GENERO, genero)
        valores.put(Tabela_Passageiro.IDADE, idade)
        valores.put(Tabela_Passageiro.ID_PASSAGEIRO, id)
        return valores
    }

}