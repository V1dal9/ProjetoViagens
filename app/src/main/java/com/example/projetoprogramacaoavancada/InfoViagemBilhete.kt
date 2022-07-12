package com.example.projetoprogramacaoavancada

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

class InfoViagemBilhete(
    var dataInicio: String,
    var dataFim: String,
    var localOrigem: String,
    var localDestino: String,
    var tipoMala: String,
    var classViagem: String,
    var Passageiro: Passageiro,
    var id: Long = -1
) {
    fun toContentValues(): ContentValues {
        val valores = ContentValues()
        valores.put(Tabela_Info_Viagem_Bilhete.LOCAL_ORIGEM, localOrigem)
        valores.put(Tabela_Info_Viagem_Bilhete.LOCAL_DESTINO, localDestino)
        valores.put(Tabela_Info_Viagem_Bilhete.DATA_FIM, dataFim)
        valores.put(Tabela_Info_Viagem_Bilhete.DATA_INICIO, dataInicio)
        valores.put(Tabela_Info_Viagem_Bilhete.TIPO_MALA , tipoMala)
        valores.put(Tabela_Info_Viagem_Bilhete.CLASS_VIAGEM , classViagem)
        valores.put(Tabela_Info_Viagem_Bilhete.PASSAGEIRO_ID, Passageiro.id)
        return valores
    }
    companion object{
        fun fromCursor(cursor: Cursor) : InfoViagemBilhete {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posOrigem = cursor.getColumnIndex(Tabela_Info_Viagem_Bilhete.LOCAL_ORIGEM)
            val posDestino = cursor.getColumnIndex(Tabela_Info_Viagem_Bilhete.LOCAL_DESTINO)
            val posDataFim = cursor.getColumnIndex(Tabela_Info_Viagem_Bilhete.DATA_FIM)
            val posDataInicio = cursor.getColumnIndex(Tabela_Info_Viagem_Bilhete.DATA_INICIO)
            val posTipoMala = cursor.getColumnIndex(Tabela_Info_Viagem_Bilhete.TIPO_MALA)
            val posClassViagem = cursor.getColumnIndex(Tabela_Info_Viagem_Bilhete.CLASS_VIAGEM)
            val posIdPassageiro = cursor.getColumnIndex(Tabela_Info_Viagem_Bilhete.PASSAGEIRO_ID)
            val posNomePassageiro = cursor.getColumnIndex(Tabela_Passageiro.CAMPO_NOME_PASSAGEIRO)
            val posGenero = cursor.getColumnIndex(Tabela_Passageiro.GENERO)
            val posIdade = cursor.getColumnIndex(Tabela_Passageiro.IDADE)

            val id = cursor.getLong(posId)
            val origem = cursor.getString(posOrigem)
            val destino = cursor.getString(posDestino)
            val dataFim = cursor.getString(posDataFim)
            val dataInicio = cursor.getString(posDataInicio)
            val tipoMala = cursor.getString(posTipoMala)
            val classViagem = cursor.getString(posClassViagem)

            val idPassageiro = cursor.getLong(posIdPassageiro)
            val nomePassageiro = cursor.getString(posNomePassageiro)
            val generoPassageiro = cursor.getString(posGenero)
            val idadePassageiro = cursor.getLong(posIdade)
            val Passageiro = Passageiro(nomePassageiro, generoPassageiro, idadePassageiro, idPassageiro)


            return InfoViagemBilhete(dataInicio, dataFim, origem , destino, tipoMala, classViagem, Passageiro, id)

        }
    }
}