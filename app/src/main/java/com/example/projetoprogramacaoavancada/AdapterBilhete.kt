package com.example.projetoprogramacaoavancada

import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class AdapterBilhete(val fragment : InfoBilheteFragment) : RecyclerView.Adapter<AdapterBilhete.ViewHolderBilhete>() {

    var cursor : Cursor? = null
    get() = field
    set(value) {
        if(field != value){
            field = value
            notifyDataSetChanged()
        }
    }

    var viewHolderSelecionado :  AdapterBilhete.ViewHolderBilhete? = null

    inner class ViewHolderBilhete(itemBilhete : View) : RecyclerView.ViewHolder(itemBilhete), View.OnClickListener{
        var textViewDataInicio = itemBilhete.findViewById<TextView>(R.id.textViewDataInicio)
        var textViewDataFim = itemBilhete.findViewById<TextView>(R.id.textViewDataFim)
        var textViewLocalOrigem = itemBilhete.findViewById<TextView>(R.id.textViewLocalOrigem)
        var textViewLocalDestino = itemBilhete.findViewById<TextView>(R.id.textViewLocalDestino)
        var textViewNomePassageiro = itemBilhete.findViewById<TextView>(R.id.textViewNomePassageiroBilhete)
        var textViewMalaBilhete = itemBilhete.findViewById<TextView>(R.id.textViewMalaBilhete)
        var textViewClassBilhete = itemBilhete.findViewById<TextView>(R.id.textViewClassBilhete)
        var textViewBilheteIdade = itemBilhete.findViewById<TextView>(R.id.textViewBilheteIdade)
        var textViewBilheteGenero = itemBilhete.findViewById<TextView>(R.id.textViewBilheteGenero)

        var bilhete : InfoViagemBilhete? = null
            get() = field
            set(value) {
                field = value

                textViewDataInicio.text = bilhete?.dataInicio ?: ""
                textViewDataFim.text = bilhete?.dataFim ?: ""
                textViewLocalOrigem.text = bilhete?.localOrigem ?: ""
                textViewLocalDestino.text = bilhete?.localDestino ?: ""
                textViewNomePassageiro.text = bilhete?.Passageiro?.nome ?: ""
                textViewMalaBilhete.text = bilhete?.tipoMala ?: ""
                textViewClassBilhete.text = bilhete?.classViagem ?: ""
                textViewBilheteIdade.text = bilhete?.Passageiro?.idade.toString()
                textViewBilheteGenero.text = bilhete?.Passageiro?.genero ?: ""
            }
        init {
            itemBilhete.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            viewHolderSelecionado?.desselecionaBilhete()
            seleciona()
        }

        private fun seleciona(){
            itemView.setBackgroundResource(android.R.color.holo_orange_light)
            viewHolderSelecionado = this
            fragment.bilheteSelecionado = bilhete
        }

        private fun desselecionaBilhete(){
            itemView.setBackgroundResource(android.R.color.white)
        }

    }

    override fun onBindViewHolder(holder: ViewHolderBilhete, position: Int){
        cursor!!.moveToPosition(position)
        holder.bilhete = InfoViagemBilhete.fromCursor(cursor!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBilhete {
        val view = LayoutInflater.from(parent.context)
        return ViewHolderBilhete(view.inflate(R.layout.item_bilhete, parent, false))

    }
    override fun getItemCount(): Int {
        if(cursor == null){
            return 0
        }
        return cursor!!.count
    }
}