package com.example.myhealth.presentation.screens.perfil

import com.example.myhealth.model.Result

data class PerfilState(
    val isLoading: Boolean = false,
    val result: Result? = null,
    val error: String = "",
    val user: Boolean = false //Confirmar si se usa
)
