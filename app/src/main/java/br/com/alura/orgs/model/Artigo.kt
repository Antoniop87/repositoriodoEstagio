package br.com.alura.orgs.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Artigo(
        @PrimaryKey(autoGenerate = true) val id: Long = 0L,
        val titulo: String,
        val resumo: String,
        val artigo: String
): Parcelable
