package fr.unica.miage.donati.pizzapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order")
data class Order(
    @PrimaryKey val reference: String,
    val items: String,
    val total: Double
)