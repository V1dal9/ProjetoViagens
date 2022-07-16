package com.example.projetoprogramacaoavancada

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

class ContentProviderPassageiro : ContentProvider() {
    var dbOpenHelper : BDViagemOpenHelper? = null
    companion object{
        private const val AUTHORITY = "com.example.projetoprogramacaoavancada"
        private val ENDERCO_BASE = Uri.parse("content://$AUTHORITY")
        val ENDERECO_PASSAGEIRO = Uri.withAppendedPath(ENDERCO_BASE, Tabela_Passageiro.NOME)
    }

    override fun onCreate(): Boolean {
        dbOpenHelper = BDViagemOpenHelper(context)
        return true

    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val db = dbOpenHelper!!.readableDatabase
        requireNotNull(projection)

        val colunas = projection as Array<String>
        val argsSelecao = selectionArgs as Array<String>?
        val id = uri.lastPathSegment

        return null

    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }
}