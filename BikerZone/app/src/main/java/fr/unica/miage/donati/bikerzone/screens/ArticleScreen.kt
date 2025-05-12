package fr.unica.miage.donati.bikerzone.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import fr.unica.miage.donati.bikerzone.BikeRoute
import fr.unica.miage.donati.bikerzone.models.Bike

@Composable
fun ArticleCard(
    bike : Bike,
    modifier: Modifier = Modifier,
    onClick: () -> Unit) {
    Card(
        modifier = modifier
            .width(160.dp)
            .height(220.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = onClick
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = bike.image),
                contentDescription = bike.name,
                modifier = Modifier.height(80.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = bike.name,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 16.sp,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Power = ${bike.power} cc",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Price = ${bike.price} $",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun BikeMenu(
    article: List<Bike>,
    modifier: Modifier = Modifier,
    navController: NavController) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.padding(8.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(article) { bike ->
            ArticleCard(
                bike = bike,
                onClick = {
                    navController.navigate(BikeRoute(article.indexOf(bike)))
                }
            )
        }
    }
}