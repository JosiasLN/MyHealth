package com.example.myhealth.repositories

import android.util.Log
import com.example.myhealth.model.Result
import com.example.myhealth.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResultRepository
@Inject
constructor(
    private val resultList: CollectionReference
){
    var emailUser: String = ""
    //obtiene el email con el que se registro o se hizo login el usuario
    fun getEmail(email: String): String{
        emailUser = email
        Log.e("getEmail", ""+emailUser)
        return emailUser
    }
    //obtiene los datos del usuario
    fun addNewData(user: User){
        try {
            resultList.document(emailUser).set(
                hashMapOf(
                    "nombre" to user.nombre,
                    "correo" to user.correo,
                    "edad" to user.edad,
                    "id" to user.id
                )
            )
            Log.e("addNewUser", ""+user.correo)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }


    //Obtiene los resultados de los calculos que realizo el usuario
    fun addNewResult(result: Result){
        Log.e("Entro a addNewResult", ""+emailUser)
        try {
                resultList.document(emailUser).collection("resultados").document(result.id).set(
                    hashMapOf(
                        "fecha" to result.fecha,
                        "tipoResult" to result.tipoResult,
                        "resultObtenido" to result.resultObtenido,
                        "id" to result.id
                    )
                )
                Log.e("addNewResult", ""+emailUser)

        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun getResultList(): Flow<Resultado<List<Result>>> = flow{
        Log.e("entro a getResult", "")
        try {
            emit(Resultado.Loading<List<Result>>())
            val resultList = resultList.document(emailUser).collection("resultados").get().await().map{ document ->
                document.toObject(Result::class.java)
            }
            emit(Resultado.Success<List<Result>>(data = resultList))

        }catch (e: Exception){
            emit(Resultado.Error<List<Result>>(message = e.localizedMessage ?: "Error desconocido"))
        }
    }

    fun getUser(): Task<DocumentSnapshot>? {
        Log.e("entro a getUser", ""+emailUser)
        return try {
            resultList.document(emailUser).get()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }




    fun deleteResult(resultId: String){
        try {
            Log.e("funcion eliminar email", ""+emailUser)
            Log.e("eliminar ID", ""+resultId)
            resultList.document(emailUser).collection("resultados").document(resultId).delete()
        }catch (e: Exception){
            Log.e("eliminar ID", ""+resultId)
            e.printStackTrace()
        }
    }


}