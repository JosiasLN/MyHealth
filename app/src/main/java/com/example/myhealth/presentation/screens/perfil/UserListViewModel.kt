package com.example.myhealth.presentation.screens.perfil

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhealth.model.User
import com.example.myhealth.repositories.ResultRepository
import com.example.myhealth.repositories.Resultado
import com.example.myhealth.repositories.ResultadoUser
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserListViewModel
@Inject
constructor(
    private val resultRepository: ResultRepository
): ViewModel(){

    private val _state: MutableState<UserState> = mutableStateOf(UserState())
    val state: State<UserState> = _state

    private val _email: User = User()
    val email: User = _email

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    init {
        getUser()
    }

    fun getUser(): Task<DocumentSnapshot>?{
        return resultRepository.getUser()
    }

}









