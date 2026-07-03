package com.rjp.smartstruct.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.rjp.smartstruct.calculation.LoadCombinationCalculator
import com.rjp.smartstruct.calculation.LoadInput

@Composable
fun CombinacoesScreen() {
    var gkText by remember { mutableStateOf("5.0") }
    var qkText by remember { mutableStateOf("3.0") }
    var psi0Text by remember { mutableStateOf("0.7") }
    var psi1Text by remember { mutableStateOf("0.5") }
    var psi2Text by remember { mutableStateOf("0.3") }

    val result = remember(gkText, qkText, psi0Text, psi1Text, psi2Text) {
        val gk = gkText.toDoubleOrNull() ?: 0.0
        val qk = qkText.toDoubleOrNull() ?: 0.0
        val psi0 = psi0Text.toDoubleOrNull() ?: 0.7
        val psi1 = psi1Text.toDoubleOrNull() ?: 0.5
        val psi2 = psi2Text.toDoubleOrNull() ?: 0.3
        LoadCombinationCalculator.calculate(LoadInput(gk, qk, psi0, psi1, psi2))
    }

    Column(
        Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Combinações EC0/EC1", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Text("Introduz cargas características por metro ou por área. A app calcula combinações base para ELU e ELS.")

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            SmartNumberField("Gk", gkText, { gkText = it }, Modifier.weight(1f))
            SmartNumberField("Qk", qkText, { qkText = it }, Modifier.weight(1f))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            SmartNumberField("ψ0", psi0Text, { psi0Text = it }, Modifier.weight(1f))
            SmartNumberField("ψ1", psi1Text, { psi1Text = it }, Modifier.weight(1f))
            SmartNumberField("ψ2", psi2Text, { psi2Text = it }, Modifier.weight(1f))
        }

        Card { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Resultados", fontWeight = FontWeight.Bold)
            ResultLine("ELU", result.elu, "1.35 Gk + 1.50 Qk")
            ResultLine("ELS rara", result.elsRare, "Gk + Qk")
            ResultLine("ELS frequente", result.elsFrequent, "Gk + ψ1 Qk")
            ResultLine("ELS quase permanente", result.elsQuasiPermanent, "Gk + ψ2 Qk")
            Divider()
            Text("Caso condicionante visual: ${result.governing} = ${"%.2f".format(result.governingValue)}", fontWeight = FontWeight.Bold)
        } }

        Card { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text("Nota", fontWeight = FontWeight.Bold)
            Text("Valores de ψ devem ser confirmados conforme categoria de utilização e Anexo Nacional aplicável.")
        } }
    }
}

@Composable
private fun SmartNumberField(label: String, value: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        modifier = modifier
    )
}

@Composable
private fun ResultLine(label: String, value: Double, formula: String) {
    Column {
        Text("$label: ${"%.2f".format(value)}", fontWeight = FontWeight.SemiBold)
        Text(formula, style = MaterialTheme.typography.bodySmall)
    }
}
