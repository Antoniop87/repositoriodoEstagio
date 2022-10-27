package br.com.alura.orgs.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.alura.orgs.database.dao.ArtigoDao
import br.com.alura.orgs.model.Artigo

@Database(entities = [Artigo::class], version = 1, exportSchema = true)
abstract class AppDatabase: RoomDatabase() {

    abstract fun artigoDao(): ArtigoDao

    companion object {
        fun instancia(context: Context): AppDatabase{
           return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "estagio.db"
            ).allowMainThreadQueries()
                .build()
        }
    }

}