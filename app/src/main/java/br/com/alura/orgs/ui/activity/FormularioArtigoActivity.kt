package br.com.alura.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.orgs.database.AppDatabase
import br.com.alura.orgs.databinding.ArtigosFormularioActivityBinding
import br.com.alura.orgs.model.Artigo

class FormularioArtigoActivity :
    AppCompatActivity() {

    private val binding by lazy {
        ArtigosFormularioActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoSalvar()
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = binding.botaoSalvar
        val db = AppDatabase.instancia(this)

        val artigoDao = db.artigoDao()

        botaoSalvar.setOnClickListener {
            val artigoNovo = criaArtigo()
            artigoDao.salva(artigoNovo)
            finish()
        }
    }

    private fun criaArtigo(): Artigo {
        val inputTitulo = binding.edittextArtigoTitulo
        val titulo = inputTitulo.text.toString()
        val inputResumo = binding.edittextArtigoResumo
        val resumo = inputResumo.text.toString()
        val inputArtigo = binding.edittextArtigoCompleto
        val artigoCompleto = inputArtigo.text.toString()


        return Artigo(
                titulo = titulo,
                resumo = resumo,
                artigo = artigoCompleto
        )
    }

}