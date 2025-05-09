package fr.unica.miage.donati.pizzapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import fr.unica.miage.donati.pizzapp.PizzaRoute
import fr.unica.miage.donati.pizzapp.model.Pizza

@Composable
fun PizzaCard(pizza: Pizza, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        modifier = modifier
            .width(160.dp)
            .height(220.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = pizza.image),
                contentDescription = pizza.name,
                modifier = Modifier
                    .size(80.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = pizza.name,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Prix = ${pizza.price} $",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun PizzaMenu(menu: List<Pizza>, modifier: Modifier = Modifier, navController: NavController) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.padding(8.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(menu) { pizza ->
            PizzaCard(
                pizza = pizza,
                onClick = {
                    navController.navigate(PizzaRoute(menu.indexOf(pizza)))
                }
            )
        }
    }
}
