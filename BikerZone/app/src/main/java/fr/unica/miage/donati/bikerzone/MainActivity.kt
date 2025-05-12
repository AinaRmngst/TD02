package fr.unica.miage.donati.bikerzone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import fr.unica.miage.donati.bikerzone.ui.theme.BikerZoneTheme

    class MainActivity : ComponentActivity(){
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContent {
                BikerZoneTheme {
                    MyApp()
                }
            }
        }
    }