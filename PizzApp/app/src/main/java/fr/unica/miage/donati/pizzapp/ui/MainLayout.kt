package fr.unica.miage.donati.pizzapp.ui

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import fr.unica.miage.donati.pizzapp.CartScreen
import fr.unica.miage.donati.pizzapp.Home
import fr.unica.miage.donati.pizzapp.PizzaList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLayout(
    navController: NavController,
    title: String,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(PizzaList) },
                    icon = { Icon(Icons.Default.Menu, contentDescription = "Plat") },
                    label = { Text("Plat") }
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
        content = {paddingValues -> content(paddingValues)}
    )
}
