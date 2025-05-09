package fr.unica.miage.donati.pizzapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.unica.miage.donati.pizzapp.model.Order

@Database(entities = [Order::class], version= 1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun orderDao(): OrderDao
}