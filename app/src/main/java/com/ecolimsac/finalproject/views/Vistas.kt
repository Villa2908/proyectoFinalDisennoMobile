package com.ecolimsac.finalproject.views

sealed class Vistas (val id: String){
    object home: Vistas("Home")
    object login: Vistas("Login")
}