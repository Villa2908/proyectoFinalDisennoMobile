package com.ecolimsac.finalproject

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
import androidx.navigation.NavController
import com.ecolimsac.finalproject.componentes.EntradaLogin
import com.ecolimsac.finalproject.componentes.Logo
import com.ecolimsac.finalproject.componentes.Navegador
import com.ecolimsac.finalproject.ui.theme.ECOLIMSACTheme

class MainActivity : ComponentActivity() {

    //private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Navegador()
        }
    }
}

@Composable
fun Login(padding: PaddingValues, navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Logo(Modifier)
        EntradaLogin(Modifier, navController)
    }
}

@Composable
fun Start(modifier: Modifier = Modifier, navController: NavController) {
    ECOLIMSACTheme {
        Scaffold(
            modifier = modifier
                .fillMaxWidth(1f)
        ) {
            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) { Login(it, navController) }
        }
    }
}