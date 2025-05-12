package fr.unica.miage.donati.bikerzone.viewmodel

import androidx.lifecycle.ViewModel
import fr.unica.miage.donati.bikerzone.data.DataSource
import fr.unica.miage.donati.bikerzone.models.Bike
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class OrderItem(val bike: Bike)

class BikeViewModel :ViewModel(){
    private val dataSource= DataSource()
    val bikes: List<Bike> get() = dataSource.loadBike()

    private val _cart = MutableStateFlow<List<OrderItem>>(emptyList())
    val cart: StateFlow<List<OrderItem>> =_cart

    fun getBikeById(id: Int): Bike =
        bikes.getOrElse(id) { bikes[0] }
}