package br.com.alura.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import br.com.alura.orgs.database.AppDatabase
import br.com.alura.orgs.databinding.ArtigosActivityBinding
import br.com.alura.orgs.ui.recyclerview.adapter.ListaArtigosAdapter

class ListaArtigosActivity : AppCompatActivity() {

    private val adapter = ListaArtigosAdapter(context = this)
    private val binding by lazy {
        ArtigosActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Artigos"
        setContentView(binding.root)
        configuraRecyclerView()
        configuraFab()

     val db = AppDatabase.instancia(this)
        val artigoDao = db.artigoDao()
        adapter.atualiza(artigoDao.buscaTodos())
    }

    override fun onResume() {
        super.onResume()
        val db = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "estagio.db"
        ).allowMainThreadQueries()
            .build()
        val artigoDao = db.artigoDao()
        adapter.atualiza(artigoDao.buscaTodos())
    }

    private fun configuraFab() {
        val fab = binding.buttonadd
        fab.setOnClickListener {
            vaiParaFormularioArtigo()
        }
    }

     private fun vaiParaFormularioArtigo() {
        val intent = Intent(this, FormularioArtigoActivity::class.java)
        startActivity(intent)
    }

    private fun configuraRecyclerView() {
        val recyclerView = binding.artigosRecyclerView
        recyclerView.adapter = adapter
        adapter.quandoClicarNoArtigo = {
            val intent = Intent(
                this,
                DetalhesDoArtigo::class.java
            ).apply {
                putExtra(CHAVE_ARTIGO, it)
            }
            startActivity(intent)
        }
    }

}