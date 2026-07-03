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
import com.rjp.smartstruct.calculation.RetainingWallCalculator
import com.rjp.smartstruct.calculation.RetainingWallInput
import java.util.Locale

@Composable
fun MuroScreen() {
    var altura by remember { mutableStateOf("3.00") }
    var gamma by remember { mutableStateOf("18.0") }
    var phi by remember { mutableStateOf("30") }
    var sobrecarga by remember { mutableStateOf("10") }
    var larguraBase by remember { mutableStateOf("2.40") }
    var bico by remember { mutableStateOf("0.70") }
    var espessura by remember { mutableStateOf("0.30") }
    var gammaBetao by remember { mutableStateOf("25") }
    var atrito by remember { mutableStateOf("0.55") }
    var qadm by remember { mutableStateOf("200") }
    var betao by remember { mutableStateOf("C25/30") }
    var aco by remember { mutableStateOf("500") }

    val input = RetainingWallInput(
        heightM = altura.toDoubleOrNull() ?: 3.0,
        gammaSoil = gamma.toDoubleOrNull() ?: 18.0,
        phiDeg = phi.toDoubleOrNull() ?: 30.0,
        surchargeKpa = sobrecarga.toDoubleOrNull() ?: 10.0,
        baseWidthM = larguraBase.toDoubleOrNull() ?: 2.4,
        toeM = bico.toDoubleOrNull() ?: 0.7,
        stemThicknessM = espessura.toDoubleOrNull() ?: 0.3,
        concreteGamma = gammaBetao.toDoubleOrNull() ?: 25.0,
        frictionCoefficient = atrito.toDoubleOrNull() ?: 0.55,
        qAdmKpa = qadm.toDoubleOrNull() ?: 200.0,
        concreteClass = betao,
        steelFykMpa = aco.toDoubleOrNull() ?: 500.0
    )
    val result = RetainingWallCalculator.calculate(input)

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Muros EC7 / EC2", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Text("Impulsos de terras, estabilidade ao deslizamento/derrubamento, tensões no solo e armadura simplificada.")

        Card { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Geometria", fontWeight = FontWeight.Bold)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                NumberField("H (m)", altura, { altura = it }, Modifier.weight(1f))
                NumberField("Base B (m)", larguraBase, { larguraBase = it }, Modifier.weight(1f))
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                NumberField("Bico/toe (m)", bico, { bico = it }, Modifier.weight(1f))
                NumberField("Esp. muro (m)", espessura, { espessura = it }, Modifier.weight(1f))
            }
        } }

        Card { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Solo e materiais", fontWeight = FontWeight.Bold)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                NumberField("γ solo", gamma, { gamma = it }, Modifier.weight(1f))
                NumberField("φ (º)", phi, { phi = it }, Modifier.weight(1f))
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                NumberField("Sobrecarga kPa", sobrecarga, { sobrecarga = it }, Modifier.weight(1f))
                NumberField("qadm kPa", qadm, { qadm = it }, Modifier.weight(1f))
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                NumberField("μ atrito", atrito, { atrito = it }, Modifier.weight(1f))
                NumberField("γ betão", gammaBetao, { gammaBetao = it }, Modifier.weight(1f))
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                NumberField("fyk MPa", aco, { aco = it }, Modifier.weight(1f))
                OutlinedTextField(value = betao, onValueChange = { betao = it }, label = { Text("Betão") }, modifier = Modifier.weight(1f))
            }
        } }

        Card { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text("Resultados principais", fontWeight = FontWeight.Bold)
            ResultLine("Ka", result.ka, "")
            ResultLine("Kp", result.kp, "")
            ResultLine("K0", result.k0, "")
            ResultLine("Pa solo", result.paSoilKnM, "kN/m")
            ResultLine("Pa sobrecarga", result.paSurchargeKnM, "kN/m")
            ResultLine("Pa total", result.paTotalKnM, "kN/m")
            ResultLine("M derrubamento", result.overturningMomentKnM, "kN.m/m")
            ResultLine("M resistente", result.resistingMomentKnM, "kN.m/m")
            ResultLine("Carga vertical", result.verticalLoadKnM, "kN/m")
            ResultLine("Excentricidade", result.eccentricityM, "m")
            ResultLine("σmax", result.sigmaMaxKpa, "kPa")
            ResultLine("σmin", result.sigmaMinKpa, "kPa")
            ResultLine("FS deslizamento", result.fsSliding, "")
            ResultLine("FS derrubamento", result.fsOverturning, "")
            ResultLine("As muro", result.stemAsCm2M, "cm²/m")
            ResultLine("As base", result.baseAsCm2M, "cm²/m")
        } }

        Card { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text("Verificações automáticas", fontWeight = FontWeight.Bold)
            result.status.forEach { Text("• $it") }
        } }
    }
}

@Composable
private fun NumberField(label: String, value: String, onChange: (String) -> Unit, modifier: Modifier = Modifier) {
    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        singleLine = true,
        modifier = modifier
    )
}

@Composable
private fun ResultLine(label: String, value: Double, unit: String) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label)
        Text(String.format(Locale.US, "%.2f %s", value, unit), fontWeight = FontWeight.Bold)
    }
}
