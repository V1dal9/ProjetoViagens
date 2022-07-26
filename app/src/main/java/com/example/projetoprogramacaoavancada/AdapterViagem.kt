package com.example.projetoprogramacaoavancada

import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterViagem(val fragment: ListaVerTudoFragment) : RecyclerView.Adapter<AdapterViagem.ViewHolderViagem>() {
    var cursor : Cursor? = null
    get() = field
    set(value) {
        if(field != value){
            field = value
            notifyDataSetChanged()
        }
    }

    var viewHolderSelecionado : ViewHolderViagem? = null

    inner class ViewHolderViagem(itemViagem : View) : RecyclerView.ViewHolder(itemViagem), View.OnClickListener{

        var textViewNomeViagem = itemViagem.findViewById<TextView>(R.id.textViewNomeViagem)
        var textViewPassageiros = itemViagem.findViewById<TextView>(R.id.textViewPassageiros)
        var textViewOrigem = itemViagem.findViewById<TextView>(R.id.textViewOrigem)
        var textViewDestino = itemViagem.findViewById<TextView>(R.id.textViewData_inicio)
        var textViewDataInicio = itemViagem.findViewById<TextView>(R.id.textViewData_inicio)
        var textViewDataFim = itemViagem.findViewById<TextView>(R.id.textViewData_fim)
        var textViewListaAcessorios = itemViagem.findViewById<TextView>(R.id.textViewAcessorios)
        var textViewListacalcado = itemViagem.findViewById<TextView>(R.id.textViewCalcado)
        var textViewListaeletronico = itemViagem.findViewById<TextView>(R.id.textViewEletronicos)
        var textViewListahigiene = itemViagem.findViewById<TextView>(R.id.textViewHigiene)
        var textViewListaroupa = itemViagem.findViewById<TextView>(R.id.textViewRoupas)
        var textViewBilheteInfoClass = itemViagem.findViewById<TextView>(R.id.textViewInfoClass)
        var textViewTipoMala = itemViagem.findViewById<TextView>(R.id.textViewTipoMala)

        var listaviagem : Lista_Viagem? = null
            get() = field
            set(value){
                field = value

                textViewListaAcessorios.text = listaviagem?.acessorios ?: ""
                textViewListacalcado.text = listaviagem?.calcado ?: ""
                textViewListaeletronico.text = listaviagem?.eletronico ?: ""
                textViewListahigiene.text = listaviagem?.higiene ?: ""
                textViewListaroupa.text = listaviagem?.roupa ?: ""
                textViewNomeViagem.text = listaviagem?.nome_lista ?: ""
                textViewBilheteInfoClass.text = listaviagem?.InfoViagem?.classViagem ?: ""
                textViewDestino.text = listaviagem?.InfoViagem?.localDestino ?: ""
                textViewOrigem.text = listaviagem?.InfoViagem?.localOrigem ?: ""
                textViewDataFim.text = listaviagem?.InfoViagem?.dataFim ?: ""
                textViewDataInicio.text = listaviagem?.InfoViagem?.dataInicio ?: ""
                textViewTipoMala.text = listaviagem?.InfoViagem?.tipoMala ?: ""
                textViewPassageiros.text = listaviagem?.passageiro?.nome ?: ""
                textViewPassageiros.text = listaviagem?.passageiro?.genero ?: ""
                textViewPassageiros.text = listaviagem?.passageiro?.idade.toString()
            }

        init {
            itemViagem.setOnClickListener(this)
        }




        override fun onClick(v : View?){
            viewHolderSelecionado?.desseleciona()
            seleciona()
        }

        private fun seleciona(){
            itemView.setBackgroundResource(android.R.color.holo_orange_light)
            viewHolderSelecionado = this
            fragment.viagemSelecionada = listaviagem
        }

        private fun desseleciona(){
            itemView.setBackgroundResource(android.R.color.white)
        }

    }



    override fun onBindViewHolder(holder: ViewHolderViagem, position: Int) {
        cursor!!.moveToPosition(position)
        holder.listaviagem = Lista_Viagem.fromCursor(cursor!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderViagem {
        //val itemViagem = fragment.layoutInflater.inflate(R.layout.item_viagem, parent, false)
        //return ViewHolderViagem(itemViagem)
        val view = LayoutInflater.from(parent.context)
        return ViewHolderViagem(view.inflate(R.layout.item_viagem, parent, false))
    }

    override fun getItemCount(): Int {
        if(cursor == null){
            return 0
        }
        return cursor!!.count
    }

}