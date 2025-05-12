package fr.unica.miage.donati.bikerzone.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import fr.unica.miage.donati.bikerzone.BikeList
import fr.unica.miage.donati.bikerzone.Home
import fr.unica.miage.donati.bikerzone.screens.CartScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLayout(
    navController: NavController,
    title: String,
    content: @Composable (PaddingValues) -> Unit
){
    Scaffold(
        topBar ={
            TopAppBar(
                title = { Text(title) }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(BikeList) },
                    icon = { Icon(Icons.Default.Menu, contentDescription = "Bike") },
                    label = { Text("Bike List") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(Home) },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(CartScreen) },
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Cart") },
                    label = { Text("Cart") }
                )
            }
        },
        content ={paddingValues -> content(paddingValues)}
    )
}