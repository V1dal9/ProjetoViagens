package com.example.projetoprogramacaoavancada

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

class LocalViagem(
    var idLocalViagem : Long = -1,
    var idInfoViagem : Long,
    val idLocal : Long
) {
    fun toContentValues() : ContentValues {
        val valoresLocal = ContentValues()
        valoresLocal.put(Tabela_Info_Viagem_Bilhete.CAMPO_ID, idInfoViagem)
        valoresLocal.put(TabelaLocal.LOCAL_ID, idLocal)
        return valoresLocal
    }

    companion object{
        fun fromCursor(cursor: Cursor):LocalViagem {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posInfoViagem = cursor.getColumnIndex(TabelaLocalViagem.INFOVIAGEM_ID)
            val posLocal = cursor.getColumnIndex(TabelaLocalViagem.LOCAL_ID)

            val id = cursor.getLong(posId)
            val infoViagemId = cursor.getLong(posInfoViagem)
            val localId = cursor.getLong(posLocal)

            return LocalViagem(id, infoViagemId, localId)
        }
    }
}