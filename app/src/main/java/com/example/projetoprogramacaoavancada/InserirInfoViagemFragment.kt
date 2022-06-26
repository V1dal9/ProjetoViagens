package com.example.projetoprogramacaoavancada

import android.database.Cursor
import android.os.Bundle
import android.widget.SimpleAdapter
import android.widget.SimpleCursorAdapter
import androidx.fragment.app.Fragment
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import com.example.projetoprogramacaoavancada.databinding.FragmentInseirViagemBinding


class InserirInfoViagemFragment :Fragment(), LoaderManager.LoaderCallbacks<Cursor> {

    private var _binding: FragmentInseirViagemBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> =
        CursorLoader(
            requireContext(),
            ContentProviderViagem.ENDERECO_PASSAGEIRO,
            Tabela_Passageiro.TODAS_COLUNAS,
            null,
            null,
            Tabela_Passageiro.CAMPO_NOME
        )


    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        val adapterGenero = SimpleCursorAdapter(
          requireContext(),
          android.R.layout.simple_list_item_1,
          data,
          arrayOf(Tabela_Passageiro.GENERO),
          intArrayOf(android.R.id.text1),
          0
        )
        binding.spinnerGenero.adapter = adapterGenero

    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        TODO("Not yet implemented")
    }


}