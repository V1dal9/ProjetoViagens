package com.example.projetoprogramacaoavancada

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import kotlin.contracts.Returns

class ContentProviderViagem : ContentProvider() {
    var dbOpenHelper : BDViagemOpenHelper? = null

    override fun onCreate(): Boolean {
        dbOpenHelper = BDViagemOpenHelper(context)

        return true
    }

    override fun query(
        uri : Uri,
        projection : Array<out String>?,
        selection : String?,
        selectionArgs : Array<out String>?,
        sortOrder : String?
    ): Cursor? {
        val db = dbOpenHelper!!.readableDatabase

        requireNotNull(projection)

        val colunas = projection as Array<String>
        val argsSelecao = selectionArgs as Array<String>?
        val id = uri.lastPathSegment

        val cursor = when (getUriMatcher().match(uri)){
            URI_PASSAGEIRO -> Tabela_Passageiro(db).query(colunas, selection, selectionArgs, null, null, sortOrder)
            URI_LISTA_VIAGEM -> TabelaListaViagem(db).query(colunas, selection, selectionArgs, null, null, sortOrder)
            else -> null
        }
        return cursor
    }

    override fun getType(p0: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO("Not yet implemented")
    }


    companion object{
        private const val AUTHORITY = "com.example.projetoprogramacaoavancada"

        private const val URI_PASSAGEIRO = 100
        private const val URI_PASSAGEIRO_ESPECIFICO = 101
        private const val URI_LISTA_VIAGEM = 200
        private const val URI_LISTA_ESPECIFICA = 201

        private val ENDERCO_BASE = Uri.parse("content://$AUTHORITY")
        val ENDERECO_PASSAGEIRO = Uri.withAppendedPath(ENDERCO_BASE, Tabela_Passageiro.NOME)
        val ENDERECO_LISTA = Uri.withAppendedPath(ENDERCO_BASE, TabelaListaViagem.NOME)
        fun getUriMatcher() : UriMatcher {
            var uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

            uriMatcher.addURI(AUTHORITY, Tabela_Passageiro.NOME, URI_PASSAGEIRO)
            uriMatcher.addURI(AUTHORITY, "${Tabela_Passageiro.NOME}/#", URI_PASSAGEIRO_ESPECIFICO)
            uriMatcher.addURI(AUTHORITY, TabelaListaViagem.NOME, URI_LISTA_VIAGEM)
            uriMatcher.addURI(AUTHORITY, "${TabelaListaViagem.NOME}/#", URI_LISTA_ESPECIFICA)
            return uriMatcher
        }
    }
}