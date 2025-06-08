package com.cesitar.appdelivery.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cesitar.appdelivery.data.dao.PedidoDao
import com.cesitar.appdelivery.data.model.*

@Database(
    entities = [Usuario::class, Articulo::class, Pedido::class, PedidoDetalle::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pedidoDao(): PedidoDao
}
