package com.peru.ecolimsac

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.peru.ecolimsac.componentes.entradaLogin
import com.peru.ecolimsac.componentes.logo
import com.peru.ecolimsac.ui.theme.ECOLIMSACTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            start()
        }
    }
}

@Composable
fun Login(padding: PaddingValues) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        logo(Modifier)
        entradaLogin(Modifier)
    }
}

@Composable
fun start(modifier: Modifier = Modifier) {
    ECOLIMSACTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxWidth(1f)
        ) {
            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) { Login(it) }
        }
    }
}