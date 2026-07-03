package com.rjp.smartstruct.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.rjp.smartstruct.calculation.BeamCalculator
import com.rjp.smartstruct.calculation.BeamInput
import com.rjp.smartstruct.calculation.BeamResult
import com.rjp.smartstruct.calculation.BeamType
import com.rjp.smartstruct.calculation.MaterialLibrary
import java.util.Locale
import kotlin.math.abs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VigaScreen() {
    var beamType by remember { mutableStateOf(BeamType.SIMPLY_SUPPORTED) }
    var materialIndex by remember { mutableIntStateOf(1) }
    var l by remember { mutableStateOf("5.00") }
    var g by remember { mutableStateOf("12.00") }
    var q by remember { mutableStateOf("6.00") }
    var p by remember { mutableStateOf("0.00") }
    var a by remember { mutableStateOf("2.50") }
    var b by remember { mutableStateOf("25") }
    var h by remember { mutableStateOf("50") }
    var rec by remember { mutableStateOf("3") }

    val mat = MaterialLibrary.presets[materialIndex]
    val input = BeamInput(
        beamType = beamType,
        spanM = l.toD(5.0),
        permanentLoadKNm = g.toD(12.0),
        variableLoadKNm = q.toD(6.0),
        pointLoadKN = p.toD(0.0),
        pointLoadPositionM = a.toD(2.5),
        widthCm = b.toD(25.0),
        heightCm = h.toD(50.0),
        coverCm = rec.toD(3.0),
        concreteFckMPa = mat.fckMPa,
        steelFykMPa = mat.fykMPa
    )
    val result = remember(input) { BeamCalculator.calculate(input) }

    Column(
        Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Módulo Vigas EC2 V3", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Text("Biapoiada ou consola, cargas G/Q/P, biblioteca de materiais, diagramas e verificação de flecha.")

        Card { Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Tipo de viga", fontWeight = FontWeight.Bold)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FilterChip(selected = beamType == BeamType.SIMPLY_SUPPORTED, onClick = { beamType = BeamType.SIMPLY_SUPPORTED }, label = { Text("Biapoiada") })
                FilterChip(selected = beamType == BeamType.CANTILEVER, onClick = { beamType = BeamType.CANTILEVER }, label = { Text("Consola") })
            }
        } }

        InputSection("Geometria") {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                NumField("Vão L (m)", l, { l = it }, Modifier.weight(1f))
                NumField("Posição P a (m)", a, { a = it }, Modifier.weight(1f))
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                NumField("b (cm)", b, { b = it }, Modifier.weight(1f))
                NumField("h (cm)", h, { h = it }, Modifier.weight(1f))
                NumField("rec. (cm)", rec, { rec = it }, Modifier.weight(1f))
            }
        }

        InputSection("Ações") {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                NumField("G (kN/m)", g, { g = it }, Modifier.weight(1f))
                NumField("Q (kN/m)", q, { q = it }, Modifier.weight(1f))
                NumField("P (kN)", p, { p = it }, Modifier.weight(1f))
            }
        }

        InputSection("Materiais") {
            var expanded by remember { mutableStateOf(false) }
            ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
                OutlinedTextField(
                    value = mat.name,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Betão + aço") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    MaterialLibrary.presets.forEachIndexed { index, preset ->
                        DropdownMenuItem(text = { Text(preset.name) }, onClick = { materialIndex = index; expanded = false })
                    }
                }
            }
        }

        ResultsCard(result)
        if (result.warnings.isNotEmpty()) Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text("Alertas", fontWeight = FontWeight.Bold)
                result.warnings.forEach { Text("• $it") }
            }
        }
        DiagramCard("Diagrama VEd (kN)", result, shear = true)
        DiagramCard("Diagrama MEd (kNm)", result, shear = false)
        ReportCard(result)
    }
}

@Composable
private fun InputSection(title: String, content: @Composable ColumnScope.() -> Unit) {
    Card { Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) { Text(title, fontWeight = FontWeight.Bold); content() } }
}

@Composable
private fun NumField(label: String, value: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier) {
    OutlinedTextField(value = value, onValueChange = { onValueChange(it.replace(',', '.')) }, label = { Text(label) }, singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal), modifier = modifier)
}

@Composable
private fun ResultsCard(r: BeamResult) {
    Card { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text("Resultados principais", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        ResultLine("Tipo", r.beamType.label, "")
        ResultLine("qEd", r.designLoadKNm, "kN/m")
        ResultLine("q serviço", r.serviceLoadKNm, "kN/m")
        ResultLine("PEd", r.designPointLoadKN, "kN")
        ResultLine("RA", r.reactionA, "kN")
        ResultLine("RB", r.reactionB, "kN")
        ResultLine("VEd,max", r.maxShearAbs, "kN")
        ResultLine("MEd,max", r.maxMoment, "kNm")
        ResultLine("d", r.effectiveDepthM * 100.0, "cm")
        ResultLine("As necessária", r.requiredSteelCm2, "cm²")
        ResultLine("As mínima", r.minSteelCm2, "cm²")
        ResultLine("As a adotar", r.adoptedSteelCm2, "cm²")
        ResultLine("Flecha imediata", r.deflectionEstimateMm, "mm")
        ResultLine("Limite L/250", r.deflectionLimitMm, "mm")
        ResultLine("Utilização flecha", r.deflectionRatio * 100.0, "%")
    } }
}

@Composable private fun ResultLine(label: String, value: String, unit: String) { Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) { Text(label); Text("$value $unit", fontWeight = FontWeight.Bold) } }
@Composable private fun ResultLine(label: String, value: Double, unit: String) = ResultLine(label, value.fmt(), unit)

@Composable
private fun DiagramCard(title: String, r: BeamResult, shear: Boolean) {
    Card { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(title, fontWeight = FontWeight.Bold)
        Canvas(Modifier.fillMaxWidth().height(180.dp)) {
            val values = r.diagram.map { if (shear) it.shear else it.moment }
            val maxAbs = values.maxOf { abs(it) }.coerceAtLeast(1.0)
            val left = 8f; val right = size.width - 8f; val midY = size.height / 2f
            drawLine(color = androidx.compose.ui.graphics.Color.Gray, start = Offset(left, midY), end = Offset(right, midY), strokeWidth = 2f)
            val path = Path()
            r.diagram.forEachIndexed { index, p ->
                val x = left + (right - left) * (p.x / r.diagram.last().x.coerceAtLeast(0.1)).toFloat()
                val v = if (shear) p.shear else p.moment
                val y = midY - (v / maxAbs * (size.height * 0.42f)).toFloat()
                if (index == 0) path.moveTo(x, y) else path.lineTo(x, y)
            }
            drawPath(path, color = androidx.compose.ui.graphics.Color(0xFF0B3D5C), style = Stroke(width = 4f))
        }
    } }
}

@Composable
private fun ReportCard(r: BeamResult) {
    Card { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text("Memória de cálculo", fontWeight = FontWeight.Bold)
        Text("Resumo pronto para futura exportação PDF:")
        Text("${r.beamType.label} | MEd=${r.maxMoment.fmt()} kNm | VEd=${r.maxShearAbs.fmt()} kN | As=${r.adoptedSteelCm2.fmt()} cm² | flecha=${r.deflectionEstimateMm.fmt()} mm")
        r.notes.forEach { Text("• $it") }
    } }
}

private fun String.toD(default: Double): Double = toDoubleOrNull() ?: default
private fun Double.fmt(): String = String.format(Locale.US, "%.2f", this)
