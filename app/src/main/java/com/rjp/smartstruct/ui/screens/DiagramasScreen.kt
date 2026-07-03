package com.rjp.smartstruct.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DiagramasScreen() {
    Column(
        Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Gráficos e desenhos automáticos", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Text("V7 adiciona uma zona gráfica para transformar resultados em desenhos técnicos rápidos: esquema estrutural, cargas, reações, diagramas, armaduras e verificações visuais.")

        DiagramCard("Viga biapoiada", "Apoios, carga distribuída, reações, VEd, MEd e deformada.") { BeamDiagram() }
        DiagramCard("Pilar EC2", "Secção, varões longitudinais, estribos e indicador N-M simplificado.") { ColumnDiagram() }
        DiagramCard("Laje", "Planta, direção principal de armadura, zonas de reforço e mapa simplificado de momentos.") { SlabDiagram() }
        DiagramCard("Sapata", "Pilar, distribuição de tensões no solo, σmax/σmin, punçoamento e armaduras X/Y.") { FootingDiagram() }
        DiagramCard("Muro de suporte", "Terreno, impulso triangular, resultante ativa, base e tensões no solo.") { WallDiagram() }

        Card { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text("Próxima etapa gráfica", fontWeight = FontWeight.Bold)
            Text("Ligar estes desenhos aos valores reais calculados em cada módulo, para o esquema mudar automaticamente com os dados introduzidos.")
        } }
    }
}

@Composable
private fun DiagramCard(title: String, subtitle: String, content: @Composable () -> Unit) {
    Card { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(title, fontWeight = FontWeight.Bold)
        Text(subtitle, style = MaterialTheme.typography.bodySmall)
        Box(Modifier.fillMaxWidth().height(210.dp)) { content() }
    } }
}

@Composable
private fun BeamDiagram() {
    Canvas(Modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height
        val y = h * 0.35f
        val x1 = w * 0.12f
        val x2 = w * 0.88f
        drawLine(Color.Black, Offset(x1, y), Offset(x2, y), strokeWidth = 6f, cap = StrokeCap.Round)
        val leftSupport = Path().apply { moveTo(x1, y); lineTo(x1 - 24f, y + 42f); lineTo(x1 + 24f, y + 42f); close() }
        val rightSupport = Path().apply { moveTo(x2, y); lineTo(x2 - 24f, y + 42f); lineTo(x2 + 24f, y + 42f); close() }
        drawPath(leftSupport, Color.DarkGray, style = Stroke(width = 4f))
        drawPath(rightSupport, Color.DarkGray, style = Stroke(width = 4f))
        for (i in 0..8) {
            val x = x1 + (x2 - x1) * i / 8f
            drawLine(Color.Red, Offset(x, y - 55f), Offset(x, y - 10f), strokeWidth = 3f)
            val arrow = Path().apply { moveTo(x, y - 10f); lineTo(x - 8f, y - 24f); lineTo(x + 8f, y - 24f); close() }
            drawPath(arrow, Color.Red)
        }
        drawLine(Color(0xFF1565C0), Offset(x1, y + 92f), Offset(w * 0.5f, y + 132f), strokeWidth = 4f)
        drawLine(Color(0xFF1565C0), Offset(w * 0.5f, y + 132f), Offset(x2, y + 92f), strokeWidth = 4f)
        val m = Path().apply { moveTo(x1, y + 150f); quadraticBezierTo(w * 0.5f, y + 205f, x2, y + 150f) }
        drawPath(m, Color(0xFF2E7D32), style = Stroke(width = 5f))
    }
}

@Composable
private fun ColumnDiagram() {
    Canvas(Modifier.fillMaxSize()) {
        val cx = size.width / 2f
        val cy = size.height / 2f
        val bw = size.width * 0.46f
        val bh = size.height * 0.62f
        drawRect(Color.LightGray, topLeft = Offset(cx - bw / 2, cy - bh / 2), size = androidx.compose.ui.geometry.Size(bw, bh), style = Stroke(width = 5f))
        val r = 11f
        val pts = listOf(
            Offset(cx - bw/2 + 28f, cy - bh/2 + 28f), Offset(cx + bw/2 - 28f, cy - bh/2 + 28f),
            Offset(cx - bw/2 + 28f, cy + bh/2 - 28f), Offset(cx + bw/2 - 28f, cy + bh/2 - 28f),
            Offset(cx, cy - bh/2 + 28f), Offset(cx, cy + bh/2 - 28f)
        )
        pts.forEach { drawCircle(Color(0xFFB71C1C), r, it) }
        drawRect(Color(0xFF1565C0), topLeft = Offset(cx - bw/2 + 18f, cy - bh/2 + 18f), size = androidx.compose.ui.geometry.Size(bw - 36f, bh - 36f), style = Stroke(width = 3f))
        drawLine(Color.Red, Offset(cx, 10f), Offset(cx, cy - bh/2 - 12f), strokeWidth = 4f)
    }
}

