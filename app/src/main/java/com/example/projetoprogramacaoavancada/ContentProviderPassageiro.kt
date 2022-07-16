package com.example.projetoprogramacaoavancada

import android.net.Uri

class ContentProviderPassageiro {
    companion object{
        private const val AUTHORITY = "com.example.projetoprogramacaoavancada"
        private val ENDERCO_BASE = Uri.parse("content://$AUTHORITY")
        val ENDERECO_PASSAGEIRO = Uri.withAppendedPath(ENDERCO_BASE, Tabela_Passageiro.NOME)
    }
}