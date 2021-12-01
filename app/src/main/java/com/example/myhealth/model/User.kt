package com.example.myhealth.model

data class User(
    val id: String,
    val nombre: String,
    val correo: String,
    val edad: String,
){
    constructor(): this("","","","")
}

