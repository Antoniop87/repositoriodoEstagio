package br.com.alura.orgs.ui.activity

import android.content.Intent
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

    private var idArtigo = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Postar Artigo"
        setContentView(binding.root)
        configuraBotaoSalvar()

        intent.getParcelableExtra<Artigo>(CHAVE_ARTIGO)?.let { artigoCarregado ->
            title = "Editar Artigo"
            idArtigo = artigoCarregado.id
            binding.edittextArtigoTitulo.setText(artigoCarregado.titulo)
            binding.edittextArtigoResumo.setText(artigoCarregado.resumo)
            binding.edittextArtigoCompleto.setText(artigoCarregado.artigo)
        }
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = binding.botaoSalvar
        val db = AppDatabase.instancia(this)

        val artigoDao = db.artigoDao()

        botaoSalvar.setOnClickListener {
            val artigoNovo = criaArtigo()
            if(idArtigo > 0){
                artigoDao.edita(artigoNovo)
            } else {
                artigoDao.salva(artigoNovo)
            }
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
                id = idArtigo,
                titulo = titulo,
                resumo = resumo,
                artigo = artigoCompleto
        )
    }

}