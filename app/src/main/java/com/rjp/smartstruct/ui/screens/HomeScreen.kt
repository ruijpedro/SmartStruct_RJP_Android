package com.rjp.smartstruct.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen() {
    var tab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Início", "Vigas EC2", "Pilares", "Lajes", "Fundações", "Muros", "Combinações", "Gráficos")

    Column(Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("SmartStruct_RJP", fontWeight = FontWeight.Bold) },
            subtitle = { Text("V7 • EC2/EC7 + EC1 • Gráficos técnicos") }
        )
        TabRow(selectedTabIndex = tab) {
            tabs.forEachIndexed { index, title ->
                Tab(selected = tab == index, onClick = { tab = index }, text = { Text(title) })
            }
        }
        when (tab) {
            0 -> StartScreen(onOpenVigas = { tab = 1 })
            1 -> VigaScreen()
            2 -> PilarScreen()
            3 -> LajeScreen()
            4 -> FundacaoScreen()
            5 -> MuroScreen()
            6 -> CombinacoesScreen()
            else -> DiagramasScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBar(title: @Composable () -> Unit, subtitle: @Composable () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Column {
                title()
                CompositionLocalProvider(LocalTextStyle provides MaterialTheme.typography.labelMedium) { subtitle() }
            }
        }
    )
}

@Composable
private fun StartScreen(onOpenVigas: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Card { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("SmartStruct_RJP V7", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Text("Base profissional para cálculo estrutural/geotécnico académico com módulos EC2/EC7, combinações EC1 e uma nova zona gráfica para desenhos técnicos automáticos.")
            Button(onClick = onOpenVigas) { Text("Abrir módulo Vigas EC2") }
        } }
        Card { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text("Incluído nesta V7", fontWeight = FontWeight.Bold)
            Text("• Estrutura Android corrigida e compilável")
            Text("• Motor de cálculo independente para vigas biapoiadas e consola")
            Text("• ELU e serviço, biblioteca de materiais C20/25 a C35/45")
            Text("• Reações, VEd, MEd, As, flecha L/250 e alertas automáticos")
            Text("• Diagramas V/M e resumo de memória de cálculo")
            Text("• Novo módulo Pilares: NEd, MEd, esbeltez, excentricidade e As estimada")
            Text("• Novo módulo Lajes: laje unidirecional, MEd/m, VEd/m e As/m")
            Text("• Novo módulo Fundações: sapata isolada, qadm, excentricidade, As e punçoamento simplificado")
            Text("• Novo módulo Muros: Ka/Kp/K0, impulsos, deslizamento, derrubamento, tensões no solo e armadura simplificada")
            Text("• Novo módulo Combinações: ELU, ELS rara, ELS frequente e ELS quase permanente")
            Text("• Novo separador Gráficos: desenhos técnicos de viga, pilar, laje, sapata e muro")
            Text("• Estrutura preparada para relatório PDF/memória de cálculo com imagens dos esquemas")
        } }
        Card { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text("Aviso RJP", fontWeight = FontWeight.Bold)
            Text("Ferramenta académica/de apoio. Os resultados devem ser verificados antes de qualquer utilização profissional.")
        } }
    }
}

