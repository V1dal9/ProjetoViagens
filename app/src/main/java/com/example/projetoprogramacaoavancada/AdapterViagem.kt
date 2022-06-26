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

    class ViewHolderViagem(itemViagem : View) : RecyclerView.ViewHolder(itemViagem){
        val textViewNomeViagem = itemViagem.findViewById<TextView>(R.id.textViewNomeViagem)
        val textViewPassageiros = itemViagem.findViewById<TextView>(R.id.textViewPassageiros)
        val textViewOrigem = itemViagem.findViewById<TextView>(R.id.textViewOrigem)
        val textViewDestino = itemViagem.findViewById<TextView>(R.id.textViewDestino)
        val textViewDataInicio = itemViagem.findViewById<TextView>(R.id.textViewData_inicio)
        val textViewDataFim = itemViagem.findViewById<TextView>(R.id.textViewData_fim)
        val textViewListaLevar = itemViagem.findViewById<TextView>(R.id.textViewListalevar)
        val textViewBilheteInfoClass = itemViagem.findViewById<TextView>(R.id.textViewBilhete_info_class)

        var listaviagem : Lista_Viagem? = null
            get() = field
            set(value: Lista_Viagem?){
                field = value

                textViewListaLevar.text = listaviagem?.acessorios ?: ""
                textViewListaLevar.text = listaviagem?.calcado ?: ""
                textViewListaLevar.text = listaviagem?.eletronico ?: ""
                textViewListaLevar.text = listaviagem?.higiene ?: ""
                textViewListaLevar.text = listaviagem?.roupa ?: ""
                textViewNomeViagem.text = listaviagem?.nome_lista ?: ""
            }
        var bilheteInfo : InfoViagemBilhete? = null
            get() = field
            set(value: InfoViagemBilhete?){
                field = value

                textViewBilheteInfoClass.text = bilheteInfo?.classViagem ?: ""
                textViewDataInicio.text = bilheteInfo?.dataInicio.toString() //passar para texto a data
                textViewDataFim.text = bilheteInfo?.dataFim.toString()
                textViewOrigem.text = bilheteInfo?.localOrigem ?: ""
                textViewDestino.text = bilheteInfo?.localDestino ?: ""
            }
        var passageiro : Passageiro? = null
            get() = field
            set(value: Passageiro?){
                textViewPassageiros.text = passageiro?.nome ?: ""
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