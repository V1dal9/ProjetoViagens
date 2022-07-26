package com.example.projetoprogramacaoavancada

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleCursorAdapter
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


        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_edita_lista

        LoaderManager.getInstance(this).initLoader(ID_LOADER_LISTA_VIAGEM, null, this)
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
            TabelaListaViagem.TODAS_COLUNAS,
            null,
            null,
            TabelaListaViagem.CAMPO_NOME
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
        //binding.spinnerPassageiro.adapter = null
    }

    /*fun processaOpcaoMenu(item: MenuItem) : Boolean =
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
       }*/

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

        val nomePassageiro = binding.editTextPassageiro.text.toString()
        if(nomePassageiro.isBlank()){
            binding.textViewPassageiro.error = "Preencha o campo Nome do passageiro"
            binding.textViewPassageiro.requestFocus()
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

        val localPartida = binding.editTextLocalPartida.text.toString()
        if (localPartida.isBlank()){
            binding.editTextLocalPartida.error = "Preencha o campo local partida"
            binding.editTextLocalPartida.requestFocus()
            return
        }


        val localChegada = binding.editTextLocalChegada.text.toString()
        if (localChegada.isBlank()){
            binding.editTextLocalChegada.error = "Preencha o campo Local Chegada"
            binding.editTextLocalChegada.requestFocus()
            return
        }
        val DataPartida = binding.editTextDataIda.text.toString()
        if (DataPartida.isBlank()){
            binding.editTextDataIda.error = "Preencha o campo Data de Partida"
            binding.editTextDataIda.requestFocus()
            return
        }
        val DataChegada = binding.editTextDateChegada.text.toString()
        if (DataChegada.isBlank()){
            binding.editTextDateChegada.error = "Preencha o campo Data de Chegada"
            binding.editTextDateChegada.requestFocus()
            return
        }
        val classViagem = binding.editTextClass.text.toString()
        if (classViagem.isBlank()){
            binding.editTextClass.error = "Preencha o campo Class"
            binding.editTextClass.requestFocus()
            return
        }
        val mala = binding.editTextMala.text.toString()
        if (mala.isBlank()){
            binding.editTextMala.error = "Preencha o campo Tipo de Mala"
            binding.editTextMala.requestFocus()
            return
        }



        /*val viagemGuardada =
            if(viagem == null){
                inserirViagem(nomeLista, Roupa, acessorios, eletronicos, higiene, calcado, Passageiro(nomePassageiro, genero, idade.toLong()), DataPartida, DataChegada, localPartida, localChegada, classViagem, mala)
            }else{
                alterarViagem(nomeLista, Roupa, acessorios, eletronicos, higiene, calcado, nomePassageiro, genero, idade.toLong(), DataPartida, DataChegada, localPartida, localChegada, classViagem, mala)
            }*/
    }

    private fun alterarViagem(
        nomeLista: String,
        roupa: String,
        acessorios: String,
        eletronicos: String,
        higiene: String,
        calcado: String,
        nomePassageiro: String,
        generoPassageiro: String,
        idadePassageiro: Long,
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
                    nome = nomePassageiro,
                    genero = generoPassageiro,
                    idade = idadePassageiro),
                InfoViagemBilhete(
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
        nomePassageiro: String,
        generoPassageiro: String,
        idadePassageiro: Long,
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
                nome = nomePassageiro,
                genero = generoPassageiro,
                idade = idadePassageiro),
            InfoViagemBilhete(
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