package com.example.practica1.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class BlockingViewModel : ViewModel() {
    var resultText by mutableStateOf("Presiona el botón para iniciar")
        private set

    var counter by mutableStateOf(0)
        private set

    fun startBlockingTask() {
        resultText = "Ejecutando tarea... (La UI se congelará)"
        counter = 0


        for (i in 1..5) {
            Thread.sleep(1000) // Bloquea el hilo principal
            counter = i
        }

        resultText = "Tarea completada"
    }
}
