package com.example.myhealth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.example.myhealth.model.LoginViewModel
import com.example.myhealth.model.RegisterViewModel
import com.example.myhealth.navigation.Destinations
import com.example.myhealth.presentation.screens.login.Login
import com.example.myhealth.presentation.screens.perfil.PerfilViewModel
import com.example.myhealth.presentation.screens.register.Registration
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalCoilApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class InicioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InicioScreen()
        }
    }
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun InicioScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destinations.Login.route) {
        composable(route = Destinations.Login.route) {
            val viewModel: LoginViewModel = hiltViewModel()

            if (viewModel.state.value.succesLogin) {
                MainScreen()
            } else {
                Login(
                    state = viewModel.state.value,
                    onLogin = viewModel::login,
                    onNavigationToRegister = {
                        navController.navigate(Destinations.Register.route)
                    },
                    onDismissDialog = viewModel::hideErrorDialog,
                )
            }
        }

        composable(Destinations.Register.route) {
            val viewModel: RegisterViewModel = hiltViewModel()

            Registration(
                state = viewModel.state.value,
                onRegister = viewModel::register,
                onBack = {
                    navController.popBackStack()
                },
                onDismissDialog = viewModel::hideErrorDialog,
            )
        }
    }
}


