package com.example.projetoprogramacaoavancada

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Lista_Viagem(
    var nomeLista : String,
    var roupa : String,
    var acessorios : String,
    var acessoriosInformaticos : String,
    var produtosHigienicos : String,
    var calcados : String,
    var Passageiro : Passageiro,
    var InfoViagem : InfoViagemBilhete,
    var id : Long = -1
) {
    fun toContentValues() : ContentValues{
        val valoresLista = ContentValues()
        valoresLista.put(TabelaListaViagem.CAMPO_NOME, nomeLista)
        valoresLista.put(TabelaListaViagem.ACESSORIO, acessorios)
        valoresLista.put(TabelaListaViagem.CALCADO, calcados)
        valoresLista.put(TabelaListaViagem.ELETRONICO, acessoriosInformaticos)
        valoresLista.put(TabelaListaViagem.HIGIENE, produtosHigienicos)
        valoresLista.put(TabelaListaViagem.ROUPA, roupa)
        valoresLista.put(TabelaListaViagem.PASSAGEIRO_ID, Passageiro.id)
        valoresLista.put(TabelaListaViagem.INFOVIAGEM_ID, InfoViagem.id)

        return valoresLista

    }

    companion object{
        fun fromCursor(cursor: Cursor): Lista_Viagem{
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNome = cursor.getColumnIndex(TabelaListaViagem.CAMPO_NOME)
            val posRoupa = cursor.getColumnIndex(TabelaListaViagem.ROUPA)
            val posAcessorio = cursor.getColumnIndex(TabelaListaViagem.ACESSORIO)
            val posEletronico = cursor.getColumnIndex(TabelaListaViagem.ELETRONICO)
            val posHigiene = cursor.getColumnIndex(TabelaListaViagem.HIGIENE)
            val posCalcado = cursor.getColumnIndex(TabelaListaViagem.CALCADO)
            val posIdPassageiro = cursor.getColumnIndex(TabelaListaViagem.PASSAGEIRO_ID)
            val posNomePassageiro = cursor.getColumnIndex(Tabela_Passageiro.CAMPO_NOME_PASSAGEIRO)
            val posGenero = cursor.getColumnIndex(Tabela_Passageiro.GENERO)
            val posIdade = cursor.getColumnIndex(Tabela_Passageiro.IDADE)
            val posIdInfoViagem = cursor.getColumnIndex(TabelaListaViagem.INFOVIAGEM_ID)
            val posLocalDestino = cursor.getColumnIndex(Tabela_Info_Viagem_Bilhete.LOCAL_DESTINO)
            val posLocalOrigem = cursor.getColumnIndex(Tabela_Info_Viagem_Bilhete.LOCAL_ORIGEM)
            val posDataInicio = cursor.getColumnIndex(Tabela_Info_Viagem_Bilhete.DATA_INICIO)
            val posDataFim = cursor.getColumnIndex(Tabela_Info_Viagem_Bilhete.DATA_FIM)
            val posTipoMala = cursor.getColumnIndex(Tabela_Info_Viagem_Bilhete.TIPO_MALA)
            val posClass = cursor.getColumnIndex(Tabela_Info_Viagem_Bilhete.CLASS_VIAGEM)

            val id = cursor.getLong(posId)
            val nomeLista = cursor.getString(posNome)
            val roupa = cursor.getString(posRoupa)
            val acessorios = cursor.getString(posAcessorio)
            val acessoriosInformaticos = cursor.getString(posEletronico)
            val higiene = cursor.getString(posHigiene)
            val calcados = cursor.getString(posCalcado)





            val idPassageiro = cursor.getLong(posIdPassageiro)
            val nomePassageiro = cursor.getString(posNomePassageiro)
            val generoPassageiro = cursor.getString(posGenero)
            val idadePassageiro = cursor.getLong(posIdade)
            val Passageiro = Passageiro(nomePassageiro, generoPassageiro, idadePassageiro, idPassageiro)

            val idInfoBilhete = cursor.getLong(posIdInfoViagem)
            val localDestino = cursor.getString(posLocalDestino)
            val localOrigem = cursor.getString(posLocalOrigem)
            val dataInicio = cursor.getString(posDataInicio)
            val dataFim = cursor.getString(posDataFim)
            val tipoMala = cursor.getString(posTipoMala)
            val classViagem = cursor.getString(posClass)
            val InfoViagem = InfoViagemBilhete(dataInicio, dataFim, localDestino, localOrigem, tipoMala, classViagem, Passageiro, idInfoBilhete)

            return Lista_Viagem(nomeLista, acessorios, calcados, acessoriosInformaticos, higiene, roupa, Passageiro, InfoViagem, id)
        }
    }
}