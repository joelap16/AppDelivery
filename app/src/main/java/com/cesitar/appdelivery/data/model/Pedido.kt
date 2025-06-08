package com.cesitar.appdelivery.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Pedido",
    foreignKeys = [
        ForeignKey(
            entity = Usuario::class,
            parentColumns = ["id_usuario"],
            childColumns = ["id_usuario"]
        ),
        ForeignKey(
            entity = Articulo::class,
            parentColumns = ["id_articulo"],
            childColumns = ["id_articulo"]
        )
    ]
)
data class Pedido(
    @PrimaryKey(autoGenerate = true) val id_pedido: Int = 0,
    val id_articulo: Int,
    val estado: String = "pendiente",
    val cantidad: Int,
    val numero_pedido: String,
    val fecha_entrega: String,
    val direccion_tienda: String,
    val documento: String?,
    val id_usuario: Int,
    val numero_guia: String?
)
