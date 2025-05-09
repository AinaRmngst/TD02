package fr.unica.miage.donati.pizzapp.viewmodel

import androidx.lifecycle.ViewModel
import fr.unica.miage.donati.pizzapp.data.DataSource
import fr.unica.miage.donati.pizzapp.model.Pizza
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class OrderItem(
    val pizza: Pizza,
    val extraCheese: Int = 0,
    val extraSauce: Int = 0
)

class PizzaViewModel : ViewModel() {

    private val dataSource = DataSource()
    val pizzas: List<Pizza> = dataSource.loadPizzas()

    private val _cart = MutableStateFlow<List<OrderItem>>(emptyList())
    val cart: StateFlow<List<OrderItem>> = _cart

    fun getPizzaById(id: Int): Pizza =
        pizzas.getOrElse(id) { pizzas[0] }

    fun addToCart(pizza: Pizza, extraCheese: Int = 0, extraSauce: Int = 0) {
        val updatedCart = _cart.value.toMutableList()
        updatedCart.add(OrderItem(pizza, extraCheese, extraSauce))
        _cart.value = updatedCart
    }

    fun removeFromCart(item: OrderItem) {
        val updatedCart = _cart.value.toMutableList()
        updatedCart.remove(item)
        _cart.value = updatedCart
    }

    fun getTotalPrice(): Double {
        return _cart.value.sumOf {
            val cheeseCost = (it.extraCheese / 50.0) * 0.5
            val sauceCost = (it.extraSauce / 50.0) * 0.5
            it.pizza.price + cheeseCost + sauceCost
        }
    }
}
