package com.example.myhealth.presentation.screens.perfil

import com.example.myhealth.model.Result

data class ResultListState(
    val isLoading: Boolean = false,
    val results: List<Result> = emptyList(),
    val error: String = ""
)
