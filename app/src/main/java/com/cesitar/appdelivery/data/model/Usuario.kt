package com.cesitar.appdelivery.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Usuario")
data class Usuario(
    @PrimaryKey(autoGenerate = true) val id_usuario: Int = 0,
    val nombre_completo: String,
    val usuario: String,
    val contraseña: String
)

