package com.rjp.smartstruct.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "SmartStruct_RJP",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )
        Text("RJP Engenharia — Eurocódigo 2", fontSize = 14.sp)

        DisclaimerCard()

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = { VigaDialogState.show = true }) { Text("Viga") }
            OutlinedButton(onClick = { /* TODO: Pilar */ }) { Text("Pilar") }
            OutlinedButton(onClick = { /* TODO: Laje */ }) { Text("Laje") }
        }

        VigaScreen()
    }
}

@Composable
private fun DisclaimerCard() {
    Card {
        Column(Modifier.padding(12.dp)) {
            Text("Aviso Legal / Disclaimer", fontWeight = FontWeight.Bold)
            Text(
                "Software académico; sem responsabilidade profissional. " +
                "O utilizador deve validar os resultados. Base EC2/EC0."
            )
        }
    }
}
