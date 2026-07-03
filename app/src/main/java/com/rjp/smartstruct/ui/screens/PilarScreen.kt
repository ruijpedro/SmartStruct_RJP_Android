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
import com.rjp.smartstruct.calculation.PillarCalculator
import com.rjp.smartstruct.calculation.PillarInput
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PilarScreen() {
    var materialIndex by remember { mutableIntStateOf(1) }
    var n by remember { mutableStateOf("600") }
    var m by remember { mutableStateOf("45") }
    var altura by remember { mutableStateOf("3.00") }
    var b by remember { mutableStateOf("30") }
    var h by remember { mutableStateOf("30") }
    var rec by remember { mutableStateOf("3") }
    val mat = MaterialLibrary.presets[materialIndex]
    val input = PillarInput(n.toD(600.0), m.toD(45.0), altura.toD(3.0), b.toD(30.0), h.toD(30.0), mat.fckMPa, mat.fykMPa, rec.toD(3.0))
    val result = remember(input) { PillarCalculator.calculate(input) }

    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Módulo Pilares EC2 V4", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Text("Pré-dimensionamento de pilares retangulares: compressão, excentricidade, esbeltez e armadura estimada.")

        Card { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("Materiais", fontWeight = FontWeight.Bold)
            Text("Selecionado: ${mat.name}")
            MaterialLibrary.presets.forEachIndexed { i, preset ->
                AssistChip(onClick = { materialIndex = i }, label = { Text(preset.name) })
            }
        } }

        Card { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("Dados", fontWeight = FontWeight.Bold)
            NumberField("NEd (kN)", n) { n = it }
            NumberField("MEd (kNm)", m) { m = it }
            NumberField("Altura livre (m)", altura) { altura = it }
            NumberField("Largura b (cm)", b) { b = it }
            NumberField("Altura h (cm)", h) { h = it }
            NumberField("Recobrimento (cm)", rec) { rec = it }
        } }

        Card { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text("Resultados", fontWeight = FontWeight.Bold)
            ResultLine("Área betão", "${result.areaConcreteCm2.fmt()} cm²")
            ResultLine("σEd", "${result.designStressMPa.fmt()} MPa")
            ResultLine("0,60 fcd ref.", "${result.reducedConcreteResistanceMPa.fmt()} MPa")
            ResultLine("Excentricidade", "${result.eccentricityCm.fmt()} cm")
            ResultLine("Esbeltez λ", result.slenderness.fmt())
            ResultLine("As,min", "${result.minimumSteelCm2.fmt()} cm²")
            ResultLine("As estimada", "${result.estimatedSteelCm2.fmt()} cm²")
            ResultLine("ρ", "${result.steelRatioPercent.fmt()} %")
            Divider()
            result.warnings.forEach { Text("• $it") }
        } }
    }
}

@Composable
private fun NumberField(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(value = value, onValueChange = onValueChange, label = { Text(label) }, singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal), modifier = Modifier.fillMaxWidth())
}

@Composable
private fun ResultLine(label: String, value: String) { Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) { Text(label); Text(value, fontWeight = FontWeight.Bold) } }
private fun String.toD(default: Double) = replace(',', '.').toDoubleOrNull() ?: default
private fun Double.fmt() = String.format(Locale.US, "%.2f", this)
