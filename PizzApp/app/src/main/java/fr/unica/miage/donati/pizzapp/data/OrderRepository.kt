package fr.unica.miage.donati.pizzapp.data

import fr.unica.miage.donati.pizzapp.model.Order

class OrderRepository(private val orderDao: OrderDao){
    suspend fun insert(order: Order) = orderDao.insert(order)
    suspend fun getAll(): List<Order> = orderDao.getOrders()
    suspend fun getByReference(reference: String): Order = orderDao.getByReference(reference)
}