package fr.unica.miage.donati.bikerzone.screens

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/*@Composable
fun HistoryScreen(
    orderViewModel: OrderViewModel = viewModel(
        factory = OrderViewModelFactory(LocalContext.current.applicationContext as Application)
    )
) {
    val orders by orderViewModel.allOrders.collectAsState()

    LazyColumn {
        items(orders) { order ->
            val date = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date(order.timestamp))
            Card(modifier = Modifier.padding(8.dp)) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text("Ref: ${order.reference}")
                    Text("Bikes: ${order.items}")
                    Text("Total: ${order.total}")
                    Text("Date: $date")
                }
            }
        }
    }
}*/
