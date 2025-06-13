package com.cesitar.appdelivery

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cesitar.appdelivery.screens.ActualizarEstadoPedidoScreen
import com.cesitar.appdelivery.screens.DetallePedidoScreen
import com.cesitar.appdelivery.ui.theme.AppDeliveryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {

                /* ACTUALIZAR PEDIDO SCREEN
                Surface(modifier = Modifier.fillMaxSize(),color = Color.White) {
                    ActualizarEstadoPedidoScreen(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxSize()
                    )
                }
                */

                // DETALLE PEDIDO SCREEN
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DetallePedidoScreen(
                        onBackClick = {
                            Toast.makeText(this@MainActivity, "Volver", Toast.LENGTH_SHORT).show()
                        },
                        onGuiaDeRemisionClick = {
                            Toast.makeText(this@MainActivity, "Abriendo Guía de Remisión", Toast.LENGTH_SHORT).show()
                        },
                        onVerRutaClick = {
                            Toast.makeText(this@MainActivity, "Mostrando Ruta en Google Maps", Toast.LENGTH_SHORT).show()
                        },
                        pedidoId = "1423722711371-01"
                    )
                }
            }
        }
    }
}
