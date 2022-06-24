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
import com.example.projetoprogramacaoavancada.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {

    private var _binding: FragmentSecondBinding? = null
    private var adapterViagem : AdapterViagem? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterViagem = AdapterViagem(this)
        binding.recyclerViewLista.adapter = adapterViagem
        binding.recyclerViewLista.layoutManager = LinearLayoutManager(requireContext())
        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_main
        /*binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        return CursorLoader(
            requireContext(),
            ContentProviderViagem.ENDERECO_PASSAGEIRO,
            Tabela_Passageiro.TODAS_COLUNAS,
            null,
            null,
            "${Tabela_Passageiro}"
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
            R.id.action_edit -> true
            R.id.action_eliminar -> true
            else -> false

        }
}