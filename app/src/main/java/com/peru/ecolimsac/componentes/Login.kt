package com.peru.ecolimsac.componentes

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.peru.ecolimsac.R

@Composable
fun logo(modifier: Modifier = Modifier) {
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
fun entradaLogin(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth(1f)) {
        editText(R.string.Email)
        editText(R.string.Password)
    }
}

@Composable
fun editText(@StringRes text: Int, modifier: Modifier = Modifier) {
    var ingreso by remember { mutableStateOf("") }
    Row(
        modifier = modifier.fillMaxWidth(1f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        TextField(
            value = ingreso, onValueChange = {nuevaEntrada -> ingreso = nuevaEntrada },
            modifier = modifier
                .background(Color.White)
                .padding(bottom = 10.dp)
                .fillMaxWidth(0.75f),
            label = {
                Text(
                    text = stringResource(text).uppercase(),
                    fontSize = 10.sp
                )
            },
            singleLine = true

        )
    }
}

@Preview
@Composable
private fun previewEntradaLogin() {
    entradaLogin()
}