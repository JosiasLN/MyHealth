package com.example.myhealth.presentation.screens.perfil

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhealth.repositories.ResultRepository
import com.example.myhealth.repositories.Resultado
import com.example.myhealth.repositories.ResultadoUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ResultListViewModel
@Inject
constructor(
    private val resultRepository: ResultRepository
): ViewModel(){

    private val _state: MutableState<ResultListState> = mutableStateOf(ResultListState())
    val state: State<ResultListState> = _state

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    init {
        getResultList()
    }

    fun getResultList(){
        resultRepository.getResultList().onEach { resultado ->
            when(resultado){
                is Resultado.Error -> {
                    _state.value = ResultListState(error = resultado.message?: "Error inesperado")
                }
                is Resultado.Loading -> {
                    _state.value = ResultListState(isLoading = true)
                }
                is Resultado.Success -> {
                    _state.value = ResultListState(results = resultado.data?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }


    fun getEmail(): String{
        return resultRepository.emailUser
    }


    fun deleteResult(resultId: String){
        resultRepository.deleteResult(resultId)
    }



}









