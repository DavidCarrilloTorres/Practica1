package com.example.practica1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.practica1.viewmodel.BlockingViewModel
import com.example.practica1.viewmodel.ConcurrentViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val blockingViewModel: BlockingViewModel by viewModels()
        val concurrentViewModel: ConcurrentViewModel by viewModels()

        setContent {
            MyApp(blockingViewModel, concurrentViewModel)
        }
    }
}

@Composable
fun MyApp(blockingViewModel: BlockingViewModel, concurrentViewModel: ConcurrentViewModel) {
    var useConcurrent by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Modo de ejecución: ${if (useConcurrent) "No Bloqueante" else "Bloqueante"}")
        Spacer(modifier = Modifier.height(10.dp))

        // Botón para alternar entre bloqueante y no bloqueante
        Button(onClick = { useConcurrent = !useConcurrent }) {
            Text("Cambiar Modo")
        }
        Spacer(modifier = Modifier.height(20.dp))

        if (useConcurrent) {
            Text("Contador: ${concurrentViewModel.counter}")
            Text(concurrentViewModel.resultText)
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = { concurrentViewModel.startConcurrentTask() }) {
                Text("Iniciar No Bloqueante")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = { concurrentViewModel.cancelTask() }, colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error)) {
                Text("Cancelar Tarea")
            }
        } else {
            Text("Contador: ${blockingViewModel.counter}")
            Text(blockingViewModel.resultText)
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = { blockingViewModel.startBlockingTask() }) {
                Text("Iniciar Bloqueante")
            }
        }
    }
}
