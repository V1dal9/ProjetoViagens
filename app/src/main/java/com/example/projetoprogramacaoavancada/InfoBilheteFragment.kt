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
import com.example.projetoprogramacaoavancada.databinding.FragmentInfoBilheteBinding

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class InfoBilheteFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {

    var bilheteSelecionado: InfoViagemBilhete? = null
        get() = field
        set(value) {
            field = value
            (requireActivity() as MainActivity).mostraOpcaoAlterarEliminar(field != null)
        }

    private var _binding: FragmentInfoBilheteBinding? = null
    private var adapterBilhete: AdapterBilhete? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoBilheteBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        const val ID_LOADER_BILHETE = 0
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LoaderManager.getInstance(this).initLoader(ID_LOADER_BILHETE, null, this)
        adapterBilhete = AdapterBilhete(this)
        binding.recyclerView.adapter = adapterBilhete
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

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
            ContentProviderBilhete.ENDERECO_BILHETE,
            Tabela_Info_Viagem_Bilhete.TODAS_COLUNAS,
            null,
            null,
            Tabela_Info_Viagem_Bilhete.CAMPO_ID
        )

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        adapterBilhete!!.cursor = data
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        if (_binding == null) {
            adapterBilhete!!.cursor = null
        }
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.action_inserir -> {
                val acao = InfoBilheteFragmentDirections.actionInfoBilheteFragmentToEditBilheteFragment()
                findNavController().navigate(acao)
                (activity as MainActivity).atualizaNome("Inserir Bilhete")
                true
            }
            R.id.action_edit -> {
                val acao =
                    InfoBilheteFragmentDirections.actionInfoBilheteFragmentToEditBilheteFragment()
                findNavController().navigate(acao)
                (activity as MainActivity).atualizaNome("Alterar Bilhete")
                true
            }
            R.id.action_eliminar -> {

                true
            }
            else -> false

        }
}