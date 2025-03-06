package com.ecolimsac.finalproject.componentes

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ecolimsac.finalproject.R
import com.ecolimsac.finalproject.entidad.Usuario
import com.ecolimsac.finalproject.views.Vistas
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

@Composable
fun Logo(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Image(
            painter = painterResource(R.drawable.logo_login),
            contentDescription = null,
            modifier = modifier
                .size(126.dp)
        )
        Text(
            stringResource(R.string.app_name),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = modifier.paddingFromBaseline(top = 32.dp, bottom = 24.dp)
        )
    }
}

@Composable
fun EntradaLogin(modifier: Modifier = Modifier, navigationController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val paddingInterno = PaddingValues(16.dp)
    var clickable by remember { mutableStateOf(true) }
    var estadoAlerta by remember { mutableStateOf(false) }
    var request by remember { mutableStateOf("") }
    var usuario: Usuario

    Column(
        modifier = modifier.fillMaxWidth(1f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        EditText(
            text = R.string.Email, valor = email,
            estadoTexto = { email = it },
            tipoEntry = VisualTransformation.None
        )
        Spacer(modifier.size(12.dp))
        EditText(
            R.string.Password,
            valor = password,
            estadoTexto = { password = it },
            tipoEntry = PasswordVisualTransformation()
        )
        Spacer(modifier.size(24.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth(0.80f)
        ) {
            Button(
                onClick = {
                    if (email.isNotEmpty() && password.isNotEmpty()) {
                        usuario = Usuario(email=email,pass = password)
                        loginEmailAndPass(usuario.email, usuario.pass, navigationController)
                    }else{
                        println("Llene los datos correctamente")
                    }
                }, contentPadding = paddingInterno,
                enabled = clickable
            ) {
                Text(text = "Iniciar Sesion")
            }
            OutlinedButton(
                onClick = {
                    // Mostrar AlertDialog si el estado está activo
                    if (email.isNotEmpty() && password.isNotEmpty()) {
                        CreateEmailAndPass(email, password)
                        estadoAlerta = true
                        request = CreateEmailAndPass(email, password)
                    } else println("Llene los datos correctamente")
                }, contentPadding = paddingInterno,
                enabled = clickable
            ) {
                Text(text = "Crear Cuenta")
            }
        }
        Spacer(modifier.size(12.dp))
        OutlinedButton(onClick = {}) {
            Text(
                text = "Inicie con Google",
                color = MaterialTheme.colorScheme.secondary
            )
        }

        if (estadoAlerta) {
            AlertDialog(
                onDismissRequest = { estadoAlerta = false },
                title = { Text("Creación de usuario") },
                text = { Text(request) },
                confirmButton = {
                    Button(onClick = { estadoAlerta = false }) {
                        Text("Aceptar")
                    }
                }
            )
        }
    }
}

@Composable
fun EditText(
    @StringRes text: Int,
    valor: String,
    modifier: Modifier = Modifier,
    estadoTexto: (String) -> Unit,
    tipoEntry: VisualTransformation
) {
    OutlinedTextField(
        value = valor,
        onValueChange = estadoTexto,
        modifier = modifier
            .fillMaxWidth(0.80f)
            .padding(2.dp),
        label = {
            Text(
                text = stringResource(text).uppercase(),
                fontSize = 10.sp,
                modifier = modifier
                    .border(BorderStroke(0.dp, Color.Transparent))
            )
        },
        singleLine = true,
        visualTransformation = tipoEntry
    )
}


fun loginEmailAndPass(email: String, pass: String, navigationController: NavController){
    val auth: FirebaseAuth = Firebase.auth
    auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
        if (task.isSuccessful) {
            println("Inicio Correcto")
            navigationController.navigate(Vistas.home.id + "/$email")
        } else {
            println("Inicio Incorrecto")
        }
    }
}

fun CreateEmailAndPass(email: String, pass: String): String {
    val auth: FirebaseAuth = Firebase.auth
    var text = false
    var razon = ""

    auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
        if (task.isSuccessful) {
            text = true
        } else {
            text = false
            razon = task.result.toString()
        }
    }
    if (text) return "Se creó correctamente el usuario" else return razon
}
