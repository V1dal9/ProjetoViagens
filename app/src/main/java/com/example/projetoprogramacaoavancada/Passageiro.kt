package com.example.projetoprogramacaoavancada

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import java.net.PasswordAuthentication

data class Passageiro(
    var nome : String,
    var genero : String,
    var idade : Long,
    var id : Long = -1
) {
    fun toContentValues() : ContentValues{
        val valores = ContentValues()
        valores.put(Tabela_Passageiro.NOME, nome)
        valores.put(Tabela_Passageiro.GENERO, genero)
        valores.put(Tabela_Passageiro.IDADE, idade)

        return valores
    }
    companion object{
        fun fromCursor(cursor: Cursor): Passageiro{
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNome = cursor.getColumnIndex(Tabela_Passageiro.NOME)
            val posGenero = cursor.getColumnIndex(Tabela_Passageiro.GENERO)
            val posIdade = cursor.getColumnIndex(Tabela_Passageiro.IDADE)

            val id = cursor.getLong(posId)
            val nome = cursor.getString(posNome)
            val genero = cursor.getString(posGenero)
            val idade = cursor.getLong(posIdade)

            return Passageiro(nome, genero, idade, id)
        }
    }

}