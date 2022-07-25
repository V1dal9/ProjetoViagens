package com.example.projetoprogramacaoavancada

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleCursorAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import com.example.projetoprogramacaoavancada.databinding.FragmentEditarPassageirosBinding

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
        activity.idMenuAtual = R.menu.menu_edita_passageiro

        if(arguments != null){
            //passageiro = EditarPassageirosFragmentArgs.fromBundle(arguments!!).passageiro

            if(passageiro != null){
                binding.textViewGenero
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
        binding.spinnerPassageiroNome.adapter = adapterPassageiro
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        if (_binding == null) return
        binding.spinnerPassageiroNome.adapter = null
    }
    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_guardar -> {
                guardar()
                true
            }
            R.id.action_cancelar -> {
                voltaListaLivros()
                true
            }
            else -> false
        }

    private fun voltaListaLivros() {
        findNavController().navigate(R.id.action_EditarViagemFragment_to_ListaViagem)
    }

    private fun guardar() {
        val idPassageiro = binding.spinnerPassageiroNome.selectedItemId
        if (idPassageiro == Spinner.INVALID_ROW_ID) {
            binding.textView6.error = "Nome Obrigatório"
            binding.spinnerPassageiroNome.requestFocus()
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

        val livroGuardado =
            if (passageiro == null) {
                //inserePassageiro()
            } else {
                //alteraPassageiro()
            }

        /*if (livroGuardado) {
            Toast.makeText(requireContext(), "Passageiro guardado com sucesso", Toast.LENGTH_LONG)
                .show()
            voltaListaLivros()
        } else {
            Snackbar.make(binding.spinnerPassageiroNome, "Erro ao guardar o Passageiro", Snackbar.LENGTH_INDEFINITE).show()
            return
        }*/
    }

    private fun alteraPassageiro(name: String, id: Long, genero: String, idade: Long) : Boolean {
        val passageiro = Passageiro(name, genero, idade, id)

        val enderecoPassageiro = Uri.withAppendedPath(ContentProviderPassageiro.ENDERECO_PASSAGEIRO, "${this.passageiro!!.id}")

        val registosAlterados = requireActivity().contentResolver.update(enderecoPassageiro, passageiro.toContentValues(), null, null)

        return registosAlterados == 1
    }

    private fun inserePassageiro(nome: String, genero: String, idade: Long): Boolean {
        val passageiro = Passageiro(genero, nome, idade)

        val enderecoPassageiroInserido = requireActivity().contentResolver.insert(ContentProviderPassageiro.ENDERECO_PASSAGEIRO, passageiro.toContentValues())

        return enderecoPassageiroInserido != null
    }


}