package br.com.alura.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import br.com.alura.orgs.R
import br.com.alura.orgs.databinding.ActivityDetalhesDoArtigoBinding
import br.com.alura.orgs.model.Artigo

class DetalhesDoArtigo : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetalhesDoArtigoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Artigo"
        setContentView(binding.root)
        tentaCarregarArtigo()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalhes, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_detalhes_apagar -> {

            }

            R.id.menu_detalhes_editar -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun tentaCarregarArtigo() {
        intent.getParcelableExtra<Artigo>(CHAVE_ARTIGO)?.let { artigoCarregado ->
            preencheCampos(artigoCarregado)
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