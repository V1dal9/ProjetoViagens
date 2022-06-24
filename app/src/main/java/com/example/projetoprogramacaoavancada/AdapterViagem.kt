package com.example.projetoprogramacaoavancada

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterViagem(val fragment: SecondFragment) : RecyclerView.Adapter<AdapterViagem.ViewHolderViagem>() {
    var cursor : Cursor? = null
    get() = field
    set(value) {
        if(field != value){
            field = value
            notifyDataSetChanged()
        }
    }

    class ViewHolderViagem(itemViagem : View) : RecyclerView.ViewHolder(itemViagem){
        val textViewNome = itemViagem.findViewById<TextView>(R.id.textViewNome)
    }

    override fun onBindViewHolder(holder: ViewHolderViagem, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderViagem {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}