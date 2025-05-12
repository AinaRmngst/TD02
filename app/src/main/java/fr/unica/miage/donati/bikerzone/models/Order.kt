package fr.unica.miage.donati.bikerzone.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class Order(
    @PrimaryKey val reference: String,
    val items: String,
    val total: Double,
    val timestamp: Long = System.currentTimeMillis()
)
