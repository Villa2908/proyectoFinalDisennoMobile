package com.ecolimsac.finalproject.componentes

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecolimsac.finalproject.R
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

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
fun EntradaLogin(modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val paddingInterno = PaddingValues(16.dp)

    Column(
        modifier = modifier.fillMaxWidth(1f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        EditText(text = R.string.Email, valor = email,
            estadoTexto = { email = it },
            tipoEntry = VisualTransformation.None)
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
            Button(onClick = {
                loginEmailAndPass(email, password)
            }, contentPadding = paddingInterno) {
                Text(text = "Iniciar Sesion")
            }
            OutlinedButton(onClick = {}, contentPadding = paddingInterno) {
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
            .background(Color.White)
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


@Preview
@Composable
private fun previewEntradaLogin() {
    EntradaLogin()
}

fun loginEmailAndPass(email: String, pass: String) {
    val auth: FirebaseAuth = Firebase.auth
    val _loading = MutableLiveData(false)

    auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
        if (task.isSuccessful) {
            println("Inicio Correcto")
        } else {
            println("Inicio Incorrecto")
        }
    }
    println("usuario: $email, $pass")
}


