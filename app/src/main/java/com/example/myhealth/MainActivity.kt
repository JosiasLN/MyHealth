package com.example.myhealth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.example.myhealth.navigation.Destinations.*
import com.example.myhealth.navigation.NavigationHost
import com.example.myhealth.presentation.components.BottomNavigationBar
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalCoilApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                MainScreen()
        }
    }
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navigationItems = listOf(
        IMC,
        PGC,
        Diabetes,
        Noticias,
        Perfil
    )
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController, items = navigationItems) },
        contentColor = Color.Black,

    ){
        NavigationHost(navController)
    }
}




