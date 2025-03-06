@file:OptIn(ExperimentalMaterial3Api::class)

package com.ecolimsac.finalproject.componentes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ecolimsac.finalproject.dto.Basura
import com.ecolimsac.finalproject.entidad.TipoBasura
import com.ecolimsac.finalproject.entidad.Usuario
import com.ecolimsac.finalproject.ui.theme.ECOLIMSACTheme
import com.ecolimsac.finalproject.views.Vistas
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun home(modifier: Modifier = Modifier, user: String?, navController: NavController) {
    ECOLIMSACTheme {
        Scaffold(
            modifier = modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text("Hola, $user") },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigate(Vistas.login.id)
                        }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Cerrar sesión"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    )
                )
            },
            content = { padding ->
                var kilogramosBasura by remember { mutableStateOf(0) }
                var seleccionado by remember { mutableStateOf<TipoBasura?>(null) }

                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(padding),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    TextField(
                        value = kilogramosBasura.toString(),
                        label = { Text("Cantidad Basura") },
                        onValueChange = { newValue ->
                            kilogramosBasura = newValue.toIntOrNull() ?: 0
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        trailingIcon = { Text("KG") }
                    )
                    Spacer(Modifier.size(24.dp))
                    OpcionesBasura(seleccionado, onSeleccionChange = { seleccionado = it })
                    Spacer(Modifier.size(24.dp))
                    Button(onClick = {
                        guardarBasura(kilogramosBasura.toFloat(), seleccionado ?: TipoBasura.GENERALES, user)
                        println("Cantidad ingresada: $kilogramosBasura")
                        seleccionado = null
                        kilogramosBasura = 0
                    }) {
                        Text("Ingresar")
                    }
                }
            }
        )
    }
}

@Composable
fun OpcionesBasura(seleccionado: TipoBasura?, onSeleccionChange: (TipoBasura) -> Unit) {
    val OPCIONES = TipoBasura.values().toList()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        OPCIONES.forEach { opcion ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                RadioButton(
                    selected = seleccionado == opcion,
                    onClick = { onSeleccionChange(opcion) }
                )
                Text(text = opcion.name)
            }
            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}

fun guardarBasura(cantidad: Float, tipoBasura: TipoBasura, usuario: String?) {
    val store = FirebaseFirestore.getInstance()
    val registro = Basura(tipoBasura, cantidad, usuario ?: "User")


    if (cantidad > 0 && tipoBasura.name.isNotBlank()) {

        store.collection("registro").add(registro)
            .addOnSuccessListener { println("Se resgistró ${registro.cantidad} de ${registro.tipoBasura}") }
            .addOnFailureListener { println("No se pudo guardar el registro") }
    }

}