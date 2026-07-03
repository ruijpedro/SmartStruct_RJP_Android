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
import com.rjp.smartstruct.calculation.MaterialLibrary
import com.rjp.smartstruct.calculation.SlabCalculator
import com.rjp.smartstruct.calculation.SlabInput
import java.util.Locale

@Composable
fun LajeScreen() {
    var materialIndex by remember { mutableIntStateOf(1) }
    var l by remember { mutableStateOf("4.00") }
    var g by remember { mutableStateOf("5.00") }
    var q by remember { mutableStateOf("3.00") }
    var esp by remember { mutableStateOf("16") }
    var rec by remember { mutableStateOf("3") }
    val mat = MaterialLibrary.presets[materialIndex]
    val input = SlabInput(l.toD(4.0), g.toD(5.0), q.toD(3.0), esp.toD(16.0), rec.toD(3.0), mat.fckMPa, mat.fykMPa)
    val result = remember(input) { SlabCalculator.calculate(input) }

    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Módulo Lajes EC2 V4", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Text("Pré-dimensionamento de laje unidirecional simplesmente apoiada por faixa de 1 metro.")
        Card { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("Materiais", fontWeight = FontWeight.Bold)
            MaterialLibrary.presets.forEachIndexed { i, preset -> AssistChip(onClick = { materialIndex = i }, label = { Text(preset.name) }) }
        } }
        Card { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("Dados", fontWeight = FontWeight.Bold)
            NumberField2("Vão L (m)", l) { l = it }
            NumberField2("Carga permanente G (kN/m²)", g) { g = it }
            NumberField2("Sobrecarga Q (kN/m²)", q) { q = it }
            NumberField2("Espessura (cm)", esp) { esp = it }
            NumberField2("Recobrimento (cm)", rec) { rec = it }
        } }
        Card { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text("Resultados por metro", fontWeight = FontWeight.Bold)
            ResultLine2("qEd", "${result.designLoadKNm2.fmt()} kN/m²")
            ResultLine2("MEd", "${result.momentKNmPerM.fmt()} kNm/m")
            ResultLine2("VEd", "${result.shearKNPerM.fmt()} kN/m")
            ResultLine2("d", "${result.effectiveDepthCm.fmt()} cm")
            ResultLine2("As,req", "${result.requiredSteelCm2PerM.fmt()} cm²/m")
            ResultLine2("As,min", "${result.minimumSteelCm2PerM.fmt()} cm²/m")
            ResultLine2("As adotada", "${result.governingSteelCm2PerM.fmt()} cm²/m")
            ResultLine2("L/d", result.spanDepthRatio.fmt())
            Divider()
            result.warnings.forEach { Text("• $it") }
        } }
    }
}

@Composable
private fun NumberField2(label: String, value: String, onValueChange: (String) -> Unit) { OutlinedTextField(value = value, onValueChange = onValueChange, label = { Text(label) }, singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal), modifier = Modifier.fillMaxWidth()) }
@Composable
private fun ResultLine2(label: String, value: String) { Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) { Text(label); Text(value, fontWeight = FontWeight.Bold) } }
private fun String.toD(default: Double) = replace(',', '.').toDoubleOrNull() ?: default
private fun Double.fmt() = String.format(Locale.US, "%.2f", this)
