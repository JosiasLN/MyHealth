package com.example.myhealth.presentation.screens.perfil


import com.google.firebase.firestore.DocumentSnapshot

data class UserState(
    val isLoading: Boolean = false,
    val results: DocumentSnapshot? = null,
    val error: String = ""
)
