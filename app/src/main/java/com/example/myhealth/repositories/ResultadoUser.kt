package com.example.myhealth.repositories

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot

sealed class ResultadoUser<T>(
    val data: DocumentSnapshot? = null,
    val message: String? = null,
){
    //class Success<T>(data: Task<DocumentSnapshot>): ResultadoUser<T>(data)

    class Error<T>(message: String?, data: DocumentSnapshot? = null): ResultadoUser<T>(data, message)

    class Loading<T>(data: DocumentSnapshot? = null): ResultadoUser<T>(data)
}
