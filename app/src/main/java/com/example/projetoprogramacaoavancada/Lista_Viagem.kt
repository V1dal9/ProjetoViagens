package com.example.projetoprogramacaoavancada

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import androidx.core.content.contentValuesOf

class Lista_Viagem(
    var id : Long = -1,
    var nome_lista : String,
    var roupa : String,
    var acessorios : String,
    var eletronico : String,
    var higiene : String,
    var calcado : String,
    var idPassageiro : Long,
    var idInfoViagem : Long
) {
    fun toContentValues() : ContentValues{
        val valoresLista = ContentValues()
        valoresLista.put(TabelaListaViagem.CAMPO_NOME, nome_lista)
        valoresLista.put(TabelaListaViagem.ACESSORIO, acessorios)
        valoresLista.put(TabelaListaViagem.CALCADO, calcado)
        valoresLista.put(TabelaListaViagem.ELETRONICO, eletronico)
        valoresLista.put(TabelaListaViagem.HIGIENE, higiene)
        valoresLista.put(TabelaListaViagem.ROUPA, roupa)
        valoresLista.put(TabelaListaViagem.PASSAGEIRO_ID, idPassageiro)
        valoresLista.put(TabelaListaViagem.INFOVIAGEM_ID, idInfoViagem)

        return valoresLista

    }

    companion object{
        fun fromCursor(cursor: Cursor): Lista_Viagem{
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNome = cursor.getColumnIndex(TabelaListaViagem.CAMPO_NOME)
            val posAcessorio = cursor.getColumnIndex(TabelaListaViagem.ACESSORIO)
            val posCalcado = cursor.getColumnIndex(TabelaListaViagem.CALCADO)
            val posEletronico = cursor.getColumnIndex(TabelaListaViagem.ELETRONICO)
            val posHigiene = cursor.getColumnIndex(TabelaListaViagem.HIGIENE)
            val posRoupa = cursor.getColumnIndex(TabelaListaViagem.ROUPA)
            val posIdPassageiro = cursor.getColumnIndex(TabelaListaViagem.PASSAGEIRO_ID)
            val posIdInfoViagem = cursor.getColumnIndex(TabelaListaViagem.INFOVIAGEM_ID)

            val id = cursor.getLong(posId)
            val nome = cursor.getString(posNome)
            val acessorio = cursor.getString(posAcessorio)
            val calcado = cursor.getString(posCalcado)
            val eletronico = cursor.getString(posEletronico)
            val higiene = cursor.getString(posHigiene)
            val roupa = cursor.getString(posRoupa)
            val idPassageiro = cursor.getLong(posIdPassageiro)
            val idInfoViagem = cursor.getLong(posIdInfoViagem)

            return Lista_Viagem(id, nome, acessorio, calcado, eletronico, higiene, roupa, idPassageiro, idInfoViagem)
        }
    }
}