package com.ecolimsac.finalproject.componentes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavArgument
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ecolimsac.finalproject.Start
import com.ecolimsac.finalproject.views.Vistas

@Composable
fun Navegador(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Vistas.login.id){
        composable(route = Vistas.login.id ){
            Start(navController = navController)
        }
        composable(route = Vistas.home.id+ "/{Usuario}", arguments = listOf(navArgument(name = "Usuario"){
            type = NavType.StringType
        })){
            home(navController = navController, user = it.arguments?.getString("Usuario"))
        }
    }
}