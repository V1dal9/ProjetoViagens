package com.example.projetoprogramacaoavancada


import android.database.sqlite.SQLiteDatabase
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

        inserirCompanhiaViagem(db, Companhia_Viagem("TAP",0))

        db.close()
    }

    @Test
    fun consegueInserirListaViagem(){
        val db = getWritableDataBase()

        inserirListaViagem(db, Lista_Viagem("Portugal","calças", "colar", "computador", "pensos", "Sapatilhas", 0, 0))

        db.close()

    }

    private fun inserirListaViagem(db : SQLiteDatabase, listaViagem: Lista_Viagem){
        listaViagem.id = TabelaListaViagem(db).insert(listaViagem.toContentValues())
    }

    @Test
    fun consegueInserirInfoViagemBilhete(){
        val db = getWritableDataBase()
        inserirInfoViagem(db, InfoViagemBilhete("Ana", "24/06/2022", "30/06/2022", "Portugal", "Barcelona", "de mão","1 class" ))
        db.close()
    }

    private fun inserirInfoViagem(db : SQLiteDatabase, infoViagemBilhete: InfoViagemBilhete){
        Tabela_Info_Viagem_Bilhete(db).insert(infoViagemBilhete.toContentValues())
    }


    @Test
    fun consegueInserirLocal(){
        val db = getWritableDataBase()
        inserirLocal(db, Local("Madeira", "Lisboa"))
        db.close()
    }

    private fun inserirLocal(db : SQLiteDatabase, local: Local){
        TabelaLocal(db).insert(local.toContentValues())

    }


    @Test
    fun consegueAlterarCompanhiaViagem(){
        val db = getWritableDataBase()

        val companhiaviagem = Companhia_Viagem("RAYNER",0)
        inserirCompanhiaViagem(db, companhiaviagem)

        companhiaviagem.nome = "Ana Raquel"

        val registosAlterados = Tabela_Companhia_Viagem(db).update(
            companhiaviagem.toContenteValues(),
            "${Tabela_Companhia_Viagem.ID_COMPANHIA} =?",
            arrayOf("${companhiaviagem.id}")
        )
        assertEquals(1, registosAlterados)

        db.close()
    }

    @Test
    fun consegueAlterarPassageiro(){
        val db = getWritableDataBase()

        val passageiro = Passageiro("Ana", "Raquel", 22)
        inserirPassageiro(db, passageiro)

        passageiro.nome = "Ana Raquel"

        val registosAlterar = Tabela_Passageiro(db).update(
            passageiro.toContentValues(),
            "${Tabela_Passageiro.CAMPO_NOME}=?",
            arrayOf("${passageiro.id}")
        )
        db.close()
    }

    @Test
    fun consegueEliminarPassageiro(){
        val db = getWritableDataBase()

        val passageiro = Passageiro("Ana Raquel", "Feminino", 23)
        inserirPassageiro(db, passageiro)

        val registosEliminados = Tabela_Passageiro(db).delete(
            "${Tabela_Passageiro.ID_PASSAGEIRO}=?",
            arrayOf("${passageiro.id}")
        )
    }

    @Test
    fun consegueAlterarListaViagem(){
        val db = getWritableDataBase()

        val listaViagem = Lista_Viagem("viagem 1","calças",
            "óculos", "pc",
            "pensos", "sapatilhas", 0, 0)
        inserirListaViagem(db, listaViagem)

        listaViagem.nome_lista = "Madeira"

        val registosAlterados = TabelaListaViagem(db).update(
            listaViagem.toContentValues(),
            "${TabelaListaViagem.ID_LISTA} =?",
            arrayOf("${listaViagem.id}")
        )
        assertEquals(1, registosAlterados)
        db.close()
    }
}