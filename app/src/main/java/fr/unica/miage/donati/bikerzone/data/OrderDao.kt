package fr.unica.miage.donati.bikerzone.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.unica.miage.donati.bikerzone.models.Order
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert (order : Order)

    @Query("SELECT * FROM orders ORDER BY timestamp DESC")
    fun getAllOrders() : Flow<List<Order>>
}