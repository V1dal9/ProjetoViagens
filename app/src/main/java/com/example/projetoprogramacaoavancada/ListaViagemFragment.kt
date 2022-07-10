package com.example.projetoprogramacaoavancada

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projetoprogramacaoavancada.databinding.FragmentListaViagemBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ListaViagemFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {

    private var _binding: FragmentListaViagemBinding? = null
    private var adapterViagem : AdapterViagem? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListaViagemBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LoaderManager.getInstance(this).initLoader(ID_LOADER_LISTA, null, this)

        adapterViagem = AdapterViagem(this)
        binding.recyclerViewLista.adapter = adapterViagem
        binding.recyclerViewLista.layoutManager = LinearLayoutManager(requireContext())

        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_main
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        return CursorLoader(
            requireContext(),
            ContentProviderViagem.ENDERECO_LISTA,
            Tabela_Passageiro.TODAS_COLUNAS,
            null,
            null,
            "${TabelaListaViagem.CAMPO_NOME}"
        )
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        adapterViagem!!.cursor = data
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        adapterViagem!!.cursor = null
    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId){
            R.id.action_inserir -> {
                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
                true
            }
            R.id.action_settings -> true
            R.id.action_edit -> true
            else -> false

        }
    companion object{
        const val ID_LOADER_LISTA = 0
    }
}