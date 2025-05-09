package fr.unica.miage.donati.pizzapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import fr.unica.miage.donati.pizzapp.screens.CartScreen
import fr.unica.miage.donati.pizzapp.screens.DetailPizza
import fr.unica.miage.donati.pizzapp.screens.PizzaMenu
import fr.unica.miage.donati.pizzapp.screens.HomeScreen
import fr.unica.miage.donati.pizzapp.ui.MainLayout
import fr.unica.miage.donati.pizzapp.viewmodel.CartViewModel
import fr.unica.miage.donati.pizzapp.viewmodel.PizzaViewModel

@kotlinx.serialization.Serializable
object Home

@kotlinx.serialization.Serializable
object PizzaList

@kotlinx.serialization.Serializable
object CartScreen

@kotlinx.serialization.Serializable
data class PizzaRoute(val idPizza: Int)

@Composable
fun MyApp() {
    val navController = rememberNavController()
    val cartViewModel: CartViewModel = viewModel()
    val pizzaViewModel: PizzaViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        composable<Home> {
            HomeScreen(navController = navController)
        }

        composable<CartScreen> {
            MainLayout(navController = navController, title = "Cart"){ padding ->
                CartScreen(
                    cartViewModel = cartViewModel,
                    navController = navController,
                    modifier = Modifier.padding(padding))
            }
        }

        composable<PizzaList> {
            MainLayout(navController = navController, title = "Menu") { padding ->
                PizzaMenu(
                    menu = pizzaViewModel.pizzas,
                    modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp),
                    navController = navController
                )
            }
        }

        composable<PizzaRoute> { backStackEntry ->
            val pizzaRoute = backStackEntry.toRoute<PizzaRoute>()
                DetailPizza(
                    pizza = pizzaViewModel.getPizzaById(pizzaRoute.idPizza),
                    navController = navController,
                    cartViewModel = cartViewModel,
                    )
        }

    }
}
