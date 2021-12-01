package com.example.myhealth.model

data class Result(
    val id: String,
    val tipoResult: String,
    val fecha: String,
    val resultObtenido: String,

){
    constructor(): this("","","","")
}
