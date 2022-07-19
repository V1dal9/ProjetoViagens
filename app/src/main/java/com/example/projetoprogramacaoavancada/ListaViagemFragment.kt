package com.example.projetoprogramacaoavancada

import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
    var viagemSelecionada : Lista_Viagem? = null
    get() = field
    set(value){
        field = value
        (requireActivity() as MainActivity).mostraOpçãoAlterarEliminar(field != null)
    }

    private var _binding: FragmentListaViagemBinding? = null
    private var adapterViagem : AdapterViagem? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

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
        activity.idMenuAtual = R.menu.menu_lista
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> =
         CursorLoader(
            requireContext(),
             ContentProviderViagem.ENDERECO_LISTA,
             TabelaListaViagem.TODAS_COLUNAS,
            null,
            null,
            TabelaListaViagem.CAMPO_NOME
        )




    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        adapterViagem!!.cursor = data
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        if (_binding == null) return
        adapterViagem!!.cursor = null
    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId){
            R.id.action_inserir -> {
                val acao = ListaViagemFragmentDirections.actionListaViagemToEditarViagemFragment()
                findNavController().navigate(acao)
                (activity as MainActivity).atualizaNome("Inserir Nome")
                true
            }
            R.id.action_edit -> {
                val acao = ListaViagemFragmentDirections.actionListaViagemToEditarViagemFragment()
                findNavController().navigate(acao)
                (activity as MainActivity).atualizaNome("Alterar Viagem")
                true
            }
            R.id.action_eliminar -> {

                true
            }
            else -> false

        }
    companion object{
        const val ID_LOADER_LISTA = 0
    }
}


