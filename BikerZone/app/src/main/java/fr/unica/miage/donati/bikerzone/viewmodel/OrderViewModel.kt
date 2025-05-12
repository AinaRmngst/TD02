package fr.unica.miage.donati.bikerzone.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import fr.unica.miage.donati.bikerzone.data.AppDatabase
import fr.unica.miage.donati.bikerzone.models.Order
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/*class OrderViewModel(application: Application) : AndroidViewModel(application) {
    private val orderDao = AppDatabase.getDatabase(application).orderDao()
    val allOrders: StateFlow<List<Order>> = orderDao.getAllOrders().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    fun addOrder(order: Order) {
        viewModelScope.launch {
            orderDao.insert(order)
        }
    }
}

class OrderViewModelFactory(
    private val application: Application
) : ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OrderViewModel(application) as T
    }
}*/
