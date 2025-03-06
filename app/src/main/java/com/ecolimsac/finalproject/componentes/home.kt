@file:OptIn(ExperimentalMaterial3Api::class)

package com.ecolimsac.finalproject.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ecolimsac.finalproject.ui.theme.ECOLIMSACTheme
import com.ecolimsac.finalproject.views.Vistas

@Composable
fun home(modifier: Modifier = Modifier, user: String?, navController: NavController) {
    ECOLIMSACTheme {
        Scaffold(
            modifier = modifier.fillMaxSize(),
            topBar = {
                TopAppBar(title = { Text("Hola, $user") }, navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(
                            Vistas.login.id
                        )
                    }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Cerrar sesion"
                        )
                    }
                },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.secondary)
                )
            }, content = { padding ->
                var kilogramosBasura by remember { mutableStateOf(0) }

                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(padding),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    TextField(
                        value = "",
                        label = { Text("Cantidad Basura") },
                        onValueChange = {kilogramosBasura = kilogramosBasura},
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        trailingIcon = { Text("KG") }
                    )
                    Spacer(Modifier.size(24.dp))
                    Button(onClick = {
                        println("Cantidad ingresada: $kilogramosBasura")
                    }) {
                        Text("Ingresar")
                    }
                }
            })
    }
}