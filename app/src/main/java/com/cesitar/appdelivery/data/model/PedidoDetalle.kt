package com.cesitar.appdelivery.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Pedido_Detalle",
    foreignKeys = [
        ForeignKey(
            entity = Usuario::class,
            parentColumns = ["id_usuario"],
            childColumns = ["id_usuario_registro"]
        )
    ]
)
data class PedidoDetalle(
    @PrimaryKey(autoGenerate = true) val id_pedido_detalle: Int = 0,
    val id_pedido: Int,
    val id_usuario_registro: String,
    val firma: ByteArray?, // Firma como imagen binaria
    val comentario: String?,
    val estado: String = "pendiente",
    val fecha_registro: String // Puede usarse como String o Date según el uso
)

