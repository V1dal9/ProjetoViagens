package com.example.projetoprogramacaoavancada

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

class Local(
    var id : Long = -1,
    val localOrigem : String,
    val localDestino : String
) {
    fun toContentValues() : ContentValues{
        val valoresLocal = ContentValues()

        valoresLocal.put(TabelaLocal.LOCAL_ORIGEM, localOrigem)
        valoresLocal.put(TabelaLocal.LOCAL_DESTINO, localDestino)
        return valoresLocal
    }

    companion object{
        fun fromCursor(cursor: Cursor): Local{
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posOrigem = cursor.getColumnIndex(TabelaLocal.LOCAL_ORIGEM)
            val posDestino = cursor.getColumnIndex(TabelaLocal.LOCAL_DESTINO)

            val id = cursor.getLong(posId)
            val origem = cursor.getString(posOrigem)
            val destino = cursor.getString(posDestino)

            return Local(id, origem, destino)
        }
    }
}