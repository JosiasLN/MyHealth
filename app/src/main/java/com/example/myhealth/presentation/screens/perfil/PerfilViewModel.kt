package com.example.myhealth.presentation.screens.perfil

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myhealth.repositories.ResultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject
import com.example.myhealth.model.Result
import com.example.myhealth.model.User
import com.example.myhealth.presentation.screens.login.LoginState
import com.google.firebase.firestore.FirebaseFirestore

@HiltViewModel
class PerfilViewModel
@Inject
constructor(
    private val resultRepository: ResultRepository
): ViewModel(){

    private val _state: MutableState<PerfilState> = mutableStateOf(PerfilState())
    val state: State<PerfilState>
        get() = _state


    fun addNewResult(tipoResult: String, resultObtenido: String, fecha: String){
        val result = Result(
            id = UUID.randomUUID().toString(),
            tipoResult = tipoResult,
            resultObtenido = resultObtenido,
            fecha = fecha,
        )

        resultRepository.addNewResult(result)
    }
}