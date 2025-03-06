package com.ecolimsac.finalproject.dto

import com.ecolimsac.finalproject.entidad.TipoBasura
import com.ecolimsac.finalproject.entidad.Usuario

data class Basura(
    val tipoBasura: TipoBasura,
    val cantidad: Float,
    val colaborador: String
)
