package fr.unica.miage.donati.bikerzone.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import fr.unica.miage.donati.bikerzone.models.Bike
import fr.unica.miage.donati.bikerzone.models.Order
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID


data class CartItem(
    val bike: Bike,
)

class CartViewModel(
    application : Application
): AndroidViewModel(application){
    private val _cart = mutableStateListOf<CartItem>()
    val cart:List<CartItem> get() = _cart

    private val gson= Gson()

    fun addToCart(bike : Bike){
        _cart.add(CartItem(bike))
    }

    fun getTotal():Double{
        return _cart.sumOf { it.bike.price }
    }

    fun clearCart(){
        _cart.clear()
    }

    fun removeFromCart(item : CartItem){
        _cart.remove(item)
    }

    fun confirmOrder(
       // orderViewModel: OrderViewModel,
        onComplete: (() -> Unit)? = null
    ){
        viewModelScope.launch(Dispatchers.IO) {
            val reference =UUID.randomUUID().toString().take(8).uppercase()
            /*val itemsJson = gson.toJson(cart)
            val totalPrice = getTotal()

            val order = Order(reference, itemsJson, totalPrice)
            orderViewModel.addOrder(order)*/

        }
        _cart.clear()
        onComplete?.invoke()
    }
}
class CartViewModelFactory(
    private val application: Application
): ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T: ViewModel> create (modelClass: Class<T>): T {
        return CartViewModel(application) as T
    }
}