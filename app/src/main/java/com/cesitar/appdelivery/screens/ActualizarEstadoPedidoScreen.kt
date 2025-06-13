package com.cesitar.appdelivery.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActualizarEstadoPedidoScreen(modifier: Modifier){

    var expanded by remember { mutableStateOf(false) }
    var selectedEstado by remember { mutableStateOf("-Seleccionar-") }
    val estados = listOf("Entregado", "Entregado Parcial", "No Entregado")
    var comentarios by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ){
        Column (modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
        ) {
            Text(text = "Sistema Delivery", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = "Actualización de estados del pedido", fontSize = 12.sp, color = Color.Gray)

            Spacer(modifier = Modifier.height(8.dp))

            // nro de pedido
            Text(text = "1523722711371-01", fontSize = 16.sp)

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Estado actual:", fontSize = 16.sp)

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Nuevo estado", fontSize = 16.sp)

            Spacer(modifier = Modifier.height(8.dp))

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = selectedEstado,
                    onValueChange = {},
                    readOnly = true,
                    label = { "-Seleccionar Estado-" },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ){
                    estados.forEach { estadoItem ->
                        DropdownMenuItem(
                            text = { Text(text = estadoItem) },
                            onClick = {
                                selectedEstado = estadoItem
                                expanded = false
                            }
                        )
                    }
                }


            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(text = "Comentarios", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))

            // textfield comentarios
            TextField(
                value = comentarios,
                onValueChange = { comentarios = it },
                label = { "Comentarios" },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )

            Spacer(modifier = Modifier.height(24.dp))

            // archivos + botones
            Text(text = "Archivos", fontSize = 16.sp)

            Spacer(modifier = Modifier.height(8.dp))

            Row (
                modifier = Modifier.fillMaxWidth()
            ){
                OutlinedButton(
                    onClick = { /*TODO: Adjuntar*/ },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                ) {
                    Text("Adjuntar" , color = Color.Black)
                }

                Spacer(modifier = Modifier.width(8.dp))

                OutlinedButton(
                    onClick = { /*TODO: Ver*/ },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                ) {
                    Text("Ver" , color = Color.Black)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // botones cerrar y grabar

            Row (
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedButton(
                    onClick = { /*TODO: Cerrar*/ },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                ) {
                    Text("Cerrar", color = Color.Black)
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = { /*TODO: Grabar*/ },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                ) {
                    Text("Grabar")
                }
            }
        }
    }
}