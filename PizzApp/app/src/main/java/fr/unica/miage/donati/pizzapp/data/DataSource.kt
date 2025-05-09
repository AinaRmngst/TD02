package fr.unica.miage.donati.pizzapp.data

import fr.unica.miage.donati.pizzapp.R
import fr.unica.miage.donati.pizzapp.model.Pizza

class DataSource (){
    fun loadPizzas(): List<Pizza>{
        return listOf(
            Pizza("Marguerita", 8.0, R.drawable.pizza1),
            Pizza("Cappriciosa", 10.0, R.drawable.pizza2),
            Pizza("Diavola", 12.0, R.drawable.pizza3),
            Pizza("Quattro Stagioni", 14.0, R.drawable.pizza4),
            Pizza("Quattro Fromaggi", 16.0, R.drawable.pizza5),
            Pizza("Marinara", 18.0, R.drawable.pizza6),
            Pizza("Pepperoni", 20.0, R.drawable.pizza7),
            Pizza("Prosciutto", 22.0, R.drawable.pizza8),
            Pizza("Frutti Di Mare", 24.0, R.drawable.pizza9),
            
        )
    }

    fun loadPizza(id: Int): Pizza {
        return loadPizzas()[id]
    }
}