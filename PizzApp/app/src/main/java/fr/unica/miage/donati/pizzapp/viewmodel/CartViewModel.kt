package fr.unica.miage.donati.pizzapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.google.gson.Gson
import fr.unica.miage.donati.pizzapp.data.AppDatabase
import fr.unica.miage.donati.pizzapp.model.Order
import fr.unica.miage.donati.pizzapp.model.Pizza
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

data class CartItem(
    val pizza: Pizza,
    val extraCheese: Int = 0,
    val extraSauce: Int = 0
)

class CartViewModel(application: Application) : AndroidViewModel(application) {

    private val _cart = mutableListOf<CartItem>()
    val cart: List<CartItem> get() = _cart

    /*private val db: AppDatabase by lazy {
        Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java,
            "pizza-db"
        ).build()
    }

    private val orderDao = db.orderDao()*/
    private val gson = Gson()

    fun addToCart(pizza: Pizza, extraCheese: Int = 0, extraSauce: Int = 0) {
        _cart.add(CartItem(pizza, extraCheese, extraSauce))
    }

    fun getTotal(): Double {
        return _cart.sumOf {
            val cheeseCost = 1.0 * it.extraCheese / 100
            val sauceCost = 1.0 * it.extraSauce / 100
            it.pizza.price + cheeseCost + sauceCost
        }
    }

    fun clearCart() {
        _cart.clear()
    }

    fun confirmOrder(onComplete: (() -> Unit)? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            val reference = UUID.randomUUID().toString().take(8).uppercase()
            val itemsJson = gson.toJson(cart)
            val totalPrice = getTotal()

            val order = Order(
                reference = reference,
                items = itemsJson,
                total = totalPrice
            )

//            orderDao.insert(order)
            _cart.clear() // thread-safe ici car on est dans coroutine unique
            onComplete?.invoke()
        }
    }
}
