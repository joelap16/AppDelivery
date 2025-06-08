package com.cesitar.appdelivery.ui.activitis

import com.cesitar.appdelivery.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cesitar.appdelivery.data.PedidoViewModel
import com.cesitar.appdelivery.data.dao.PedidoDao
import com.cesitar.appdelivery.data.database.DatabaseProvider
import com.cesitar.appdelivery.data.model.OrderModel
import com.cesitar.appdelivery.data.model.Pedido
import com.cesitar.appdelivery.ui.theme.AppDeliveryTheme
import com.cesitar.appdelivery.ui.theme.Gray10
import com.cesitar.appdelivery.ui.theme.Gray20
import com.cesitar.appdelivery.ui.theme.GreenPrimary
import com.cesitar.appdelivery.ui.theme.Orange

class OrdersListScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val pedidoDao = DatabaseProvider.getDatabase(this).pedidoDao()
        setContent {
            AppDeliveryTheme {
                OrdersListScreen(pedidoDao)
            }

        }
    }
}

@Composable
fun OrdersListScreen(pedidoDao: PedidoDao) {
    var showDrawer by remember { mutableStateOf(false) }
    val viewModel = PedidoViewModel(pedidoDao)
    val pedidos by viewModel.pedidos.collectAsState()

    var listaDePedidos = pedidos

    //eliminar luego cuando esten los datos preparados
    if(pedidos.isEmpty()) {
        listaDePedidos = listXampleOrder()
    }


    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Gray20),
            content = { paddingValues ->
                Box(
                    modifier = Modifier
                        .padding(paddingValues)
                        .background(Gray20)
                ) {
                    Column {
                        Spacer(Modifier.height(20.dp))
                        HeaderListScreen { showDrawer = true }
                        Spacer(Modifier.height(5.dp))
                        ActionsOrders()
                        OrdersListComponent(listaDePedidos)
                    }
                }
            })

        // Overlay para cerrar el drawer tocando fuera
        if (showDrawer) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f))
                    .clickable { showDrawer = false } // cerrar al hacer clic fuera
            )
        }

        // Drawer lateral derecho
        AnimatedVisibility(visible = showDrawer) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(250.dp)
                    .align(Alignment.CenterEnd)
                    .background(Color.White)
                    .clickable(enabled = false) {} // evita que el drawer se cierre al tocar dentro
            ) {
                ContentDrawer()
            }
        }
    }
}

@Composable
fun OrdersListComponent(orders: List<Pedido>) {
    LazyColumn(
        modifier = Modifier
            .padding(all = 16.dp)
            .background(Color.White)
    ) {
        items(orders) { item ->
            OrderComponent(item)
        }
    }
}


@Composable
fun OrderComponent(dataModel: Pedido) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("2442442255.5", style = styleUidOrder())
            Button(
                onClick = { println("hola") },
                colors = ButtonDefaults.buttonColors(backgroundColor = Orange)
            ) {
                Text(
                    "Guía de Remisión",
                    Modifier.clickable { remissionGuide(dataModel.numero_guia!!) })
            }
        }

        RowDataOrder("Empresa:", "Sin Especificar")
        RowDataOrder("Cliente:", "Sin especificar")
        RowDataOrder("Documento:", dataModel.documento!!)
        RowDataOrder("Dirección:", dataModel.direccion_tienda)
        RowDataOrder("Horario de entrega:", dataModel.fecha_entrega)
        RowDataOrder("Articulos: ", "articulo")
        Divider(color = Color.Gray)
    }
}

@Composable
fun HeaderListScreen(onMenuClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Gray10
            )
    ) {
        IconButton(onClick = onMenuClick) {
            Box(
                modifier = Modifier
                    .background(color = GreenPrimary)
                    .padding(all = 5.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.icon_drawer_menu),
                    contentDescription = "Menu",
                    modifier = Modifier
                        .height(24.dp)
                        .width(24.dp)
                )
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            "Listado de Pedidos", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
        )
    }
}

@Composable
fun RowDataOrder(name: String, data: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        NameInfoOrder(name)
        Spacer(modifier = Modifier.width(10.dp))
        Text(data, maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun NameInfoOrder(data: String) {
    Text(data, style = styleNameInfoOrder())
}

fun styleNameInfoOrder(): TextStyle {
    return TextStyle(fontWeight = FontWeight.ExtraBold, color = Color.Gray)
}

fun styleUidOrder(): TextStyle {
    return TextStyle(color = GreenPrimary, fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
}

fun orderXample(): OrderModel {
    return OrderModel(
        address = "Av los laureles",
        hourDelivered = "23/21/2025",
        enterprise = "Keferis Corporation",
        client = "Carlina Ceballos",
        documentNumber = "12345678",
        articles = "Iphone 15 pro max, celular con camara galactica",
        uid = null
    )
}

@Composable
fun ContentDrawer() {
    Column {
        Box(
            modifier = Modifier
                .background(color = Color.DarkGray)
                .fillMaxWidth()
        ) {
            Column(Modifier.padding(top = 30.dp)) {
                Text(
                    "KEVIN FERNANDEZ RISCO",
                    style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold)
                )
                Text("Tienda", color = Color.Red)
                Text("v 1.0.0.1")
            }
        }
        Spacer(Modifier.height(10.dp))
        Text("Listado de pedidos")
        Divider()
        Spacer(Modifier.height(10.dp))
        Text("Cerrar Sesión")
    }
}

@Composable
fun ActionsOrders() {

    val orderCant = 10

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Pedidos asignados")
        Button(
            onClick = refreshOrders(),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent, contentColor = Color.Black
            ),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(R.drawable.refresh_icon),
                    contentDescription = "Refresh",
                    modifier = Modifier
                        .width(19.dp)
                        .height(19.dp)
                )
                Spacer(Modifier.width(5.dp))
                Text("Refrescar")
            }
        }
        Text("($orderCant) pedidos")
    }
}

fun refreshOrders(): () -> Unit = {

}

fun logout() {

}

fun listOrders() {

}

fun remissionGuide(uid: String): () -> Unit = {

}

//eliminar luego de que se ingresen datos desde un formulario

fun listXampleOrder(): List<Pedido> {
    val pedidoXample = Pedido(
        id_pedido = 1,
        numero_pedido = "14141443.2",
        numero_guia = "6742444",
        documento = "98765432",
        fecha_entrega = "02/05/2022",
        id_usuario = 1,
        id_articulo = 1,
        direccion_tienda = "Av España 433",
        estado = "Pendiente",
        cantidad = 2
    )

    return listOf(pedidoXample, pedidoXample, pedidoXample, pedidoXample)
}
