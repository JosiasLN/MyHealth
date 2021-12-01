package com.example.myhealth.model

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhealth.R
import com.example.myhealth.presentation.screens.register.RegisterState
import com.example.myhealth.repositories.ResultRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.Result as Result

@HiltViewModel
class
RegisterViewModel
@Inject
constructor(
    private val resultRepository: ResultRepository
): ViewModel(){

    val state: MutableState<RegisterState> = mutableStateOf(RegisterState())

    fun register(
        name: String,
        email: String,
        edad: String,
        password: String,
        confirmPassword: String,
        idUser: String
    ){
        val auth: FirebaseAuth = FirebaseAuth.getInstance()
        val errorMessage: Int? = null
        if(name.isBlank() || email.isBlank() || edad.isBlank() || password.isBlank() || confirmPassword.isBlank()){
            state.value = state.value.copy(errorMessage = R.string.error_input_empty)
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            state.value = state.value.copy(errorMessage = R.string.error_not_a_valid_email)
        }else if (password != confirmPassword){
            state.value = state.value.copy(errorMessage = R.string.error_incorrectly_repeated_password)
        }else if (!state.value.successRegister){
            Log.i("RegistroUser", "Entro al registro")
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{task ->
                if(task.isSuccessful){
                    viewModelScope.launch {
                        state.value = state.value.copy(displayProgressBar = true)
                        delay(3000)
                        state.value = state.value.copy(displayProgressBar = false)

                        //Se manda los datos para guardar en la basde de datos
                        val user = User(
                            id = idUser,
                            nombre = name,
                            correo = email,
                            edad = edad,
                        )

                        Log.e("RegisterViewModel Id", ""+user.id)

                        resultRepository.getEmail(email)
                        resultRepository.addNewData(user)
                    }

                }
            }.addOnFailureListener {
                state.value = state.value.copy(errorMessage = R.string.error_invalid)
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

