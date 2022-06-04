package com.example.projetoprogramacaoavancada

import android.content.ContentValues
import androidx.core.content.contentValuesOf

class Lista_Viagem(
    var id : Long,
    var nome_lista : String,
    val roupa : String,
    val acessorios : String,
    val eletronico : String,
    val higiene : String,
    val calçado : String
) {
    fun toContentValues() : ContentValues{
        val valoresLista = ContentValues()
        valoresLista.put(Tabela_Lista_Viagem.NOME_LISTA, nome_lista)
        valoresLista.put(Tabela_Lista_Viagem.ACESSORIO, acessorios)
        valoresLista.put(Tabela_Lista_Viagem.CALÇADO, calçado)
        valoresLista.put(Tabela_Lista_Viagem.ELETRONICO, eletronico)
        valoresLista.put(Tabela_Lista_Viagem.HIGIENE, higiene)
        valoresLista.put(Tabela_Lista_Viagem.ROUPA, roupa)
        valoresLista.put(Tabela_Lista_Viagem.ID_LISTA, id)
        return valoresLista

    }
}