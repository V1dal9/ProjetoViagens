package com.example.projetoprogramacaoavancada


import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BDTeste {
    private fun appContext() =
        InstrumentationRegistry.getInstrumentation().targetContext


    @Before
    fun apagaBaseDados() {
        appContext().deleteDatabase(BDViagemOpenHelper.NOME)
    }

    @Test
    fun consegueAbrirBaseDados() {
        val openHelper = BDViagemOpenHelper(appContext())
        val db = openHelper.readableDatabase

        assertTrue(db.isOpen)

        db.close()
    }

    @Test
    fun consegueInserirPassageiro(){
        val db = getWritableDataBase()

        inserirPassageiro(db, Passageiro("Ana", "F", 22))

        db.close()
    }

    private fun inserirPassageiro(db : SQLiteDatabase, passageiro: Passageiro){
        passageiro.id = Tabela_Passageiro(db).insert(passageiro.toContentValues())
        //assertNotEquals(-1, passageiro.id)
    }

    private fun inserirCompanhiaViagem(db : SQLiteDatabase, companhiaviagem: Companhia_Viagem ){
        companhiaviagem.id = Tabela_Companhia_Viagem(db).insert(companhiaviagem.toContenteValues())
    }

    private fun getWritableDataBase(): SQLiteDatabase {
        val openHelper = BDViagemOpenHelper(appContext())
        return openHelper.writableDatabase
    }

    @Test
    fun consegueInserirCompanhiaViagem(){
        val db = getWritableDataBase()

        inserirCompanhiaViagem(db, Companhia_Viagem(0,"TAP", 0))

        db.close()
    }

    @Test
    fun consegueInserirListaViagem(){
        val db = getWritableDataBase()

        inserirListaViagem(db, Lista_Viagem(0,"Portugal", "S", "S", "S", "S", "S"))

        db.close()

    }

    private fun inserirListaViagem(db : SQLiteDatabase, listaViagem: Lista_Viagem){
        listaViagem.id = TabelaListaViagem(db).insert(listaViagem.toContentValues())
    }

    @Test
    fun consegueInserirInfoViagemBilhete(){
        val db = getWritableDataBase()
        inserirInfoViagem(db, InfoViagemBilhete(0, "Ana", 24, 4, "Portugal", "Barcelona", "de mão","1 class" ))
        db.close()
    }

    private fun inserirInfoViagem(db : SQLiteDatabase, infoViagemBilhete: InfoViagemBilhete){
        Tabela_Info_Viagem_Bilhete(db).insert(infoViagemBilhete.toContentValues())
    }


    @Test
    fun consegueInserirOrigem(){
        val db = getWritableDataBase()
        inserirOrigem(db, Local(0, "Portugal", "Madeira"))
        db.close()
    }

    private fun inserirOrigem(db : SQLiteDatabase, local: Local){
        TabelaLocal(db).insert(local.toContentValues())
    }

    @Test
    fun consegueInserirDestino(){
        val db = getWritableDataBase()
        inserirDestino(db, LocalViagem(0, 0))
        db.close()
    }

    private fun inserirDestino(db : SQLiteDatabase, localViagem: LocalViagem){
        TabelaLocalViagem(db).insert(localViagem.toContentValues())
    }

    @Test
    fun consegueAlterarCompanhiaViagem(){
        val db = getWritableDataBase()

        val companhiaviagem = Companhia_Viagem(0,"RAYNER", 0)
        inserirCompanhiaViagem(db, companhiaviagem)

        companhiaviagem.nome = "Ana Raquel"

        val registosAlterados = Tabela_Companhia_Viagem(db).update(
            companhiaviagem.toContenteValues(),
            "${BaseColumns._ID} = ?",
            arrayOf("${companhiaviagem.id}")
        )


        db.close()
    }

    @Test
    fun consegueAlterarPassageiro(){
        val db = getWritableDataBase()

        val companhiaviagem = Companhia_Viagem(0,"Ana", 0)
        inserirCompanhiaViagem(db, companhiaviagem)

        val passageiroRaquel = Passageiro("Ana", "Raquel", 22)
        inserirPassageiro(db, passageiroRaquel)

        val passageiroAna = Passageiro("Ana", "F", 20)
        inserirPassageiro(db, passageiroAna)

        db.close()
    }

    @Test
    fun consegueAletrarListaViagem(){
        val db = getWritableDataBase()

        val listaViagem = Lista_Viagem(0, "viagem 1", "calças",
            "óculos", "pc",
            "pensos", "sapatilhas")
        inserirListaViagem(db, listaViagem)

        listaViagem.nome_lista = "Madeira"

        val registosAlterados = TabelaListaViagem(db).update(
            listaViagem.toContentValues(),
            "${TabelaListaViagem.ID_LISTA} =?",
            arrayOf("${listaViagem.id}")
        )

        db.close()
    }

}