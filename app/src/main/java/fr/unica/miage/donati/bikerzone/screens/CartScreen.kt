package fr.unica.miage.donati.bikerzone.screens

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import fr.unica.miage.donati.bikerzone.Home
import fr.unica.miage.donati.bikerzone.viewmodel.CartViewModel
import fr.unica.miage.donati.bikerzone.viewmodel.CartViewModelFactory

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    cartViewModel: CartViewModel,
    navController: NavController,
) {
    val cart =cartViewModel.cart
    val total =cartViewModel.getTotal()
    val context = LocalContext.current
    /*val application = context.applicationContext as Application
     val cartViewModel: CartViewModel = viewModel(
    factory = CartViewModelFactory(application)
    )*/

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(cart) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("Bike : ${item.bike.name}")
                            Text("Price : ${item.bike.price}")
                        }
                        IconButton(onClick = {
                            cartViewModel.removeFromCart(item)
                        }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete"
                            )
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))
        Text("Total : $total", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                cartViewModel.confirmOrder {
                    Toast.makeText(context, "Order confirmed", Toast.LENGTH_SHORT).show()
                    navController.navigate(Home)
                }
            }) {
                Text("Confirm Order")
            }
            Button(onClick = {
                cartViewModel.clearCart()
            }) {
                Text("Clear Cart")
            }
        }
    }
}