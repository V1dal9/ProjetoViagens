package com.example.projetoprogramacaoavancada

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.provider.BaseColumns

class ContentProviderPassageiro : ContentProvider() {
    var dbOpenHelper : BDViagemOpenHelper? = null

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

        val cursor = when (ContentProviderViagem.getUriMatcher().match(uri)){
            ContentProviderViagem.URI_PASSAGEIRO -> Tabela_Passageiro(db).query(colunas, selection, argsSelecao, null, null, sortOrder)
            ContentProviderViagem.URI_PASSAGEIRO_ESPECIFICO -> Tabela_Passageiro(db).query(colunas, "${BaseColumns._ID}=?", arrayOf("${id}"), null,null, null)
            else -> null
        }
        return cursor

    }

    override fun getType(uri: Uri): String? =
        when (getUriMatcher().match(uri)) {
            ContentProviderViagem.URI_PASSAGEIRO -> "${ContentProviderViagem.MULTIPLOS_REGISTOS}/${Tabela_Passageiro.NOME}"
            ContentProviderViagem.URI_PASSAGEIRO_ESPECIFICO -> "${ContentProviderViagem.UNICO_REGISTO}/${Tabela_Passageiro.NOME}"
            else -> null
        }
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val db = dbOpenHelper!!.writableDatabase

        requireNotNull(values)

        val id = when (ContentProviderViagem.getUriMatcher().match(uri)) {
            URI_PASSAGEIRO -> Tabela_Passageiro(db).insert(values)
            else -> -1
        }

        db.close()

        if (id == -1L) return null

        return Uri.withAppendedPath(uri, "$id")
    }
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val db = dbOpenHelper!!.writableDatabase

        val id = uri.lastPathSegment

        val registosApagados = when (ContentProviderViagem.getUriMatcher().match(uri)) {
            ContentProviderViagem.URI_LISTA_ESPECIFICA -> TabelaListaViagem(db).delete("${BaseColumns._ID}=?", arrayOf("${id}"))
            ContentProviderViagem.URI_PASSAGEIRO_ESPECIFICO -> Tabela_Passageiro(db).delete("${BaseColumns._ID}=?", arrayOf("${id}"))
            ContentProviderViagem.URI_COMPANHIA_ESPECIFICA -> Tabela_Companhia_Viagem(db).delete("${BaseColumns._ID} = ?", arrayOf("${id}"))
            ContentProviderViagem.URI_BILHETE_ESPECIFICA -> Tabela_Info_Viagem_Bilhete(db).delete("${BaseColumns._ID} = ?", arrayOf("${id}"))
            ContentProviderViagem.URI_LOCAL_ESPECIFICA -> Tabela_Info_Viagem_Bilhete(db).delete("${BaseColumns._ID} = ?", arrayOf("${id}"))
            else -> 0
        }

        db.close()

        return registosApagados
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        requireNotNull(values)

        val db = dbOpenHelper!!.writableDatabase

        val id = uri.lastPathSegment

        val registosAlterados = when (ContentProviderViagem.getUriMatcher().match(uri)) {
            URI_PASSAGEIRO_ESPECIFICO -> Tabela_Passageiro(db).update(values,"${BaseColumns._ID}=?", arrayOf("${id}"))
            else -> 0
        }

        db.close()

        return registosAlterados
    }




    companion object{
        private const val AUTHORITY = "com.example.projetoprogramacaoavancada"

        private const val URI_PASSAGEIRO = 100
        private const val URI_PASSAGEIRO_ESPECIFICO = 101

        private val ENDERCO_BASE = Uri.parse("content://${AUTHORITY}")

        val ENDERECO_PASSAGEIRO = Uri.withAppendedPath(ENDERCO_BASE, Tabela_Passageiro.NOME)
        fun getUriMatcher() : UriMatcher{
            val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

            uriMatcher.addURI(AUTHORITY, Tabela_Passageiro.NOME, URI_PASSAGEIRO)
            uriMatcher.addURI(AUTHORITY, "${Tabela_Passageiro.NOME}/#", URI_PASSAGEIRO_ESPECIFICO)
            return uriMatcher
        }
    }

}