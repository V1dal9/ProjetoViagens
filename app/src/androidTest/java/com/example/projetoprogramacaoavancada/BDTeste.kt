package com.example.projetoprogramacaoavancada


import android.database.sqlite.SQLiteDatabase
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BDTeste {
    private fun appContext() =
        InstrumentationRegistry.getInstrumentation().targetContext


    @Before
    fun apagaBaseDados() {
        //appContext().deleteDatabase(BDViagemOpenHelper.NOME)
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
        val passageiro = Passageiro("Rita", "Feminino", 19)
        inserirPassageiro(db, passageiro)
        val companhiaviagem = Companhia_Viagem("TAP", passageiro.id)
        inserirCompanhiaViagem(db, companhiaviagem)
        db.close()
    }

    @Test
    fun consegueInserirListaViagem(){
        val db = getWritableDataBase()
        val passageiro = Passageiro("Luís", "Masculino", 49)
        inserirPassageiro(db, passageiro)
        val infoviagemBilhete = InfoViagemBilhete("24/06/2022", "30/06/2022", "Portugal", "Barcelona", "de mão","1º class", passageiro)
        inserirInfoViagem(db, infoviagemBilhete)
        val listaViagem = Lista_Viagem("Portugal","calças", "colar", "computador", "pensos", "Sapatilhas", passageiro, infoviagemBilhete)
        inserirListaViagem(db, listaViagem)

        db.close()
    }

    private fun inserirListaViagem(db : SQLiteDatabase, listaViagem: Lista_Viagem){
        listaViagem.id = TabelaListaViagem(db).insert(listaViagem.toContentValues())
    }

    @Test
    fun consegueInserirInfoViagemBilhete(){
        val db = getWritableDataBase()
        val passageiro = Passageiro("Luís", "Masculino", 49)
        inserirPassageiro(db, passageiro)
        val infoviagemBilhete = InfoViagemBilhete("24/06/2022", "30/06/2022", "Portugal", "Barcelona", "de mão","1º class", passageiro)
        inserirInfoViagem(db, infoviagemBilhete)
        db.close()
    }

    @Test
    fun consegueAlterarInfoViagemBilhete(){
        val db = getWritableDataBase()
        val passageiroLuis = Passageiro("Luís", "Masculino", 49)
        inserirPassageiro(db, passageiroLuis)

        val infoviagemBilhete = InfoViagemBilhete("24/06/2022", "30/06/2022", "Portugal", "Barcelona", "de mão","1º class", passageiroLuis)
        inserirInfoViagem(db, infoviagemBilhete)

        infoviagemBilhete.dataInicio = "20/07/2024"
        infoviagemBilhete.dataFim = "30/07/2024"
        infoviagemBilhete.localOrigem = "Barcelona"
        infoviagemBilhete.localDestino = "Madeira"
        infoviagemBilhete.tipoMala = "porão"
        infoviagemBilhete.classViagem = "2ª Class"
        infoviagemBilhete.passageiro = passageiroLuis

        val registosAlterados = Tabela_Info_Viagem_Bilhete(db).update(
            infoviagemBilhete.toContentValues(),
            "${Tabela_Info_Viagem_Bilhete.CAMPO_ID}=?",
            arrayOf("${infoviagemBilhete.id}")
        )


        db.close()
    }
    @Test
    fun consegueEliminarInfoViagemBilhete(){
        val db = getWritableDataBase()
        val passageiroLuis = Passageiro("Luís", "Masculino", 49)
        inserirPassageiro(db, passageiroLuis)
        val infoviagemBilhete = InfoViagemBilhete("24/06/2022", "30/06/2022", "Portugal", "Barcelona", "de mão","1º class", passageiroLuis)
        inserirInfoViagem(db, infoviagemBilhete)

        val registosEliminados = Tabela_Info_Viagem_Bilhete(db).delete(
            "${Tabela_Info_Viagem_Bilhete.CAMPO_ID}=?",
            arrayOf("${infoviagemBilhete.id}")
        )
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

        val passageiroAna = Passageiro("Ana", "Feminino", 19)
        inserirPassageiro(db, passageiroAna)
        val companhiaviagem = Companhia_Viagem("RAYNER", passageiroAna.id)
        inserirCompanhiaViagem(db, companhiaviagem)

        companhiaviagem.nome = "Ana Raquel"
        companhiaviagem.idPassageiro = passageiroAna.id //associar o id

        val registosAlterados = Tabela_Companhia_Viagem(db).update(
            companhiaviagem.toContenteValues(),
            "${Tabela_Companhia_Viagem.ID_COMPANHIA} =?",
            arrayOf("${companhiaviagem.id}")
        )
        assertEquals(1, registosAlterados)

        db.close()
    }

    @Test
    fun consegueEliminarCompanhiaViagem(){
        val db = getWritableDataBase()
        val passageiro = Passageiro("João", "Masculino", 30)
        inserirPassageiro(db, passageiro)
        val companhiaviagem = Companhia_Viagem("TAP", passageiro.id)
        inserirCompanhiaViagem(db, companhiaviagem)
        val registosEliminados = Tabela_Companhia_Viagem(db).delete(
            "${Tabela_Companhia_Viagem.ID_COMPANHIA}=?",
            arrayOf("${companhiaviagem.id}")
        )
        assertEquals(1, registosEliminados)
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
            "${Tabela_Passageiro.CAMPO_ID}=?",
            arrayOf("${passageiro.id}")
        )
        assertEquals(1, registosEliminados)
        db.close()
    }

    @Test
    fun consegueAlterarListaViagem(){
        val db = getWritableDataBase()
        val passageiro = Passageiro("Luís", "Masculino", 49)
        inserirPassageiro(db, passageiro)
        val infoviagemBilhete = InfoViagemBilhete("24/06/2022", "30/06/2022", "Portugal", "Barcelona", "de mão","1º class", passageiro)
        inserirInfoViagem(db, infoviagemBilhete)
        val listaViagem = Lista_Viagem("viagem 1","calças",
            "óculos", "pc",
            "pensos", "sapatilhas", passageiro, infoviagemBilhete)
        inserirListaViagem(db, listaViagem)

        listaViagem.nome_lista = "Madeira"
        listaViagem.acessorios = "colar"
        listaViagem.calcado = "salto alto"
        listaViagem.eletronico = "computador"
        listaViagem.higiene = "pasta de dentes"
        listaViagem.higiene = "calisola\n calções\n cuecas"
        listaViagem.passageiro = passageiro
        listaViagem.InfoViagem = infoviagemBilhete

        val registosAlterados = TabelaListaViagem(db).update(
            listaViagem.toContentValues(),
            "${TabelaListaViagem.ID_LISTA} =?",
            arrayOf("${listaViagem.id}")
        )
        assertEquals(1, registosAlterados)
        db.close()
    }

    @Test
    fun consegueEliminarListaViagem(){
        val db = getWritableDataBase()
        val passageiro = Passageiro("Luís", "Masculino", 49)
        inserirPassageiro(db, passageiro)
        val infoviagemBilhete = InfoViagemBilhete("24/06/2022", "30/06/2022", "Portugal", "Barcelona", "de mão","1º class", passageiro)
        inserirInfoViagem(db, infoviagemBilhete)
        val listaViagem = Lista_Viagem("viagem 1","calças",
            "óculos", "pc",
            "pensos", "sapatilhas", passageiro, infoviagemBilhete)
        inserirListaViagem(db, listaViagem)

        val registoEliminado = TabelaListaViagem(db).delete(
            "${TabelaListaViagem.ID_LISTA}=?",
            arrayOf("${listaViagem.id}")
        )
        db.close()
    }
}