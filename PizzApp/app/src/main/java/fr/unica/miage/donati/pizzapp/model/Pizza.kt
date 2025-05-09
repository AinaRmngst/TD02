package fr.unica.miage.donati.pizzapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Pizza (
    val name: String,
    val price: Double,
    val image: Int
)
