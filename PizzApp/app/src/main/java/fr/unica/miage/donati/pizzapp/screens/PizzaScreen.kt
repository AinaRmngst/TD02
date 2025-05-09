package fr.unica.miage.donati.pizzapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import fr.unica.miage.donati.pizzapp.model.Pizza
import fr.unica.miage.donati.pizzapp.viewmodel.CartViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@kotlinx.serialization.Serializable
object CartScreen

@Composable
fun DetailPizza(
    pizza: Pizza,
    modifier: Modifier = Modifier,
    navController: NavController,
    cartViewModel: CartViewModel = viewModel(),
) {
    var extraCheese by remember { mutableStateOf(50) }
    var extraSauce by remember { mutableStateOf(50) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState)},
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(CartScreen) }) {
                Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "Order")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = pizza.image),
                contentDescription = pizza.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = pizza.name, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Price : ${pizza.price}$")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Extra cheese : $extraCheese%")
            Slider(
                value = extraCheese.toFloat(),
                onValueChange = { extraCheese = it.toInt() },
                valueRange = 0f..100f,
                steps = 4
            )
            Text(text = "+ ${(extraCheese / 50.0 * 0.5).toBigDecimal().setScale(2)}$")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Extra Sauce : $extraSauce%")
            Slider(
                value = extraSauce.toFloat(),
                onValueChange = { extraSauce = it.toInt() },
                valueRange = 0f..100f,
                steps = 4
            )
            Text(text = "+ ${(extraSauce / 50.0 * 0.5).toBigDecimal().setScale(2)}$")
            Spacer(modifier = Modifier.height(16.dp))
            FloatingActionButton(onClick = {
                cartViewModel.addToCart(pizza, extraCheese, extraSauce)
                coroutineScope.launch{
                    snackbarHostState.showSnackbar("Pizza added to cart")
                }
            }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
            }
        }
    }
}
