package com.example.myhealth.navigation

import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.example.myhealth.navigation.Destinations.*
import com.example.myhealth.presentation.screens.*
import com.example.myhealth.presentation.screens.noticias.DetailsNoticias
import com.example.myhealth.presentation.screens.noticias.Noticias
import com.example.myhealth.presentation.screens.noticias.NoticiasViewModel
import com.example.myhealth.presentation.screens.perfil.Perfil
import com.example.myhealth.presentation.screens.perfil.PerfilViewModel
import com.example.myhealth.presentation.screens.perfil.ResultListViewModel

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun NavigationHost(
    navController: NavHostController
) {

    //Aqui se define las diferentes pantallas que se estaran utilizando en la navegacion
    //startDestination es la pantalla en la cual se inicia
    NavHost(navController = navController, startDestination = IMC.route){

        //Aqui se crea la pantalla IMC
        composable(IMC.route){
            Imc(
                navegarResultImc = {resultImc ->
                    navController.navigate(ResultImc.createRouteImc(resultImc))
                }
            )
        }
        //Se crea la pantalla del resultado de IMC
        composable(
            ResultImc.route,
            arguments = listOf(navArgument("resultImc"){ defaultValue = "Vacio" },
            )
        ){navBackStackEntry ->
            val resultImc = navBackStackEntry.arguments?.getString("resultImc")
            requireNotNull(resultImc)

            val viewModel: PerfilViewModel = hiltViewModel()
            val state = viewModel.state.value

            ResultImc(
                resultImc,
                addNewResult = viewModel::addNewResult,
                state = state)
        }



        //Aqui se crea la pantalla PGC
        composable(PGC.route){
            Pgc(
                navegarResultPgc = {resultPgc ->
                    navController.navigate(ResultPgc.createRoutePgc(resultPgc))
                }
            )
        }
        //Se crea la pantalla del resultado para el porcentage de Grasa Corporal
        composable(
            ResultPgc.route,
            arguments = listOf(navArgument("resultPgc"){ defaultValue = "0.0" })
        ){navBackStackEntry ->
            val resultPgc = navBackStackEntry.arguments?.getString("resultPgc")
            requireNotNull(resultPgc)

            val viewModel: PerfilViewModel = hiltViewModel()
            val state = viewModel.state.value

            ResultPgc(
                resultPgc,
                addNewResult = viewModel::addNewResult,
                state = state)
        }



        //Se crea la pantalla de Diabetes
        composable(Diabetes.route){
            Diabetes(
                navegarImc = {navController.navigate(IMC.route)},
                navegarResultDiab = {resultDiab ->
                    navController.navigate(ResultDiab.createRouteDiab(resultDiab))
                }
            )
        }
        //Se crea la pantalla del resultado para Diabetes
        composable(
            ResultDiab.route,
            arguments = listOf(navArgument("resultDiab"){ defaultValue = 0 })
        ){navBackStackEntry ->
            val resultDiab = navBackStackEntry.arguments?.getInt("resultDiab")
            requireNotNull(resultDiab)

            val viewModel: PerfilViewModel = hiltViewModel()
            val state = viewModel.state.value

            ResultDiab(
                resultDiab,
                addNewResult = viewModel::addNewResult,
                state = state)
        }



        //Se crea la pantalla de Noticias (API)
        composable(Noticias.route){
            Noticias(navController)

        }
        //Se crea la pantalla de Detalles de Noticias (API)
        composable("${DetailsNoticias.route}/{newTitle}") {
            it.arguments?.getString("newTitle")?.let { title ->
                DetailsNoticias(title, navController)
            }
        }


        //Se crea la pantalla de Login

        composable(Perfil.route) {
            val viewModel: ResultListViewModel = hiltViewModel()
            val state = viewModel.state.value
            val isRefreshing = viewModel.isRefreshing.collectAsState()

            Perfil(
                state = state,
                isRefreshing = isRefreshing.value,
                refreshData = viewModel::getResultList,
                deleteResult = viewModel::deleteResult,
                email = viewModel.getEmail(),
            )
        }
    }
}