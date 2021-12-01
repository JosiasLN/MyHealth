package com.example.myhealth.presentation.screens.login

import androidx.annotation.StringRes

data class LoginState(
    val email: String = "",
    val password: String = "",
    val succesLogin: Boolean = false,
    val displayProgressBar: Boolean = false,
    @StringRes val errorMessage: Int? = null
)
