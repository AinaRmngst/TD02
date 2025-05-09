package fr.unica.miage.donati.pizzapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import fr.unica.miage.donati.pizzapp.model.Order

@Dao
interface OrderDao{
    @Insert
    suspend fun insert(order: Order)

    @Query("SELECT * FROM `order`")
    suspend fun getOrders(): List<Order>

    @Query("SELECT * FROM `order` WHERE reference = :reference")
    suspend fun getByReference(reference: String): Order
}