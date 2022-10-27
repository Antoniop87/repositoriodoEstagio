package br.com.alura.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.R
import br.com.alura.orgs.databinding.ArtigoItemBinding
import br.com.alura.orgs.model.Artigo

class ListaArtigosAdapter(
    private val context: Context,
    artigos: List<Artigo> = emptyList(),
    var quandoClicarNoArtigo: (artigo: Artigo) -> Unit = {}
) : RecyclerView.Adapter<ListaArtigosAdapter.ViewHolder>() {

    private val artigos = artigos.toMutableList()

    inner class ViewHolder(private val binding: ArtigoItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var artigo: Artigo

        init {
            itemView.setOnClickListener {
                if (::artigo.isInitialized){
                    quandoClicarNoArtigo(artigo)
                }
            }
        }

        fun vincula(artigo: Artigo) {
            this.artigo = artigo
            val titulo = binding.tituloArtigo
            titulo.text = artigo.titulo
            val resumo = binding.resumoArtigo
            resumo.text = artigo.resumo
            val artigoCompleto = binding.artigoCompleto
            artigoCompleto.text = artigo.artigo
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ArtigoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val artigo = artigos[position]
        holder.vincula(artigo)
    }

    override fun getItemCount(): Int = artigos.size

    fun atualiza(produtos: List<Artigo>) {
        this.artigos.clear()
        this.artigos.addAll(produtos)
        notifyDataSetChanged()
    }

}
