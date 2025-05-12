package fr.unica.miage.donati.bikerzone.models

import kotlinx.serialization.Serializable

@Serializable
data class Bike(
    val name: String,
    val power: Int,
    val price: Double,
    val image: Int
)