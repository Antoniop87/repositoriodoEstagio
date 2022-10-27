package br.com.alura.orgs.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.alura.orgs.model.Artigo

@Dao
interface ArtigoDao {

    @Query("SELECT * FROM Artigo")
    fun buscaTodos(): List<Artigo>

    @Insert
    fun salva(vararg artigo: Artigo)

}