package com.example.projetoprogramacaoavancada

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import com.example.projetoprogramacaoavancada.databinding.FragmentEditarPassageirosBinding
import com.google.android.material.snackbar.Snackbar

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class EditarPassageirosFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {
    private var _binding: FragmentEditarPassageirosBinding? = null

    private val binding get() = _binding!!
    private var passageiro: Passageiro? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentEditarPassageirosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val activity = requireActivity() as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_edita_lista

        if(arguments != null){
            //passageiro = EditarPassageirosFragmentArgs.fromBundle(arguments!!).passageiro

            if(passageiro != null){
                binding.editTextNomePassageiro.setText(passageiro!!.nome)
                binding.textViewGenero.setText(passageiro!!.genero)
                binding.textViewIdade.setText(passageiro!!.idade.toString())
            }
        }

        LoaderManager.getInstance(this).initLoader(ID_LOADER_PASSAGEIRO, null, this)
    }

    companion object {
        const val ID_LOADER_PASSAGEIRO = 0
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

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
        val adapterPassageiro = SimpleCursorAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            data,
            arrayOf(Tabela_Passageiro.CAMPO_NOME),
            intArrayOf(android.R.id.text1),
            0
        )

    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        if (_binding == null) return

    }
    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_Save -> {
                guardar()
                true
            }
            R.id.action_back -> {
                voltaListaPassageiro()
                true
            }
            else -> false
        }

    private fun voltaListaPassageiro() {
        findNavController().navigate(R.id.action_editarPassageirosFragment_to_passageiroFragment2)
    }

    private fun guardar() {
        val NomePassageiro = binding.editTextNomePassageiro.text.toString()
        if (NomePassageiro.isBlank()) {
            binding.editTextNomePassageiro.error = "Nome Obrigatório"
            binding.editTextNomePassageiro.requestFocus()
            return
        }

        val genero = binding.textViewGenero.text.toString()
        if (genero.isBlank()) {
            binding.textViewGenero.error = "Género Obrigatório"
            binding.textViewGenero.requestFocus()
            return
        }

        val idade = binding.textViewIdade.text.toString()
        if (idade.isBlank()) {
            binding.textViewIdade.error = "Idade Obrigatória"
            binding.textViewIdade.requestFocus()
            return
        }

        val PassageiroGuardado =
            if (passageiro == null) {
                inserePassageiro(NomePassageiro, genero, idade.toLong())
            } else {
                alteraPassageiro(NomePassageiro, genero, idade.toLong())
            }

        if (PassageiroGuardado) {
            Toast.makeText(requireContext(), "Passageiro guardado com sucesso", Toast.LENGTH_LONG).show()
            voltaListaPassageiro()
        } else {
            Snackbar.make(binding.editTextNomePassageiro, "Erro ao guardar o Passageiro", Snackbar.LENGTH_INDEFINITE).show()
            return
        }
    }

    private fun alteraPassageiro(name: String, genero: String, idade: Long) : Boolean {
        val passageiro = Passageiro(name, genero, idade)

        val enderecoPassageiro = Uri.withAppendedPath(ContentProviderPassageiro.ENDERECO_PASSAGEIRO, "${this.passageiro!!.id}")

        val registosAlterados = requireActivity().contentResolver.update(enderecoPassageiro, passageiro.toContentValues(), null, null)

        return registosAlterados == 1
    }

    private fun inserePassageiro(name: String, genero: String, idade: Long): Boolean {
        val passageiro = Passageiro(name, genero, idade)

        val enderecoPassageiroInserido = requireActivity().contentResolver.insert(ContentProviderPassageiro.ENDERECO_PASSAGEIRO, passageiro.toContentValues())

        return enderecoPassageiroInserido != null
    }


}