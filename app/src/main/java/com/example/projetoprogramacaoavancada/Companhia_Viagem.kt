package com.example.projetoprogramacaoavancada

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

class Companhia_Viagem(
    var id : Long = -1,
    var nome : String,
    var idPassageiro : Long
) {
    fun toContenteValues() : ContentValues{
        val valores = ContentValues()

        valores.put(Tabela_Companhia_Viagem.NOME, nome)
        valores.put(Tabela_Companhia_Viagem.PASSAGEIRO_ID, idPassageiro)

        return valores
    }

    companion object{
        fun fromCursor(cursor: Cursor) : Companhia_Viagem {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNome = cursor.getColumnIndex(Tabela_Companhia_Viagem.NOME)
            val posIdPassageiro = cursor.getColumnIndex(Tabela_Companhia_Viagem.PASSAGEIRO_ID)

            val id = cursor.getLong(posId)
            val nome = cursor.getString(posNome)
            val idPassageiro = cursor.getLong(posIdPassageiro)

            return Companhia_Viagem(id, nome, idPassageiro)
        }
    }

}
