package com.cesitar.appdelivery.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Articulo")
data class Articulo(
    @PrimaryKey(autoGenerate = true) val id_articulo: Int = 0,
    val nombre: String,
    val stock: Int
)
