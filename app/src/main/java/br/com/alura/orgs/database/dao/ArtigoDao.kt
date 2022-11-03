package br.com.alura.orgs.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.alura.orgs.model.Artigo

@Dao
interface ArtigoDao {

    @Query("SELECT * FROM Artigo")
    fun buscaTodos(): List<Artigo>

    @Insert
    fun salva(vararg artigo: Artigo)

    @Delete
    fun remove(artigo: Artigo)

    @Update
    fun edita(artigo: Artigo)

    @Query("SELECT * FROM Artigo WHERE id = :id")
    fun buscaPorId(id: Long) : Artigo?

}