package com.example.myhealth.presentation.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myhealth.navigation.Destinations

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    items: List<Destinations>
) {
    val currentRoute = currentRoute(navController)

    BottomNavigation{
        BottomNavigation {
            items.forEach{ screen ->
                BottomNavigationItem(
                    icon = { Icon(imageVector = screen.icon, contentDescription = screen.title) },
                    label = {Text(screen.title)},
                    selected = currentRoute == screen.route,
                    onClick = {
                              navController.navigate(screen.route){
                                  popUpTo(navController.graph.findStartDestination().id){
                                      saveState = true
                                  }
                                  launchSingleTop = true
                              }
                    },
                    alwaysShowLabel = false
                )
            }
        }
    }
}


@Composable
private fun currentRoute(navController: NavHostController): String?{
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}