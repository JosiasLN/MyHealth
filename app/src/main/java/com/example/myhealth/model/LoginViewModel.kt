package com.example.myhealth.model

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhealth.R
import com.example.myhealth.presentation.screens.login.LoginState
import com.example.myhealth.repositories.ResultRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject
constructor(
    private val resultRepository: ResultRepository
): ViewModel(){

    val state: MutableState<LoginState> = mutableStateOf(LoginState())

    fun login(email: String, password: String){
        val errorMessage: Int? = null
        if(email.isBlank() || password.isBlank()){
            state.value = state.value.copy(errorMessage = R.string.error_input_empty)
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            state.value = state.value.copy(errorMessage = R.string.error_not_a_valid_email)
        }else if (!state.value.succesLogin){
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener{task ->
                if(task.isSuccessful){
                    viewModelScope.launch {
                        state.value = state.value.copy(displayProgressBar = true)
                        delay(3000)
                        state.value = state.value.copy(email = email, password = password)
                        state.value = state.value.copy(displayProgressBar = false)
                        state.value = state.value.copy(succesLogin = true) //Este valor da el acceso al mainActivity

                        resultRepository.getEmail(email)
                    }
                }else{
                    state.value = state.value.copy(errorMessage = R.string.error_unknown)
                }
            }.addOnFailureListener {
                state.value = state.value.copy(errorMessage = R.string.error_invalid_credentials)
            }
        }

        errorMessage?.let {
            state.value = state.value.copy(errorMessage = it)
            return
        }

    }

    fun hideErrorDialog() {
        state.value = state.value.copy(
            errorMessage = null
        )
    }

}