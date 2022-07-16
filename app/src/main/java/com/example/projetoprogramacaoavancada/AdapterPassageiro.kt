package com.example.projetoprogramacaoavancada

import android.database.Cursor

class AdapterPassageiro(val fragment: PassageiroFragment) {
    var cursor : Cursor? = null
    get() = field
    set(value){
        if(field != value){
            field = value
            //notifyDataSetChanged() falta implementar viewHolder
        }
    }

    var passageiro : Passageiro? = null
        get() = field
        set(value){
            field = value

        }
    //var viewHolderSelecionado : View

}