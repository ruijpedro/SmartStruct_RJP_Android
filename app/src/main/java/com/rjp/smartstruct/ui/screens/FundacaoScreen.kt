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
import com.rjp.smartstruct.calculation.FoundationCalculator
import com.rjp.smartstruct.calculation.FoundationInput
import com.rjp.smartstruct.calculation.MaterialLibrary
import java.util.Locale

@Composable
fun FundacaoScreen() {
    var materialIndex by remember { mutableIntStateOf(1) }
    var ned by remember { mutableStateOf("900") }
    var med by remember { mutableStateOf("70") }
    var qadm by remember { mutableStateOf("250") }
    var colB by remember { mutableStateOf("35") }
    var colH by remember { mutableStateOf("35") }
    var sapB by remember { mutableStateOf("220") }
    var sapL by remember { mutableStateOf("220") }
    var sapH by remember { mutableStateOf("55") }

    val mat = MaterialLibrary.presets[materialIndex]
    val input = FoundationInput(
        nedKN = ned.toD(900.0), medKNm = med.toD(70.0), soilPressureKPa = qadm.toD(250.0),
        columnBCm = colB.toD(35.0), columnHCm = colH.toD(35.0),
        footingBCm = sapB.toD(220.0), footingLCm = sapL.toD(220.0), footingHCm = sapH.toD(55.0),
        concreteFckMPa = mat.fckMPa, steelFykMPa = mat.fykMPa
    )
    val result = remember(input) { FoundationCalculator.calculate(input) }

    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Módulo Fundações EC2/EC7 V5", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Text("Pré-dimensionamento de sapata isolada: tensões no terreno, excentricidade, momentos por metro, armadura e punçoamento simplificado.")

        Card { Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Materiais", fontWeight = FontWeight.Bold)
            MaterialLibrary.presets.forEachIndexed { index, m ->
                FilterChip(selected = materialIndex == index, onClick = { materialIndex = index }, label = { Text(m.name) })
            }
        } }

        InputSectionV5("Ações") {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                NumFieldV5("NEd (kN)", ned, { ned = it }, Modifier.weight(1f))
                NumFieldV5("MEd (kNm)", med, { med = it }, Modifier.weight(1f))
            }
            NumFieldV5("q adm terreno (kPa)", qadm, { qadm = it })
        }
        InputSectionV5("Geometria") {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                NumFieldV5("Pilar b (cm)", colB, { colB = it }, Modifier.weight(1f))
                NumFieldV5("Pilar h (cm)", colH, { colH = it }, Modifier.weight(1f))
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                NumFieldV5("Sapata B (cm)", sapB, { sapB = it }, Modifier.weight(1f))
                NumFieldV5("Sapata L (cm)", sapL, { sapL = it }, Modifier.weight(1f))
                NumFieldV5("Altura (cm)", sapH, { sapH = it }, Modifier.weight(1f))
            }
        }

        Card { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text("Resultados", fontWeight = FontWeight.Bold)
            ResultLineV5("Área adotada", "${result.areaM2.fmt()} m²")
            ResultLineV5("Área mínima", "${result.requiredAreaM2.fmt()} m²")
            ResultLineV5("Excentricidade", "${result.eccentricityM.fmt()} m")
            ResultLineV5("σmax / σmin", "${result.sigmaMaxKPa.fmt()} / ${result.sigmaMinKPa.fmt()} kPa")
            ResultLineV5("MEd X / Y", "${result.medXKNmPerM.fmt()} / ${result.medYKNmPerM.fmt()} kNm/m")
            ResultLineV5("As X / Y", "${result.asXcm2PerM.fmt()} / ${result.asYcm2PerM.fmt()} cm²/m")
            ResultLineV5("Punçoamento", "${result.punchingRatio.fmt()} ${if (result.punchingOk) "OK" else "NÃO OK"}")
        } }

        Card { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text("Alertas automáticos", fontWeight = FontWeight.Bold)
            result.warnings.forEach { Text("• $it") }
        } }
    }
}

@Composable
private fun InputSectionV5(title: String, content: @Composable ColumnScope.() -> Unit) {
    Card { Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) { Text(title, fontWeight = FontWeight.Bold); content() } }
}

@Composable
private fun NumFieldV5(label: String, value: String, onChange: (String) -> Unit, modifier: Modifier = Modifier) {
    OutlinedTextField(value = value, onValueChange = onChange, label = { Text(label) }, singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal), modifier = modifier.fillMaxWidth())
}

@Composable
private fun ResultLineV5(label: String, value: String) { Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) { Text(label); Text(value, fontWeight = FontWeight.Bold) } }

private fun Double.fmt(): String = String.format(Locale.US, "%.2f", this)
private fun String.toD(default: Double): Double = replace(',', '.').toDoubleOrNull() ?: default
