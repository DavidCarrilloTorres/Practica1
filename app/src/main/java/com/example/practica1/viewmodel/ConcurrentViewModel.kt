package com.example.practica1.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class ConcurrentViewModel : ViewModel() {
    var resultText by mutableStateOf("Presiona el botón para iniciar")
        private set

    var counter by mutableStateOf(0)
        private set

    private var job: Job? = null

    fun startConcurrentTask() {
        job?.cancel() // Cancela cualquier tarea en ejecución

        job = viewModelScope.launch(Dispatchers.IO) {
            resultText = "Ejecutando tarea en segundo plano..."
            counter = 0

            for (i in 1..5) {
                delay(1000)  // No bloquea la UI
                counter = i
            }

            withContext(Dispatchers.Main) {
                resultText = "Tarea completada"
            }
        }
    }

    fun cancelTask() {
        job?.cancel()
        resultText = "Tarea cancelada"
        counter = 0
    }
}
