package com.example.projetoprogramacaoavancada

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.projetoprogramacaoavancada.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    var idMenuAtual = R.menu.menu_main
        get() = field
        set(value) {
            if (value != field) {
                field = value
                invalidateOptionsMenu()
            }
        }

    var menu: Menu? = null
    var fragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        this.menu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        val opcaoProcessada: Boolean

        if (fragment is MenuInicialFragment) {
            opcaoProcessada = (fragment as MenuInicialFragment).processaOpcaoMenu(item)
        } else if (fragment is ListaViagemFragment) {
            opcaoProcessada = (fragment as ListaViagemFragment).processaOpcaoMenu(item)
        }else if (fragment is EditarInfoViagemFragment) {
            opcaoProcessada = (fragment as EditarInfoViagemFragment).processaOpcaoMenu(item)
        }/*else if(fragment is EliminarViagemFragment){
            opcaoProcessada = (fragment as EliminarViagemFragment).processaOpcaoMenu(item)
        }*/else {
            opcaoProcessada = false
        }

        if (opcaoProcessada) return true

        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
    fun mostraOpcaoAlterarEliminar(mostra: Boolean){
        menu!!.findItem(R.id.action_edit).setVisible(mostra)
        menu!!.findItem(R.id.action_eliminar).setVisible(mostra)
    }
    fun atualizaNome(id_string_nome: String){
        binding.toolbar.setTitle(id_string_nome)
    }
}