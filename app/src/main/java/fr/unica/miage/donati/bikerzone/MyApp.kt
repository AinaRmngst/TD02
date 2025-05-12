package fr.unica.miage.donati.bikerzone

import android.app.Application
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import fr.unica.miage.donati.bikerzone.screens.BikeMenu
import fr.unica.miage.donati.bikerzone.screens.CartScreen
import fr.unica.miage.donati.bikerzone.screens.ChatScreen
import fr.unica.miage.donati.bikerzone.screens.DetailBike
import fr.unica.miage.donati.bikerzone.screens.HomeScreen
import fr.unica.miage.donati.bikerzone.ui.MainLayout
import fr.unica.miage.donati.bikerzone.viewmodel.BikeViewModel
import fr.unica.miage.donati.bikerzone.viewmodel.CartViewModel
import fr.unica.miage.donati.bikerzone.viewmodel.CartViewModelFactory

@kotlinx.serialization.Serializable
object Home

@kotlinx.serialization.Serializable
object BikeList

@kotlinx.serialization.Serializable
object CartScreen

@kotlinx.serialization.Serializable
object ChatScreen

@kotlinx.serialization.Serializable
data class BikeRoute(val idBike: Int)

@kotlinx.serialization.Serializable
data class ChatScreenWithPrompt(val bikeName: String)

@Composable
fun MyApp(
    navController: NavHostController = rememberNavController()
){
    val bikeViewModel:BikeViewModel = viewModel()
    val context = LocalContext.current
    val application = context.applicationContext as Application
    val cartViewModel:CartViewModel = viewModel(
        factory = CartViewModelFactory(application)
    )

    NavHost(
        navController = navController,
        startDestination = Home
    )  {
        composable<Home>{
            HomeScreen(navController = navController)
        }

        composable<CartScreen>{
            MainLayout(navController = navController, title = "Cart"){ padding ->
                CartScreen(
                    cartViewModel = cartViewModel,
                    navController = navController,
                    modifier = Modifier.padding(padding))
            }
        }

        composable<BikeList> {
            MainLayout(navController = navController, title = "Menu") { padding ->
                BikeMenu(
                    article = bikeViewModel.bikes,
                    modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp),
                    navController = navController
                )
            }
        }

        composable<ChatScreen>{
            MainLayout(navController = navController, title = "Chat") { padding ->
                ChatScreen(
                    navController = navController,
                    modifier = Modifier.padding(padding),
                    onBack = { navController.popBackStack() }
                )
            }
        }

        composable<BikeRoute>{ backStackEntry ->
            val bikeRoute =backStackEntry.toRoute<BikeRoute>()
            DetailBike(
                bike = bikeViewModel.getBikeById(bikeRoute.idBike),
                modifier = Modifier,
                navController = navController,
                cartViewModel = cartViewModel,
            )
        }

        composable<ChatScreenWithPrompt> { backStackEntry ->
            val bikeName = backStackEntry.toRoute<ChatScreenWithPrompt>().bikeName
            MainLayout(navController = navController, title = "Chat for $bikeName") { padding ->
                ChatScreen(
                    navController = navController,
                    modifier = Modifier.padding(padding),
                    onBack = { navController.popBackStack() },
                    initialPrompt = "Parle en tant qu'expert sur : $bikeName. Propose une aide à l'utilisateur."
                )
            }
        }
    }
}