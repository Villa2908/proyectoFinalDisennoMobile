package com.ecolimsac.finalproject.componentes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ecolimsac.finalproject.Start
import com.ecolimsac.finalproject.views.Vistas

@Composable
fun Navegador(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Vistas.login.id){
        composable(route = Vistas.login.id){
            Start()
        }
        composable(route = Vistas.home.id){
//            Realizar la vista home
        }
    }
}