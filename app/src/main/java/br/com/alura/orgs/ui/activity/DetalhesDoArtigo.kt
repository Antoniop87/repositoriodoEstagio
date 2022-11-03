package br.com.alura.orgs.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import br.com.alura.orgs.R
import br.com.alura.orgs.database.AppDatabase
import br.com.alura.orgs.databinding.ActivityDetalhesDoArtigoBinding
import br.com.alura.orgs.model.Artigo

class DetalhesDoArtigo : AppCompatActivity() {

    private var artigoId: Long? = null
    private var artigo: Artigo? = null
    private val binding by lazy {
        ActivityDetalhesDoArtigoBinding.inflate(layoutInflater)
    }
    private val artigoDao by lazy {
        AppDatabase.instancia(this).artigoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Artigo"
        setContentView(binding.root)
        tentaCarregarArtigo()
    }

    override fun onResume() {
        super.onResume()
        artigoId?.let { id ->
            artigo = artigoDao.buscaPorId(id)
        }
        artigo?.let {
            preencheCampos(it)
        }?: finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalhes, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when(item.itemId){
                R.id.menu_detalhes_apagar -> {
                    artigo?.let { artigoDao.remove(it) }

                    finish()
                }

                R.id.menu_detalhes_editar -> {
                    Intent(this,FormularioArtigoActivity::class.java).apply {
                        putExtra(CHAVE_ARTIGO, artigo)
                        startActivity(this)
                    }
                }
            }

        return super.onOptionsItemSelected(item)
    }

    private fun tentaCarregarArtigo() {
        intent.getParcelableExtra<Artigo>(CHAVE_ARTIGO)?.let { artigoCarregado ->
            artigoId = artigoCarregado.id
        }?: finish()
    }

    private fun preencheCampos(artigoCarregado: Artigo){
        with(binding){
            titulodeltalhes.text = artigoCarregado.titulo
            resumodetalhes.text = artigoCarregado.resumo
            artifofull.text = artigoCarregado.artigo
        }
    }
}