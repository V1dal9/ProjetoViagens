package com.example.projetoprogramacaoavancada

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import com.example.projetoprogramacaoavancada.databinding.FragmentPassageiroBinding

class PassageiroFragment :Fragment(), LoaderManager.LoaderCallbacks<Cursor> {
    var passageiroSelecionado : Passageiro? = null
    get() = field
    set(value){
        field = value
        (requireActivity() as MainActivity).mostraOpçãoAlterarEliminar(field != null)
    }

    private var _binding: FragmentPassageiroBinding? = null
    private var adapterPassageiro : AdapterPassageiro? = null

    private val binding get() = _binding!!

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> =
        CursorLoader(
            requireContext(),
            ContentProviderPassageiro.ENDERECO_PASSAGEIRO,
            Tabela_Passageiro.TODAS_COLUNAS,
            null,
            null,
            Tabela_Passageiro.CAMPO_NOME
        )


    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        adapterPassageiro!!.cursor = data
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        if(_binding == null) return
        adapterPassageiro!!.cursor = null
    }




}