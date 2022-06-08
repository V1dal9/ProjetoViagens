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
    val calcado : String
) {
    fun toContentValues() : ContentValues{
        val valoresLista = ContentValues()
        valoresLista.put(TabelaListaViagem.NOME, nome_lista)
        valoresLista.put(TabelaListaViagem.ACESSORIO, acessorios)
        valoresLista.put(TabelaListaViagem.CALCADO, calcado)
        valoresLista.put(TabelaListaViagem.ELETRONICO, eletronico)
        valoresLista.put(TabelaListaViagem.HIGIENE, higiene)
        valoresLista.put(TabelaListaViagem.ROUPA, roupa)
        valoresLista.put(TabelaListaViagem.ID_LISTA, id)
        return valoresLista

    }
}