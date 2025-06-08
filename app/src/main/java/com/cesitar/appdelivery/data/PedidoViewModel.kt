package com.cesitar.appdelivery.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cesitar.appdelivery.data.dao.PedidoDao
import com.cesitar.appdelivery.data.model.Pedido
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class PedidoViewModel(pedidoDao: PedidoDao) : ViewModel() {

    val pedidos: StateFlow<List<Pedido>> = pedidoDao.getAllPedidos()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}