@Composable
private fun SlabDiagram() {
    Canvas(Modifier.fillMaxSize()) {
        val x = size.width * 0.16f
        val y = size.height * 0.18f
        val w = size.width * 0.68f
        val h = size.height * 0.58f
        drawRect(Color.LightGray, Offset(x, y), androidx.compose.ui.geometry.Size(w, h), style = Stroke(width = 5f))
        for (i in 1..7) {
            val xx = x + w * i / 8f
            drawLine(Color(0xFFB71C1C), Offset(xx, y + 8f), Offset(xx, y + h - 8f), strokeWidth = 3f)
        }
        for (i in 1..4) {
            val yy = y + h * i / 5f
            drawLine(Color(0xFF1565C0), Offset(x + 8f, yy), Offset(x + w - 8f, yy), strokeWidth = 2f)
        }
        drawCircle(Color(0x332E7D32), radius = h * 0.28f, center = Offset(x + w / 2f, y + h / 2f))
    }
}

@Composable
private fun FootingDiagram() {
    Canvas(Modifier.fillMaxSize()) {
        val cx = size.width / 2f
        val y = size.height * 0.28f
        val fw = size.width * 0.72f
        val fh = size.height * 0.32f
        drawRect(Color.LightGray, Offset(cx - fw/2, y), androidx.compose.ui.geometry.Size(fw, fh), style = Stroke(width = 5f))
        drawRect(Color.DarkGray, Offset(cx - 34f, y - 68f), androidx.compose.ui.geometry.Size(68f, 68f), style = Stroke(width = 5f))
        for (i in 1..7) drawLine(Color(0xFFB71C1C), Offset(cx - fw/2 + i*fw/8f, y + 12f), Offset(cx - fw/2 + i*fw/8f, y + fh - 12f), strokeWidth = 3f)
        val stress = Path().apply { moveTo(cx - fw/2, y + fh + 24f); lineTo(cx + fw/2, y + fh + 24f); lineTo(cx + fw/2, y + fh + 90f); lineTo(cx - fw/2, y + fh + 50f); close() }
        drawPath(stress, Color(0x552E7D32))
        drawPath(stress, Color(0xFF2E7D32), style = Stroke(width = 3f))
    }
}

@Composable
private fun WallDiagram() {
    Canvas(Modifier.fillMaxSize()) {
        val baseY = size.height * 0.72f
        val stemX = size.width * 0.42f
        drawRect(Color.DarkGray, Offset(size.width * 0.20f, baseY), androidx.compose.ui.geometry.Size(size.width * 0.58f, 34f))
        drawRect(Color.DarkGray, Offset(stemX, size.height * 0.16f), androidx.compose.ui.geometry.Size(32f, baseY - size.height * 0.16f))
        val ground = Path().apply { moveTo(stemX + 32f, size.height * 0.16f); lineTo(size.width * 0.90f, baseY); lineTo(stemX + 32f, baseY); close() }
        drawPath(ground, Color(0x553C8D40))
        drawPath(ground, Color(0xFF2E7D32), style = Stroke(width = 3f))
        val thrust = Path().apply { moveTo(stemX - 18f, size.height * 0.18f); lineTo(stemX - 18f, baseY); lineTo(stemX - 110f, baseY); close() }
        drawPath(thrust, Color(0x55B71C1C))
        drawPath(thrust, Color(0xFFB71C1C), style = Stroke(width = 3f))
        drawLine(Color.Red, Offset(stemX - 110f, baseY - 40f), Offset(stemX - 45f, baseY - 40f), strokeWidth = 5f)
    }
}
