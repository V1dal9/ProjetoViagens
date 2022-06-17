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
        val companhiaviagem = Companhia_Viagem(0, "TAP")
        inserirCompanhiaViagem(db, companhiaviagem)

        val passageiro = Passageiro(0, "Ana", "F", 22)
        inserirPassageiro(db, passageiro)

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

        inserirCompanhiaViagem(db, Companhia_Viagem(0,"TAP"))

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
        inserirInfoViagem(db, InfoViagemBilhete(0, "Ana", 24, 4, "Portugal", "Barcelona"))
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
        inserirDestino(db, LocalViagem(0,"Barcelona"))
        db.close()
    }

    private fun inserirDestino(db : SQLiteDatabase, localViagem: LocalViagem){
        TabelaLocalViagem(db).insert(localViagem.toContentValues())
    }

    @Test
    fun consegueAlterarCompanhiaViagem(){
        val db = getWritableDataBase()

        val companhiaviagem = Companhia_Viagem(0,"Ana")
        inserirCompanhiaViagem(db, companhiaviagem)

        companhiaviagem.nome = "Ana Raquel"

        val registosAlterados = Tabela_Companhia_Viagem(db).update(
            companhiaviagem.toContenteValues(),
            "${BaseColumns._ID} = ?",
            arrayOf("${companhiaviagem.id}")
        )
        assertEquals(1, registosAlterados)

        db.close()
    }

    @Test
    fun consegueAlterarPassageiro(){
        val db = getWritableDataBase()

        val passageiroRaquel = Passageiro(0, "Raquel", "F", 22)
        inserirPassageiro(db, passageiroRaquel)

        val passageiroAna = Passageiro(0, "Ana", "F", 20)
        inserirPassageiro(db, passageiroAna)

    }
}