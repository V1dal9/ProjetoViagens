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
import com.example.projetoprogramacaoavancada.databinding.FragmentEditarViagemBinding


class EditarInfoViagemFragment :Fragment(), LoaderManager.LoaderCallbacks<Cursor> {

    private var _binding: FragmentEditarViagemBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView
    private val binding get() = _binding!!
    private var viagem : Lista_Viagem? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
            ContentProviderViagem.ENDERECO_LISTA,
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
        binding.spinnerPassageiro.adapter = adapterGenero

    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        binding.spinnerPassageiro.adapter = null
    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
       when(item.itemId){
           R.id.action_guardar -> {
               guardar()
               true
           }
           R.id.action_cancelar -> {
               voltarListaViagem()
               true
           }
           else -> false
       }

    private fun guardar(){
        val nomeLista = binding.editTextNomeLista.text.toString()
        if(nomeLista.isBlank()){
            binding.editTextNomeLista.error = "Preencha o Nome da lista"
            binding.editTextNomeLista.requestFocus()
            return
        }

        val Roupa = binding.editTextRoupa.text.toString()
        if(Roupa.isBlank()){
            binding.editTextRoupa.error = "Preencha o campo roupa"
            binding.editTextRoupa.requestFocus()
            return
        }

        val acessorios = binding.editTextAcessorios.text.toString()
        if(acessorios.isBlank()){
            binding.editTextAcessorios.error = "Preencha o campo acessorios"
            binding.editTextAcessorios.requestFocus()
            return
        }

        val eletronicos = binding.editTextEletronicos.text.toString()
        if(eletronicos.isBlank()){
            binding.editTextEletronicos.error = "Preencha o campo eletronicos"
            binding.editTextEletronicos.requestFocus()
            return
        }

        val higiene = binding.editTextHigiene.text.toString()
        if(higiene.isBlank()){
            binding.editTextHigiene.error = "Preencha o campo higiene"
            binding.editTextHigiene.requestFocus()
            return
        }

        val calcado = binding.editTextCalcado.text.toString()
        if(calcado.isBlank()){
            binding.editTextCalcado.error = "Preencha o campo calçado"
            binding.editTextCalcado.requestFocus()
            return
        }

        val idPassageiro = binding.spinnerPassageiro.selectedItemId
        if(idPassageiro == Spinner.INVALID_ROW_ID){
            binding.textViewPassageiro.error = "Escolha um passageiro"
            binding.spinnerPassageiro.requestFocus()
            return
        }

        val genero = binding.editTextGenero.text.toString()
        if(genero.isBlank()){
            binding.editTextCalcado.error = "Preencha o campo genero"
            binding.editTextGenero.requestFocus()
            return
        }

        val idade = binding.editTextIdade.text.toString()
        if(idade.isBlank()){
            binding.editTextIdade.error = "Preencha o campo idade"
            binding.editTextIdade.requestFocus()
            return
        }



        val viagemGuardada =
            if(viagem == null){
                //inserirViagem(nomeLista, Roupa, acessorios, eletronicos, higiene, calcado, idPassageiro, genero, idade, )
            }else{
                //alterarViagem(nomeLista, Roupa, acessorios, eletronicos, higiene, calcado, idPassageiro, genero, idade, )
            }
    }

    private fun alterarViagem(
        nomeLista: String,
        roupa: String,
        acessorios: String,
        eletronicos: String,
        higiene: String,
        calcado: String,
        idPassageiro: Long,
        nomePassageiro: String,
        generoPassageiro: String,
        idadePassageiro: Long,
        idInfoViagemBilhete: Long,
        dataInicio: String,
        dataFim: String,
        localOrigem: String,
        localDestino: String,
        tipoMala: String,
        classViagem: String,
        passageiro: Passageiro
        ): Boolean{
            val viagem = Lista_Viagem(
                nomeLista,
                roupa,
                acessorios,
                eletronicos,
                higiene,
                calcado,
                Passageiro(
                    id = idPassageiro,
                    nome = nomePassageiro,
                    genero = generoPassageiro,
                    idade = idadePassageiro),
                InfoViagemBilhete(
                    id = idInfoViagemBilhete,
                    dataFim = dataFim,
                    dataInicio = dataInicio,
                    localDestino = localDestino,
                    localOrigem = localOrigem,
                    tipoMala = tipoMala,
                    classViagem = classViagem,
                    Passageiro = passageiro
                    )
            )

        val enderecoViagem = Uri.withAppendedPath(ContentProviderViagem.ENDERECO_LISTA, "${this.viagem!!.id}")

        val registosAlterados = requireActivity().contentResolver.update(enderecoViagem, viagem.toContentValues(), null, null)

        return registosAlterados == 1
    }

    private fun inserirViagem(
        nomeLista: String,
        roupa: String,
        acessorios: String,
        eletronicos: String,
        higiene: String,
        calcado: String,
        idPassageiro: Long,
        nomePassageiro: String,
        generoPassageiro: String,
        idadePassageiro: Long,
        idInfoViagemBilhete: Long,
        dataInicio: String,
        dataFim: String,
        localOrigem: String,
        localDestino: String,
        tipoMala: String,
        classViagem: String,
        passageiro: Passageiro
    ): Boolean{
        val viagem = Lista_Viagem(nomeLista,
            roupa,
            acessorios,
            eletronicos,
            higiene,
            calcado,
            Passageiro(
                id = idPassageiro,
                nome = nomePassageiro,
                genero = generoPassageiro,
                idade = idadePassageiro),
            InfoViagemBilhete(
                id = idInfoViagemBilhete,
                dataFim = dataFim,
                dataInicio = dataInicio,
                localDestino = localDestino,
                localOrigem = localOrigem,
                tipoMala = tipoMala,
                classViagem = classViagem,
                Passageiro = passageiro)
        )

        val enderecoViagemInserir = requireActivity().contentResolver.insert(ContentProviderViagem.ENDERECO_LISTA, viagem.toContentValues())

        return enderecoViagemInserir != null
    }


    private fun voltarListaViagem(){
        findNavController().navigate(R.id.action_EditarViagemFragment_to_ListaViagem)
    }


}