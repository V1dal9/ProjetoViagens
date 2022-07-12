package com.example.projetoprogramacaoavancada

import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleCursorAdapter
import androidx.fragment.app.Fragment
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import com.example.projetoprogramacaoavancada.databinding.FragmentEditarViagemBinding


class EditarInfoViagemFragment :Fragment(), LoaderManager.LoaderCallbacks<Cursor> {

    private var _binding: FragmentEditarViagemBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditarViagemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LoaderManager.getInstance(this).initLoader(ID_LOADER_LISTA_VIAGEM, null, this)
        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_edita_lista
    }

    companion object{
        const val ID_LOADER_LISTA_VIAGEM = 0
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> =
        CursorLoader(
            requireContext(),
            ContentProviderViagem.ENDERECO_PASSAGEIRO,
            Tabela_Passageiro.TODAS_COLUNAS,
            null,
            null,
            Tabela_Passageiro.CAMPO_NOME_PASSAGEIRO
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
        binding.spinnerGenero.adapter = null
    }

    /*fun processaOpçãoMenu(item: MenuItem) : Boolean =
       when(item.itemId){
           R.id.ac
       } */

    private fun voltarListaViagem(){
        //
    }


}