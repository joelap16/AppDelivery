package com.cesitar.appdelivery.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cesitar.appdelivery.data.model.Pedido
import kotlinx.coroutines.flow.Flow

@Dao
interface PedidoDao {

    @Query("SELECT * FROM Pedido ORDER BY fecha_entrega DESC")
    fun getAllPedidos(): Flow<List<Pedido>>
}
