package com.example.projetoprogramacaoavancada

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterPassageiro(val fragment: PassageiroFragment2) : RecyclerView.Adapter<AdapterPassageiro.ViewHolderPassageiro>(){
    var cursor : Cursor? = null
    get() = field
    set(value){
        if(field != value){
            field = value
            notifyDataSetChanged()
        }
    }
    var viewHolderSelecionado : ViewHolderPassageiro? = null

    inner class ViewHolderPassageiro(itemPassageiro: View) : RecyclerView.ViewHolder(itemPassageiro), View.OnClickListener{

        var textViewNomePassageiro = itemPassageiro.findViewById<TextView>(R.id.textViewNomePassageiro)
        var textViewGeneroPassageiro = itemPassageiro.findViewById<TextView>(R.id.textViewGeneroPassageiro)
        var textViewIdadePassageiro = itemPassageiro.findViewById<TextView>(R.id.textViewIdadePassageiro)

        init {
            itemPassageiro.setOnClickListener(this)
        }

        var passageiro : Passageiro? = null
            get() = field
            set(value) {
                field = value

                textViewNomePassageiro.text = passageiro?.nome ?: ""
                textViewGeneroPassageiro.text = passageiro?.genero ?: ""
                textViewIdadePassageiro.text = passageiro?.idade.toString()
        }



        override fun onClick(v: View?) {
            viewHolderSelecionado?.desseleciona()
            seleciona()
        }

        private fun desseleciona() {
            itemView.setBackgroundResource(android.R.color.white)
        }

        private fun seleciona() {
            itemView.setBackgroundResource(android.R.color.holo_orange_light)
            viewHolderSelecionado = this
            fragment.passageiroSelecionado = passageiro
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPassageiro {
        val itemPassageiro = fragment.layoutInflater.inflate(R.layout.item_passageiro, parent, false)
        return ViewHolderPassageiro(itemPassageiro)
    }

    override fun onBindViewHolder(holder: ViewHolderPassageiro, position: Int) {
        cursor!!.moveToPosition(position)
        holder.passageiro = Passageiro.fromCursor(cursor!!)
    }

    override fun getItemCount(): Int {
        if(cursor == null){
            return 0
        }
        return cursor!!.count
    }
}