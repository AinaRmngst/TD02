package fr.unica.miage.donati.pizzapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import fr.unica.miage.donati.pizzapp.viewmodel.CartViewModel

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    cartViewModel: CartViewModel = viewModel(),
    navController: NavController,
    ) {
    val cart = cartViewModel.cart
    val total = cartViewModel.getTotal()

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(cart) { item ->
                Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Pizza : ${item.pizza.name}")
                        Text("Prix de base : ${item.pizza.price} $")
                        if (item.extraCheese > 0) {
                            Text("Extra Cheese : ${item.extraCheese}%")
                        }
                        if( item.extraSauce > 0) {
                            Text("Extra Sauce : ${item.extraSauce}%")
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Text("Total : $total $", style = MaterialTheme.typography.headlineSmall)

        Spacer(Modifier.height(16.dp))

        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ){
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){Button(
            onClick = {
                // à implémenter plus tard
            }
        ) {
            Text("Confirm Order")
        }
        Button(
            onClick = {
                // à implémenter plus tard
            }
        ) {
            Text("Clear Cart")
        }}}
    }
}
