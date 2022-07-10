package com.example.projetoprogramacaoavancada

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterViagem(val fragment: ListaViagemFragment) : RecyclerView.Adapter<AdapterViagem.ViewHolderViagem>() {
    var cursor : Cursor? = null
    get() = field
    set(value) {
        if(field != value){
            field = value
            notifyDataSetChanged()
        }
    }

    var viewHolderSelecionado : ViewHolderViagem? = null

    inner class ViewHolderViagem(itemViagem : View) : RecyclerView.ViewHolder(itemViagem),
        View.OnClickListener {


        val textViewNomeViagem = itemViagem.findViewById<TextView>(R.id.textViewNomeViagem)
        val textViewPassageiros = itemViagem.findViewById<TextView>(R.id.textViewPassageiros)
        /*val textViewOrigem = itemViagem.findViewById<TextView>(R.id.textViewOrigem)
        val textViewDestino = itemViagem.findViewById<TextView>(R.id.textViewDestino)
        val textViewDataInicio = itemViagem.findViewById<TextView>(R.id.textViewData_inicio)
        val textViewDataFim = itemViagem.findViewById<TextView>(R.id.textViewData_fim)*/
        val textViewListaAcessórios = itemViagem.findViewById<TextView>(R.id.textViewListalevar)
        val textViewListacalcado = itemViagem.findViewById<TextView>(R.id.textViewListalevar2)
        val textViewListaeletronico = itemViagem.findViewById<TextView>(R.id.textViewListalevar3)
        val textViewListahigiene = itemViagem.findViewById<TextView>(R.id.textViewListalevar4)
        val textViewListaroupa = itemViagem.findViewById<TextView>(R.id.textViewListalevar5)
        val textViewBilheteInfoClass = itemViagem.findViewById<TextView>(R.id.textViewBilhete_info_class)

        init {
            itemViagem.setOnClickListener(this)
        }

        var listaviagem : Lista_Viagem? = null
            get() = field
            set(value: Lista_Viagem?){
                field = value

                textViewListaAcessórios.text = listaviagem?.acessorios ?: ""
                textViewListacalcado.text = listaviagem?.calcado ?: ""
                textViewListaeletronico.text = listaviagem?.eletronico ?: ""
                textViewListahigiene.text = listaviagem?.higiene ?: ""
                textViewListaroupa.text = listaviagem?.roupa ?: ""
                textViewNomeViagem.text = listaviagem?.nome_lista ?: ""
                textViewBilheteInfoClass.text = listaviagem?.InfoViagem?.classViagem ?: ""
                textViewBilheteInfoClass.text = listaviagem?.InfoViagem?.localDestino ?: ""
                textViewBilheteInfoClass.text = listaviagem?.InfoViagem?.localOrigem ?: ""
                textViewBilheteInfoClass.text = listaviagem?.InfoViagem?.dataFim ?: ""
                textViewBilheteInfoClass.text = listaviagem?.InfoViagem?.dataInicio ?: ""
                textViewBilheteInfoClass.text = listaviagem?.InfoViagem?.tipoMala ?: ""
                textViewPassageiros.text = listaviagem?.Passageiro?.nome ?: ""
                textViewPassageiros.text = listaviagem?.Passageiro?.genero ?: ""
                textViewPassageiros.text = listaviagem?.Passageiro?.idade.toString() ?: ""
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
        val itemViagem = fragment.layoutInflater.inflate(R.layout.item_viagem, parent, false)
        return ViewHolderViagem(itemViagem)
    }

    override fun getItemCount(): Int {
        if(cursor == null){
            return 0
        }
        return cursor!!.count
    }

}