package fr.unica.miage.donati.bikerzone.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import fr.unica.miage.donati.bikerzone.ChatScreen
import fr.unica.miage.donati.bikerzone.ChatScreenWithPrompt
import fr.unica.miage.donati.bikerzone.models.Bike
import fr.unica.miage.donati.bikerzone.viewmodel.CartViewModel
import kotlinx.coroutines.launch

@kotlinx.serialization.Serializable
object CartScreen

@Composable
fun DetailBike(
    bike : Bike,
    modifier: Modifier,
    navController: NavController,
    cartViewModel: CartViewModel =viewModel(),
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton ={
            FloatingActionButton(onClick = {navController.navigate(CartScreen)}) {
                Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "Cart")
            }
        }
    ){ innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = bike.image),
                contentDescription = bike.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = bike.name, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Price : ${bike.power}cc")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Price : ${bike.price}$")
            Spacer(modifier = Modifier.height(16.dp))
            Column (
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    FloatingActionButton(onClick = {
                        cartViewModel.addToCart(bike)
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Bike added to cart")
                        }
                        navController.navigate(CartScreen)
                    }) {
                        Icon(imageVector = Icons.Filled.Send, contentDescription = "Cart")
                    }
                    FloatingActionButton(onClick = {
                        navController.navigate(ChatScreenWithPrompt(bike.name))

                    }) {
                        Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Any Question?")
                    }
                }
            }

        }
    }
}