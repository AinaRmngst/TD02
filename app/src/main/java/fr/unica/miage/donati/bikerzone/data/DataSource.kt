package fr.unica.miage.donati.bikerzone.data

import fr.unica.miage.donati.bikerzone.R
import fr.unica.miage.donati.bikerzone.models.Bike

class DataSource(){
    fun loadBike() : List<Bike> {
        return listOf(
                Bike("Yamaha MT-07", 689, 7999.0, R.drawable.yamahamt07),
                Bike("Kawasaki Z900", 948, 10189.0, R.drawable.kawazakiz900),
                Bike("Honda CB500F", 471, 6699.0, R.drawable.hondacb500f),
                Bike("KTM Duke 390", 373, 6199.0, R.drawable.ktmduke390),
                Bike("BMW R1250GS", 1254, 19990.0, R.drawable.bmwr1250gs)
            )
    }
    fun loadBike(id : Int) : Bike?{
        return loadBike()[id]
    }
}