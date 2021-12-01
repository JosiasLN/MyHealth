package com.example.myhealth.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NamedNavArgument


sealed class Destinations(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val arguments: List<NamedNavArgument>
){
    //Pantalla donde se obienen los datos para el IMC
    object IMC: Destinations("IMC", "IMC", Icons.Filled.Fastfood, emptyList())

    //Pantalla de resultado de IMC
    object ResultImc: Destinations("ResultImc/?resultImc={resultImc}", "Resultado IMC", Icons.Filled.Fastfood, emptyList()){
        fun createRouteImc(resultImc: String) = "ResultImc/?resultImc=$resultImc"
    }


    //Pantalla donde se obtienen los datos para el PGC
    object PGC: Destinations("PGC", "PGC", Icons.Filled.Accessibility, emptyList())

    //Pantalla de resultado de PGC
    object ResultPgc: Destinations("ResultPgc/?resultPgc={resultPgc}", "Resultado PGC", Icons.Filled.Accessibility, emptyList()) {
        fun createRoutePgc(resultPgc: String) = "ResultPgc/?resultPgc=$resultPgc"
    }


    //Pantalla donde se obtienen la probabilidad de tener diabetes
    object Diabetes: Destinations("Diabetes", "Diabetes", Icons.Filled.FavoriteBorder, emptyList())

    //Pantalla de resultado de Diabetes
    object ResultDiab: Destinations("ResultDiab/?resultDiab={resultDiab}", "Resultado Diab", Icons.Filled.FavoriteBorder, emptyList()) {
        fun createRouteDiab(resultDiab: Int) = "ResultDiab/?resultDiab=$resultDiab"
    }

    //Pantalla de Noticias (API)
    object Noticias: Destinations("noticias", "Noticias", Icons.Filled.Album, emptyList())
    //Pantalla de Detalles de Noticias (API)
    object DetailsNoticias: Destinations("DETAILS_SCREEN","Detalles",Icons.Filled.Album,emptyList())




    //Pantalla para ver datos del perfil
    object Perfil: Destinations("perfil/?email={email}", "Perfil", Icons.Filled.Person, emptyList()){
        fun getEmail(email: String) = "perfil/?email=$email"
    }


    //Pantalla de Login
    object Login: Destinations("login", "Login", Icons.Filled.Security,emptyList())

    //Pantalla de Registro
    object Register: Destinations("register", "Registro", Icons.Filled.Security,emptyList() )


}
